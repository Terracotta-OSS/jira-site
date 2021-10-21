/* How i start the cache, maybe helpful */

cacheManager = new CacheManager(Configuration.getDir() + "ehcache.xml");
BootstrapCacheLoader cacheLoader = new RMIBootstrapCacheLoaderFactory().createBootstrapCacheLoader(new Properties());

cache = new Cache("myapp-" + Configuration.getUid(), 10000, false, false, 28800, 28800);
cache.setBootstrapCacheLoader(cacheLoader);

CacheEventListener cacheEventListener = null;
RMICacheReplicatorFactory cacheReplicator = null;
cacheReplicator = new RMICacheReplicatorFactory();

Properties eventListenerProp = new Properties();
eventListenerProp.put("replicatePuts","true");
eventListenerProp.put("replicateAsynchronously","true");
eventListenerProp.put("replicateUpdates","true");
eventListenerProp.put("replicateUpdatesViaCopy","true");
eventListenerProp.put("replicateRemovals","true");
cacheEventListener = cacheReplicator.createCacheEventListener(eventListenerProp);

cache.getCacheEventNotificationService().registerListener(cacheEventListener);
cacheManager.addCache(cache);