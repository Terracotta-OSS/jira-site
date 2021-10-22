import org.apache.log4j.LogSF;
import org.apache.log4j.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Test
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Logger logger = Logger.getLogger(Test.class);
        
        String key = "key";

        CacheManager cm = CacheManager.create();
        
        Cache cache = cm.getCache("test");
        
        long start = System.currentTimeMillis();
        cache.put(new Element(key, "test"));
        for (int i = 0 ; i < 10 ; i ++)
        {
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                logger.error(e.getMessage(), e);
            }
            
            Element e = cache.get(key);
            if (e != null)
            {
                LogSF.info(logger, "{}ms - Creation: {}, Access - {}, Update: {}", 
                        System.currentTimeMillis() - start, e.getCreationTime(), 
                        e.getLastAccessTime(), e.getLastUpdateTime());
            }
            else
            {
                LogSF.info(logger, "{}ms - Expired", System.currentTimeMillis() - start);
                break;
            }
        }
        
        cm.shutdown();
    }

}
