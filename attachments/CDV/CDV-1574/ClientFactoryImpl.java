package org.terracotta.express;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.terracotta.express.ClientImpl.ClientShutdownException;
import org.terracotta.express.loader.Handler;
import org.terracotta.express.loader.Jar;
import org.terracotta.express.loader.JarManager;

@SuppressWarnings("rawtypes")
public class ClientFactoryImpl implements ClientFactoryInternal {

  private final JarManager              jarManager     = new JarManager();
  private final Map<String, URL>        virtualTimJars = new ConcurrentHashMap<String, URL>();
  private final Map<String, ClientImpl> clientsByUrl   = new ConcurrentHashMap<String, ClientImpl>();
  private final URL                     bootJarUrl;
  private final List<Jar>               l1Jars         = Collections.synchronizedList(new ArrayList<Jar>());
  private final BundleClassLoader		appClassLoader = new BundleClassLoader();

  // public nullary constructor needed as entry point from SPI
  public ClientFactoryImpl() {
    testForBootJar();
    testForWrongTcconfig();

    System.setProperty("tc.active", "true");
    System.setProperty("tc.dso.globalmode", "false");

    URL bootJar = null;

    ZipInputStream standaloneJar = null;
    try {
      URL source = ClientImpl.class.getProtectionDomain().getCodeSource().getLocation();
      source = org.terracotta.express.loader.Util.fixUpUrl(source);

      File sigarTmpDir = createTempDir("tmpSigarJars");
      standaloneJar = new ZipInputStream(source.openStream());
      for (ZipEntry entry = standaloneJar.getNextEntry(); entry != null; entry = standaloneJar.getNextEntry()) {
        if (entry.getName().startsWith("L1") && entry.getName().endsWith(".jar")) {
          URL l1Jar = new URL("jar:" + source.toExternalForm() + "!/" + entry.getName());
          l1Jars.add(jarManager.getOrCreate(l1Jar.toExternalForm(), l1Jar));
        } else if (entry.getName().startsWith("TIMs") && entry.getName().endsWith(".jar")) {
          String baseJar = baseName(entry);
          URL timJarUrl = new URL("jar:" + source.toExternalForm() + "!/" + entry.getName());
          jarManager.getOrCreate(timJarUrl.toExternalForm(), timJarUrl);
          virtualTimJars.put(baseJar, newTcJarUrl(timJarUrl));
        } else if (entry.getName().equals("dso-boot.jar")) {
          bootJar = new URL("jar:" + source.toExternalForm() + "!/" + entry.getName());
          jarManager.getOrCreate(bootJar.toExternalForm(), bootJar);
        } else if (entry.getName().equals("exported-classes.jar")) {
          URL exports = new URL("jar:" + source.toExternalForm() + "!/" + entry.getName());
          jarManager.getOrCreate(exports.toExternalForm(), exports);
        }

        // extract to tmp dir if sigar file
        if (entry.getName().toLowerCase().contains("sigar")) {
          handleSigarZipEntry(standaloneJar, entry, sigarTmpDir);
        }
      }

      bootJarUrl = bootJar;

      // XXX: do we need to coordinate with other instances of this library in the same VM?
      System.setProperty(SIGAR_LIB_PATH_PROPERTY_NAME, sigarTmpDir.getAbsolutePath());

    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    } finally {
      if (standaloneJar != null) {
        try {
          standaloneJar.close();
        } catch (IOException ioe) {
          // ignore
        }
      }
    }

  }

  @Override
  public Client getOrCreateClient(String tcConfig, boolean isUrlConfig, Class[] moduleIntrospectionSources) {
    if (!isUrlConfig) { return createClient(tcConfig, isUrlConfig, moduleIntrospectionSources); }

    ClientImpl client = clientsByUrl.get(tcConfig);
    if (client != null) {
      try {

    	// FIXME Need to add introspection sources to appClassLoader
    	appClassLoader.addIntrospectionSources(moduleIntrospectionSources);

        client.join(moduleIntrospectionSources);
        return client;
      } catch (ClientShutdownException e) {
        //
      }
    }

    synchronized (tcConfig.intern()) {
      client = clientsByUrl.get(tcConfig);
      if (client != null) {
        try {

          // FIXME Need to add introspection sources to appClassLoader
          appClassLoader.addIntrospectionSources(moduleIntrospectionSources);

          client.join(moduleIntrospectionSources);
          return client;
        } catch (ClientShutdownException e) {
          //
        }
      }

      // FIXME Need to add introspection sources to appClassLoader
      appClassLoader.addIntrospectionSources(moduleIntrospectionSources);
      client = createClient(tcConfig, isUrlConfig, moduleIntrospectionSources);
      clientsByUrl.put(tcConfig, client);
      return client;
    }
  }

  void remove(ClientImpl clientImpl, String tcConfig, boolean isUrlConfig) {
    if (isUrlConfig) {
      synchronized (tcConfig.intern()) {
        ClientImpl removed = clientsByUrl.remove(tcConfig);
        if (removed != clientImpl) { throw new AssertionError("removed: " + removed + " for " + tcConfig); }
      }
    }
  }

  private ClientImpl createClient(String tcConfig, boolean isUrlConfig, Class[] moduleIntrospectionSources) {
    // XXX: This is far from correct.
    List<URL> timJars = new ArrayList<URL>();
    for (Entry<String, URL> entry : virtualTimJars.entrySet()) {
      if (entry.getKey().startsWith("tim-") || entry.getKey().startsWith("terracotta-toolkit")) {
        timJars.add(entry.getValue());
      }
    }

    return newClient(tcConfig, isUrlConfig, timJars, moduleIntrospectionSources);
  }

  private ClientImpl newClient(String tcConfig, boolean isUrlConfig, List<URL> timJars,
                               Class[] moduleIntrospectionSources) {
    ClientImpl client;
    client = new ClientImpl(tcConfig, isUrlConfig, jarManager, timJars.toArray(new URL[] {}), appClassLoader,
    		virtualTimJars, l1Jars, bootJarUrl, moduleIntrospectionSources, this);
    return client;
  }

  private static final String SIGAR_LIB_PATH_PROPERTY_NAME = "org.hyperic.sigar.path";
  private static final String BASE_LIBRARY_NAME            = baseLibraryName();

  private static void handleSigarZipEntry(final ZipInputStream agentJar, final ZipEntry entry, final File sigarTmpDir)
      throws IOException {
    // extract only if this is for the current platform
    if (entry.getName().contains(BASE_LIBRARY_NAME)) {
      extractSigarZipEntry(agentJar, entry, sigarTmpDir);
    }
  }

  private static void extractSigarZipEntry(final ZipInputStream jar, final ZipEntry entry, final File outputDir)
      throws IOException {
    byte[] content = getCurrentZipEntry(jar);
    String outName = baseName(entry);

    // no need to strip off the version (like in hibernate-agent)

    // dump the content at outputDir/outName
    File outFile = new File(outputDir, outName);
    writeFile(outFile, content);
    outFile.deleteOnExit();
  }

  private static void writeFile(final File file, final byte[] contents) throws IOException {
    FileOutputStream out = null;

    try {
      out = new FileOutputStream(file);
      out.write(contents);
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException ioe) {
          // ignore
        }
      }
    }
  }

  private static byte[] getCurrentZipEntry(final ZipInputStream zis) throws IOException {
    byte[] buf = new byte[1024];
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    int n;
    while ((n = zis.read(buf, 0, 1024)) > -1) {
      bout.write(buf, 0, n);
    }
    bout.close();
    return bout.toByteArray();
  }

  private static File createTempDir(final String prefix) throws IOException {
    final File tempDir = File.createTempFile(prefix, Long.toString(System.nanoTime()));
    if (!(tempDir.delete())) { throw new IOException("Could not delete temp file: " + tempDir.getAbsolutePath()); }
    if (!(tempDir.mkdir())) { throw new IOException("Could not create temp directory: " + tempDir.getAbsolutePath()); }
    tempDir.deleteOnExit();
    return tempDir;
  }

  private static void testForBootJar() {
    try {
      Class c = Class.forName("com.tc.object.bytecode.ManagerUtil");
      if (c.getClassLoader() == null) {
        //
        throw new RuntimeException(
                                   "The Terracotta dso-boot.jar is specified via -Xbootclasspath. This is not a correct configuration, please remove it");
      }

    } catch (ClassNotFoundException cnfe) {
      // expected
    } catch (NoClassDefFoundError ncdfe) {
      // expected
    }
  }

  private static void testForWrongTcconfig() {
    String tcConfigValue = System.getProperty("tc.config");
    if (tcConfigValue != null) {
      //
      throw new RuntimeException("The Terracotta config file should not be set through -Dtc.config in this usage.");
    }
  }

  private static String baseName(final ZipEntry entry) {
    return new File(entry.getName()).getName();
  }

  private URL newTcJarUrl(final URL embedded) {
    try {
      return new URL(Handler.TC_JAR_PROTOCOL, "", -1, Handler.TAG + embedded.toExternalForm() + Handler.TAG + "/",
                     new Handler(jarManager));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  // Adapted from Sigar's ArchName.java

  private static String baseLibraryName() {
    String name = System.getProperty("os.name");
    String arch = System.getProperty("os.arch");
    String version = System.getProperty("os.version");
    String majorVersion = version.substring(0, 1); // 4.x, 5.x, etc.

    StringBuffer buf = new StringBuffer();

    if (arch.endsWith("86")) {
      arch = "x86";
    }

    if (name.equals("Linux")) {
      buf.append(arch).append("-linux");
    } else if (name.indexOf("Windows") > -1) {
      buf.append(arch).append("-winnt");
    } else if (name.equals("SunOS")) {
      if (arch.startsWith("sparcv") && "64".equals(System.getProperty("sun.arch.data.model"))) {
        arch = "sparc64";
      }
      buf.append(arch).append("-solaris");
    } else if (name.equals("HP-UX")) {
      if (arch.startsWith("IA64")) {
        arch = "ia64";
      } else {
        arch = "pa";
      }
      if (version.indexOf("11") > -1) {
        buf.append(arch).append("-hpux-11");
      }
    } else if (name.equals("AIX")) {
      buf.append("ppc-aix-").append(majorVersion);
    } else if (name.equals("Mac OS X")) {
      buf.append("universal-macosx");
    } else if (name.equals("FreeBSD")) {
      // none of the 4,5,6 major versions are binary compatible
      buf.append(arch).append("-freebsd-").append(majorVersion);
    } else if (name.equals("OpenBSD")) {
      buf.append(arch).append("-openbsd-").append(majorVersion);
    } else if (name.equals("NetBSD")) {
      buf.append(arch).append("-netbsd-").append(majorVersion);
    } else if (name.equals("OSF1")) {
      buf.append("alpha-osf1-").append(majorVersion);
    } else if (name.equals("NetWare")) {
      buf.append("x86-netware-").append(majorVersion);
    }

    if (buf.length() == 0) {
      return null;
    }
      String prefix = "libsigar-";
      if (name.startsWith("Windows")) {
        prefix = "sigar-";
      }
      return prefix + buf.toString();

  }
  

  	/**
  	 * {@link ClassLoader} merging the class loader from the introspected {@link Bundle}s.
  	 */

	private class BundleClassLoader extends ClassLoader {

		private final CopyOnWriteArrayList<Bundle>	_bundles	= new CopyOnWriteArrayList<Bundle>();


		protected BundleClassLoader() {
			super(ClientFactoryImpl.this.getClass().getClassLoader());
		}


		protected void addIntrospectionSources(Class<?>... srcs) {

			for (Class<?> src : srcs) {
				
				Bundle bndl = FrameworkUtil.getBundle(src);
				_bundles.addIfAbsent(bndl);
				
			}
		}


		@Override
		public Class<?> loadClass(String name) throws ClassNotFoundException {

			try {
				return super.loadClass(name);
			} catch (ClassNotFoundException ignored) {}

			for (Bundle bndl : _bundles)
				try {
					return bndl.loadClass(name);
				} catch (ClassNotFoundException ignored) {}

			throw new ClassNotFoundException(name);
		}
	}
}
