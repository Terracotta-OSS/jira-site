package net.sf.ehcache.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.event.EventContext;
import javax.naming.event.NamespaceChangeListener;
import javax.naming.event.NamingEvent;
import javax.naming.event.NamingExceptionEvent;
import javax.naming.spi.ObjectFactory;

import net.sf.ehcache.CacheManager;

/**
 * <p>
 * Allows creation of EHCache CacheManager through a JNDI ObjectFactory.
 * </p>
 * 
 * <p>
 * Example of usage in Tomcat:
 * </p>
 * 
 * <pre>
 * &lt;Resource
 *   name=&quot;/cache/CacheManager&quot;
 *   type=&quot;net.sf.ehcache.CacheManager&quot;
 *   configurationFile=&quot;${catalina.base}/conf/test-ehcache.xml&quot;
 *   factory=&quot;net.sf.ehcache.jndi.CacheManagerFactory&quot;
 *   closeMethod=&quot;shutdown&quot;
 *   singleton=&quot;true&quot;
 * /&gt;
 * </pre>
 * 
 * <p>
 * ${catalina.base}/conf/test-ehcache.xml:
 * </p>
 * 
 * <pre>
 * &lt;ehcache&gt;
 *   &lt;diskStore path=&quot;${catalina.base}/temp/cache&quot; /&gt;
 *   ...
 * &lt;/ehcache&gt;
 * </pre>
 * 
 * <p>
 * Optionally in web.xml:
 * </p>
 * 
 * <pre>
 * &lt;resource-ref&gt;
 *   &lt;res-ref-name&gt;cache/CacheManager&lt;/res-ref-name&gt;
 *   &lt;res-type&gt;net.sf.ehcache.CacheManager&lt;/res-type&gt;
 *   &lt;res-auth&gt;Container&lt;/res-auth&gt;
 * &lt;/resource-ref&gt;
 * </pre>
 * 
 * <p>
 * Retrieve it in your application:
 * </p>
 * 
 * <pre>
 * CacheManager manager = (CacheManager) new InitialContext().lookup(&quot;java:comp/env/cache/CacheManager&quot;);
 * </pre>
 * 
 * @author Xavier Dury
 */
public class CacheManagerFactory implements ObjectFactory {

	public static final String CONFIGURATION_FILE = "configurationFile";

	private static final NamespaceChangeListener SHUTDOWN_LISTENER = new NamespaceChangeListener() {

		@Override
		public void namingExceptionThrown(NamingExceptionEvent event) {}

		@Override
		public void objectAdded(NamingEvent event) {}

		@Override
		public void objectRemoved(NamingEvent event) {
			Object object = event.getOldBinding().getObject();
			if (object instanceof CacheManager) {
				CacheManager cacheManager = (CacheManager) object;
				cacheManager.shutdown();
			}
		}

		@Override
		public void objectRenamed(NamingEvent event) {}
	};

	@Override
	public Object getObjectInstance(Object object, Name jndiName, Context context, Hashtable<?, ?> environment) throws Exception {
		String configurationFile = null;
		if (object instanceof Reference) {
			Reference reference = (Reference) object;
			RefAddr refAddr = reference.get(CONFIGURATION_FILE);
			if (refAddr != null) {
				configurationFile = (String) refAddr.getContent();
			}
		}
		if (context instanceof EventContext) {
			EventContext eventContext = (EventContext) context;
			eventContext.addNamingListener(jndiName, EventContext.OBJECT_SCOPE, SHUTDOWN_LISTENER);
		}
		return configurationFile == null ? CacheManager.newInstance() : CacheManager.newInstance(configurationFile);
	}
}
