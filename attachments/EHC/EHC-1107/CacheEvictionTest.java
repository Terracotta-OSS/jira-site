package com.timocom.trust.loginmodule.spi.spec;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import com.timocom.trust.loginmodule.spi.internal.cache.CacheResolver;

import java.util.concurrent.TimeUnit;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.junit.Test;

/**
 * Check eviction of cached objects where two objects with the same key were inserted.
 * Objects put to cache using <code>cache.put(KEY, VALUE)</code> never expire !!!!!!
 */
public class CacheEvictionTest {

    static String CACHE_NAME = "MY_CACHE";

    static String KEY = "KEY";
    static String VALUE = "VALUE";

    @Test
    public void testCache_usingPut() throws Exception {

        final CacheManager cacheManager = CacheResolver.resolveCacheProvider().getCacheManager();
        final MutableConfiguration<String, String> cacheConfig = new MutableConfiguration<String, String>()
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.MILLISECONDS, 500)));
        javax.cache.Cache<String,String> cache = cacheManager.createCache(CACHE_NAME, cacheConfig);

        assertThat("Cache should be empty", cache.get(KEY), is(nullValue()));

        cache.put(KEY, VALUE);
        cache.put(KEY, VALUE);
        assertThat("Cache must be hit - the KEY/VALUE have just been put", cache.get(KEY), is(notNullValue()));

        Thread.sleep(1000);
        assertThat("Cache should be empty now since ist has expired", cache.get(KEY), is(nullValue()));
    }

}