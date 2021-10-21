package my;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import net.sf.ehcache.CacheManager;

public class EhcacheTest implements Runnable
{
    protected static final boolean DEBUG = false;
    protected static final String HR = "========================================";

    protected PrintWriter printWriter = createPrintWriter();

    public static void main(final String[] args)
    {
        final EhcacheTest app = new EhcacheTest();
        app.run();
    }

    @Override
    public void run()
    {
        final PrintWriter out = getPrintWriter();
        if (DEBUG)
        {
            dumpCharsetInfo(System.getProperty("file.encoding"));
            dumpProperties();
        }
        final InputStream stream = EhcacheTest.class.getResourceAsStream("/ehcache.xml");
        assert stream != null;
        try
        {
            final CacheManager cacheManager = new CacheManager(stream);
            cacheManager.shutdown();
        }
        catch (final Throwable t)
        {
            t.printStackTrace(out);
        }
        finally
        {
            out.close();
        }
    }

    protected void dumpProperties()
    {
        final PrintWriter  out   = getPrintWriter();
        final Properties   props = System.getProperties();
        final List<String> keys  = (List) Collections.list(props.keys());

        Collections.sort(keys);

        for (String key : keys)
        {
            if (key.contains("encoding"))
            {
                out.println(key + " = " + props.getProperty(key));
            }
        }
        out.println(HR);
    }

    protected void dumpCharsetInfo(final String cs)
    {
        final Charset     charset = Charset.forName(cs);
        final PrintWriter out     = getPrintWriter();

        out.println("charset name: " + charset.name());

        Set<String> aliases = charset.aliases();

        out.println("charset aliases:");

        for (String alias : aliases)
        {
            out.println("  " + alias);
        }
        out.println(HR);
    }

    protected PrintWriter createPrintWriter()
    {
        PrintWriter out = null;
        try
        {
            out = new PrintWriter("./EhcacheTest-output.txt", "UTF-8");
        }
        catch (final FileNotFoundException e)
        {
            throw new Error(e);
        }
        catch (final UnsupportedEncodingException e)
        {
            // not possible for UTF-8
        }
        return out;
    }

    protected final PrintWriter getPrintWriter() {
        return this.printWriter;
    }
}
