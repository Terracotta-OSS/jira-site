package cache.test;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.loader.CacheLoader;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

/**
 * Test class to validate that the cache loader will sometimes be skipped when calling getAllWithLoader()
 * @author Scott Buchholz (SB017701)
 */
public class ConcurrentReadsTest {
    private long uniqueValue = 1000L;

    @Before
    public void beforeClass() throws Exception {

        // Shutdown the singleton CacheManager so we can initialize a different one below.
        CacheManager.create().shutdown();

        // Depending on the speed of your machine you may need to adjust the TTL/max entries to verify the failure
        CacheConfiguration configuration = new CacheConfiguration("test-cache", 10)
                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU).eternal(false).timeToLiveSeconds(10)
                .diskExpiryThreadIntervalSeconds(0);
        configuration.setMaxElementsOnDisk(10);

        Cache testCache = new Cache(configuration);
        testCache.registerCacheLoader(new TestLoader());
        CacheManager.getInstance().addCache(testCache);
    }

    @After
    public void afterClass() throws Exception {
        // Shutdown the singleton CacheManager
        CacheManager.getInstance().shutdown();

    }

    @Test
    public void testConcurrentPrivilegeRetrieval() throws Exception {
        final long sleepMilliseconds = TimeUnit.SECONDS.toMillis(30);

        // Give the threads a few seconds to stop before the test ends so the threads do not access components
        // which have been shutdown causing exceptions to be logged.
        final long stopMilliseconds = System.currentTimeMillis() + sleepMilliseconds - TimeUnit.SECONDS.toMillis(2);

        final String startDateTime = new DateTime().toString();
        final Collection<String> failures = new CopyOnWriteArrayList<String>();

        // Spin up a large number of thread to maximize chances of seeing the issue
        for (int x = 0; x < 100; x++) {
            new Thread(new CacheAccessor(CacheManager.getInstance().getCache("test-cache"), stopMilliseconds,
                    Long.valueOf(x % 10), failures)).start();
        }

        Thread.sleep(sleepMilliseconds);

        if (!failures.isEmpty()) {
            fail(String.format("Encountered %d failures; start time: %s; failure messages: \n%s", failures.size(),
                    startDateTime, Joiner.on("\n").join(failures)));
        }
    }

    /**
     * Retrieve a value from cache using the cache loader.
     */
    private static final class CacheAccessor implements Runnable {
        private final long stopMilliseconds;
        private final Collection<String> failures;
        private final Cache cache;
        private final Long value;

        public CacheAccessor(final Cache cache, final long stopMilliseconds, final Long value,
                final Collection<String> failures) {

            this.stopMilliseconds = stopMilliseconds;
            this.failures = failures;
            this.value = value;
            this.cache = cache;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {

            for (int i = 1; i < 10000000; i++) {

                try {

                    Map<Long, Long> results = cache.getAllWithLoader(Collections.singleton(value), null);
                    if (results.get(value) == null) {
                        failures.add(String.format("Failed to find value:%s", value));
                    }
                } catch (final Exception e) {
                    failures.add(e.getMessage());
                }

                if (System.currentTimeMillis() > stopMilliseconds) {
                    return;
                }
            }
        }
    }

    // This cache loader always returns a value for key (just echos the key out as the result.
    public static class TestLoader implements CacheLoader {

        @Override
        public Object load(Object key) throws CacheException {
            return key; // To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Map loadAll(Collection keys) {
            Map<Object, Object> result = Maps.newHashMap();
            for (Object key : keys) {
                result.put(key, key);
            }
            return result;
        }

        @Override
        public Object load(Object key, Object argument) {
            return key;
        }

        @Override
        public Map loadAll(Collection keys, Object argument) {
            Map<Object, Object> result = Maps.newHashMap();
            for (Object key : keys) {
                result.put(key, key);
            }
            return result;
        }

        @Override
        public String getName() {
            return "test-cache-loader";
        }

        @Override
        public CacheLoader clone(Ehcache cache) throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        @Override
        public void init() {
        }

        @Override
        public void dispose() throws CacheException {
        }

        @Override
        public Status getStatus() {
            return Status.STATUS_ALIVE;
        }
    }
}
