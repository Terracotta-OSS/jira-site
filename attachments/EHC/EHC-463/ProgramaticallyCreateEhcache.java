package org.tc;
import java.io.File;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

public class ProgramaticallyCreateEhcache {

	CacheManager 	cacheManager = CacheManager.getInstance();
	Cache 			cache;
	
	public ProgramaticallyCreateEhcache(){
		System.out.println("Creating cache ...");
		String diskStore	= System.getProperty("java.io.tmpdir");
//		String diskStore	= "target";
		System.out.println("Disk Store: " + diskStore);

		File disk = new File(diskStore);
		System.out.println("isDirectory		: " + disk.isDirectory());
		System.out.println("canWrite		: " + disk.canWrite());
		System.out.println("isAbsolute		: " + disk.isAbsolute());
		
		cache = new Cache("regionName",         	// String name
		        1000,                       		// int maxElementsInMemory
		        MemoryStoreEvictionPolicy.LRU,    	// MemoryStoreEvictionPolicy
		        false,                            	// boolean overflowToDisk
		        diskStore,                          // String diskStorePath
		        false,                        		// boolean eternal
		        120,                				// long timeToLiveSeconds
		        120,                				// long timeToIdleSeconds
		        true,                            	// boolean diskPersistent
		        120,  								// long diskExpiryThreadIntervalSeconds
		        null,                             	// RegisteredEventListeners
		        null,                             	// BootstrapCacheLoader 
		        0,                                	// int maxElementsOnDisk
		        0,                                	// int diskSpoolBufferSizeMB
		        true);                             	// boolean clearOnFlush
			cacheManager.addCache(cache);
			System.out.println("Created cache ... " + cache.getName());
	}
	
	public static void main(String[] args) {
		new ProgramaticallyCreateEhcache();

	}

}
