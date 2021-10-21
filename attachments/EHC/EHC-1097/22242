import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class CacheTest {

	CacheManager cacheManager;
	public CacheTest() {
		 try {
			cacheManager = CacheManager.newInstance(new FileInputStream("ehcache.xml"));
		} catch (CacheException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void insertElements() {
		String cacheName = "testcache";
		String key = "samplekey";
		for(long i=0; i<10000; i++) {
			Set<Long> valueSet = get(cacheName, key);
			if(valueSet == null) {
				valueSet = new HashSet<Long>();
				put(cacheName, key, valueSet);
			}
			valueSet.add(i);
			put(cacheName, key, valueSet);
		}
	}


	public static void main(String[] args) {
		CacheTest ct = new CacheTest();
		ct.insertElements();
		System.out.println("Completed");
	}

	private Ehcache getCache(String cacheName) {
		return cacheManager.getEhcache(cacheName);
	}
	
    public void put(String cacheName , String key, Set<Long> value) {
        getCache(cacheName).put(new Element(key, value));
    }
    
	public Set<Long> get(String cacheName , String key) {
        Element element = getCache(cacheName).get(key);
        if (element != null) {
            return (Set<Long>) element.getObjectValue();
        }
        return null;
    }
}


