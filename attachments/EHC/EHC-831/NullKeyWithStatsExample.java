package com.firm58.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * This example demomstrates the NullPointerException that is thrown by an in memory cache
 * with statistics enabled.  Tested against Ehcache 2.3.0.
 */
public class NullKeyWithStatsExample
{
    public static void main(String[] argv)
    {
        // create an in memory only cache
        boolean overflowToDisk = false;
        Cache cache = new Cache("test cache", 1000, overflowToDisk, false, 3600L, 3600L);
        CacheManager cacheManger = new CacheManager();
        cacheManger.addCache(cache);

        // when stats are disabled, a get by null works
        cache.setStatisticsEnabled(false);
        cache.get(null);

        /**
         * when stats are enabled, a get by null throws a NPE
         * Exception in thread "main" java.lang.NullPointerException
         *     at net.sf.ehcache.store.compound.CompoundStore.containsKey(CompoundStore.java:329)
         *     at net.sf.ehcache.store.compound.impl.MemoryOnlyStore.containsKeyInMemory(MemoryOnlyStore.java:93)
         *     at net.sf.ehcache.Cache.searchInStoreWithStats(Cache.java:1742)
         *     at net.sf.ehcache.Cache.get(Cache.java:1405)
         *     at net.sf.ehcache.Cache.get(Cache.java:1378)
         *     at com.firm58.ehcache.NullKeyWithStatsExample.main(NullKeyWithStatsExample.java:35)
         */
        cache.setStatisticsEnabled(true);
        cache.get(null);
    }
}
