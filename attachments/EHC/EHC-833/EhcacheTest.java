import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

public class EhcacheTest {
	
	private static final Logger logger = Logger.getLogger(EhcacheTest.class);
	
	public static void main(String[] args) {
		CacheManager manager = CacheManager.create();
		Cache sampleCache = manager.getCache("sample");
		int j=0;
		for(int i=0; i<10000000; i++){
			sampleCache.put(new Element(i, "something"));
			sampleCache.get(i);
			sampleCache.flush();
			j++;
			if(j==10000){
				logger.info("-----------------------------------------");
				logger.info("Statistics is " + sampleCache.getStatistics());
				logger.info("total size is " + sampleCache.getSize());
				logger.info("memory size is " + sampleCache.getMemoryStoreSize());
				logger.info("disk size is " + sampleCache.getDiskStoreSize());
				j=0;
			}
		}
	}

}
