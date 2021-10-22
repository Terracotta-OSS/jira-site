import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.ehcache.Cache;
import org.ehcache.Cache.Entry;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.spi.loaderwriter.BulkCacheLoadingException;
import org.ehcache.spi.loaderwriter.BulkCacheWritingException;
import org.ehcache.spi.loaderwriter.CacheLoaderWriter;

public class TestEhcache {

  private static final Logger LOGGER = Logger.getLogger(TestEhcache.class);
  
  private static final File CACHE_DIR = new File("c:\\dev\\TestEhCache");
  
  private static enum Option {
    A, B, C, D
  }
  
  public static void main(String... args) {
    try {
      testOptionLongCache();
      //testOptionLongCache();
    }
    catch(Throwable t) {
      LOGGER.error("", t);
    }
    finally {
      LOGGER.info("done");
      System.exit(0);
    }
  }
  
  private static void testOptionLongCache() {
    String cacheName = "OptionLong";
    CacheManager manager = createOptionLongCacheManager(CACHE_DIR, cacheName);
    Cache<Option,Long> cache = manager.getCache(cacheName, Option.class, Long.class);
    showEntries(cache);
    lookupOptionLongs(cache);
    showEntries(cache);
    manager.close();
    LOGGER.info("");
  }
 
  private static <K,V> void showEntries(Cache<K,V> cache) {
    LOGGER.info("entries in cache:");
    int count = 0;
    for (Entry<K,V> entry : cache) {
      LOGGER.info(String.format("%s => %s", entry.getKey(), entry.getValue()));
      count++;
    }
    LOGGER.info(String.format("found %d entries", count));
    LOGGER.info("");
  }
  
  private static void lookupOptionLongs(Cache<Option,Long> cache) {
    LOGGER.info("performing lookups:");
    List<Option> keys = Arrays.asList(Option.A, Option.B, Option.C, Option.D);
    for (Option key : keys) {
      Long value = cache.get(key);
      LOGGER.info(String.format("get(%s) = %s", key, value));
    }
    LOGGER.info("");
  }  
  
  private static CacheManager createOptionLongCacheManager(File dir, String cacheName) {
    return CacheManagerBuilder.newCacheManagerBuilder()
      .with(CacheManagerBuilder.persistence(dir.toString()))
      .withCache(
        cacheName,
        CacheConfigurationBuilder.newCacheConfigurationBuilder(Option.class, Long.class, 
          ResourcePoolsBuilder.newResourcePoolsBuilder().disk(10, MemoryUnit.GB, true))
        .withExpiry(Expirations.timeToLiveExpiration(Duration.of(31, TimeUnit.DAYS)))
        .withLoaderWriter(new OptionLongLoader())
       )
      .build(true);
  }

  
  private static class OptionLongLoader implements CacheLoaderWriter<Option,Long> {

    @Override
    public void delete(Option arg0) throws Exception {
    }

    @Override
    public void deleteAll(Iterable<? extends Option> arg0)
      throws BulkCacheWritingException, Exception {
    }

    @Override
    public Long load(Option value) throws Exception {
      LOGGER.info("loading " + value);
      return Long.valueOf(value.name().charAt(0));
    }

    @Override
    public Map<Option, Long> loadAll(Iterable<? extends Option> values)
      throws BulkCacheLoadingException, Exception {
      return null;
    }

    @Override
    public void write(Option arg0, Long arg1) throws Exception {
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void writeAll(Iterable<? extends Map.Entry<? extends Option, ? extends Long>> arg0)
      throws BulkCacheWritingException, Exception {
    }
    
  }  
  
}
