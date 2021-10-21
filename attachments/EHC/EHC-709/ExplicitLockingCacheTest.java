package net.sf.ehcache.constructs.locking;

import junit.framework.TestCase;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ExplicitLockingCacheTest extends TestCase {

    private static final String TEST_CACHE = "ExplicitLockCacheTest";
    private ExplicitLockingCache testCache;
    private CacheManager mgr;

    protected void setUp() throws Exception {
        super.setUp();
        Configuration configuration = new Configuration();
        CacheConfiguration defaultCacheConfiguration = new CacheConfiguration(
                "default", 2000);
        configuration.addDefaultCache(defaultCacheConfiguration);

        CacheConfiguration theCache = new CacheConfiguration(TEST_CACHE, 10000);
        // add more settings here!
        configuration.addCache(theCache);

        mgr = new CacheManager(configuration);
        testCache = new ExplicitLockingCache(mgr.getCache(TEST_CACHE));
    }

    /**
     * Show that if an entry already exists for the given key it is not re-added
     */
    @Test
    public void testPutIfAbsent() {
        assertCache();
    }

    private void assertCache() {
        assertEquals(testCache.get("putIfAbsent"), null);
        testCache.putIfAbsent(new Element("putIfAbsent", "1"));
        assertEquals(testCache.get("putIfAbsent").getValue(), "1");
        testCache.putIfAbsent(new Element("putIfAbsent", "2"));
        assertEquals(testCache.get("putIfAbsent").getValue(), "1");
    }

    @Test
    public void testAsDecorator() {
        Cache decoratedCache = mgr.getCache(TEST_CACHE);
        mgr.replaceCacheWithDecoratedCache(decoratedCache,
                new ExplicitLockingCache(decoratedCache));
        testCache = (ExplicitLockingCache) mgr.getEhcache(TEST_CACHE);
        assertCache();
    }

}
