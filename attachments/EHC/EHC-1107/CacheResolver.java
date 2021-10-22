package com.timocom.trust.loginmodule.spi.internal.cache;

import java.text.MessageFormat;

import javax.cache.CacheException;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resolves the cache provider to be used for caching.
 */
public final class CacheResolver {

    private static final Logger LOG = LoggerFactory.getLogger(CacheResolver.class);

    private static final String DEFAULT_EHCACHE_PROVIDER = "org.ehcache.jcache.JCacheCachingProvider";

    /**
     * Resolves a JSR107 {@link CachingProvider}.
     * @return by default an ehcache-implementation, otherwise the
     * @throws java.lang.IllegalStateException if no CachingProvider is on classpath.
     */
    public static CachingProvider resolveCacheProvider() {
        try {
            return Caching.getCachingProvider(DEFAULT_EHCACHE_PROVIDER);
        } catch (final CacheException e) {
            LOG.warn(MessageFormat.format("Could not resolve default CachingProvider [{0}]. Trying to find other...", DEFAULT_EHCACHE_PROVIDER));
        }

        try {
            return Caching.getCachingProvider();
        } catch (final CacheException e) {
            throw new IllegalStateException("Could not resolve CachingProvider. Either there's none or there are multiple implementations on classpath", e);
        }
    }

    private CacheResolver() {
    }
}
