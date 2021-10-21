package smx3.statistics.standard;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.apache.commons.lang.StringUtils;
import org.fest.assertions.Assertions;
import org.testng.annotations.Test;

import java.io.Serializable;
import java.util.*;

import static java.lang.String.format;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: rowan
 * Date: 19/11/2009
 * Time: 5:14:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchResultsSupportTest {

    public synchronized Cache findOrBuildEventCache(Class aClass, String id) {

        String cacheName = aClass.getName() + ":" + id;

        CacheManager cacheManager = CacheManager.getInstance();
        if (!cacheManager.cacheExists(cacheName)) {
            final Cache cache = new Cache(cacheName, 100, MemoryStoreEvictionPolicy.FIFO, false,
                null, false, 0, 1200, false, Cache.DEFAULT_EXPIRY_THREAD_INTERVAL_SECONDS, null, null);
            cacheManager.addCache(cache);
        }

        return cacheManager.getCache(cacheName);
    }



    @Test
    public void testShouldBeAbleToSaturateCacheWithoutIssue() throws Exception {
        Cache previous = null;
        final Map<String, Element> local = new HashMap<String, Element>();
        final Map<String, Integer> keyIndex = new HashMap<String, Integer>();
        final Map<String, Boolean> keyUsed = new HashMap<String, Boolean>();
        final List<String> keySets = new ArrayList<String>();
        final LinkedList<String> keyQueue = new LinkedList<String>();
        for (int i = 0; i < 1000; i++) {
            final Cache cache = findOrBuildEventCache(String.class, "a");
            final String key = UUID.randomUUID().toString();
            final String body = UUID.randomUUID().toString();

            final Element element = new Element(key, body);
            cache.put(element);
            local.put(key, element);
            keyIndex.put(key, i);
            keyQueue.add(key);

            if (local.size() > 10) {
                final String lKey = keyQueue.remove();
                final Element retrieved = local.remove(lKey);
                final Element cached = cache.get(lKey);

                assertThat(keyUsed.containsKey(lKey)).isFalse();

                keyUsed.put(lKey, true);

                keySets.add(i + ": " + StringUtils.join(cache.getKeys().iterator(), ", "));

                final String message = "Cached value should not be null. Current run %s, key [%s] as put in on run %s, cache size is %s, and here's the key set cache:<br>%s";

                assertThat(cached)
                        .describedAs(format(message, i, lKey, keyIndex.get(lKey), cache.getSize(), StringUtils.join(keySets.iterator(), "<br>\n")))
                        .isNotNull();


                assertThat(cached)
                        .isSameAs(retrieved)
                        .describedAs("Cached value should be the same as retrieved value");

                if (previous != null) {
                    assertThat(cache).isSameAs(previous);
                }
                assertThat(local.size()).isEqualTo(10).describedAs("Local cache should have 10 elements in it.");
            }
            previous = cache;
        }
    }
}
