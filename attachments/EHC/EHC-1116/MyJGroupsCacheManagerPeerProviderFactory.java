package denver.commerce.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.distribution.CacheManagerPeerProvider;
import net.sf.ehcache.distribution.jgroups.JGroupsCacheManagerPeerProvider;
import net.sf.ehcache.distribution.jgroups.JGroupsCacheManagerPeerProviderFactory;
import net.sf.ehcache.util.ClassLoaderUtil;
import net.sf.ehcache.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Properties;

/**
 * Created by rmartin on 1/18/16.
 */
public class MyJGroupsCacheManagerPeerProviderFactory extends JGroupsCacheManagerPeerProviderFactory {
    private static final Logger LOG = LoggerFactory.getLogger(MyJGroupsCacheManagerPeerProviderFactory.class.getName());

    private static final String CHANNEL_NAME = "channelName";
    private static final String CONNECT = "connect";
    private static final String FILE = "file";
    /**
     * {@inheritDoc}
     */
    @Override
    public CacheManagerPeerProvider createCachePeerProvider(CacheManager cacheManager, Properties properties) {
        LOG.trace("Creating JGroups CacheManagerPeerProvider for {} with properties:\n{}", cacheManager.getName(), properties);

        final String connect = this.getProperty(CONNECT, properties);
        final String file = this.getProperty(FILE, properties);
        final String channelName = this.getProperty(CHANNEL_NAME, properties);

        final JGroupsCacheManagerPeerProvider peerProvider;
        if (file != null) {
            if (connect != null) {
                LOG.warn("Both '" + CONNECT + "' and '" + FILE + "' properties set. '" + CONNECT + "' will be ignored");
            }

            // All of this copypasta from the superclass was done in order to change the following obsolete line - RAM
            //final ClassLoader contextClassLoader = ClassLoaderUtil.getStandardClassLoader();
            final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            final URL configUrl = contextClassLoader.getResource(file);

            LOG.debug("Creating JGroups CacheManagerPeerProvider for {} with configuration file: {}", cacheManager.getName(), configUrl);
            peerProvider = new JGroupsCacheManagerPeerProvider(cacheManager, configUrl);
        } else {
            LOG.debug("Creating JGroups CacheManagerPeerProvider for {} with configuration:\n{}", cacheManager.getName(), connect);
            peerProvider = new JGroupsCacheManagerPeerProvider(cacheManager, connect);
        }

        peerProvider.setChannelName(channelName);

        return peerProvider;
    }

    private String getProperty(final String name, Properties properties) {
        String property = PropertyUtil.extractAndLogProperty(name, properties);
        if (property != null) {
            property = property.trim();
            property = property.replaceAll(" ", "");
            if (property.equals("")) {
                property = null;
            }
        }
        return property;
    }
}
