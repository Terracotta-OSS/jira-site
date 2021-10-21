/**
 * Code adapted from own code to show hot to use a CacheEventListener to manage cache groups.
 */
public final class EhCacheFacade { 

  private final EhCache _cache;
  private final CacheManager _cacheManager;  

  // Mapping of cache groups to the set of element keys they are mapped to:
  private final Map<String, Set<String>> _cacheGroups;

   /**
     * Factory for the {@link CacheGroupsListener}.
     */
    public static final class CacheGroupsListenerFactory extends CacheEventListenerFactory {
        public CacheEventListener createCacheEventListener(Properties properties) {
            return new CacheGroupsListener();
        }
    }

    /**
     * CacheEventListener that removes {@link CacheGroup} mappings when cache elements are removed/expired.
     * When using a patched Element class this could also handle the puts/updates!
     */
    private static final class CacheGroupsListener implements CacheEventListener {
        private Map<String, Set<String>> _cacheGroupMapping;

        public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
            removeCacheGroupMapping(element);
        }

        public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
	    // Mapping cache groups to element key is handled elsewhere because we have not patched Element to contain the cache group keys.
        }

        public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
	    // Mapping cache groups to element key is handled elsewhere because we have not patched Element to contain the cache group keys.
        }

        public void notifyElementExpired(Ehcache cache, Element element) {
            removeCacheGroupMapping(element);
        }

        public void notifyElementEvicted(Ehcache cache, Element element) {
            removeCacheGroupMapping(element);
        }

        public void notifyRemoveAll(Ehcache cache) {
            removeAllCacheGroupMappings();
        }

        public void dispose() {
            // This is also called for default config cache, so _cacheGroupMapping might in fact be unset!
            if (_cacheGroupMapping != null) {
                removeAllCacheGroupMappings();
            }
        }

        public Object clone() throws CloneNotSupportedException {
            Object clone = super.clone();
            ((CacheGroupsListener) clone)._cacheGroupMapping = null;
            return clone;
        }

        private void setCacheGroupMapping(Map<String, Set<String>> cacheGroupMapping) {
            _cacheGroupMapping = cacheGroupMapping;
        }


        private void removeAllCacheGroupMappings() {
            _cacheGroupMapping.clear();
        }

        private void removeCacheGroupMapping(Element element) {
            if (element.getObjectKey() instanceof String) {
                String key = (String) element.getObjectKey();
                for (Set<String> keys : _cacheGroupMapping.values()) {
                    keys.remove(key);
		    // Possible optimization: break loop once the key has been removed. 
		    // Okay if an element key belongs to only one cache group at a time.
                }
            }
        }
    }

    /**
     * Simplified code to create CacheManager and one BlockingCache using the above CacheGroupsListener.
     */
    public EhCacheFacade(int capacity) {
        
        // Default cache settings:
        CacheConfiguration cacheConfig = new CacheConfiguration(Cache.DEFAULT_CACHE_NAME, capacity)
                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)
                .overflowToDisk(false)
                .diskPersistent(false)
                .cacheEventListenerFactory(new CacheConfiguration.CacheEventListenerFactoryConfiguration().className(CacheGroupsListenerFactory.class.getName()));

        // CacheManager configuration:
        Configuration config = new Configuration();
        config.addDefaultCache(cacheConfig);

	// Create "normal" EhCache cache:
	_cacheManager = new CacheManager(config);
        _cacheManager.addCache(name);
        _cache = _cacheManager.getCache(name);

        // Associate the cache's CacheListener with cache group mappings (this is ugly but there seems to be no other way):
	_cacheGroups = new ConcurrentHashMap<String, Set<String>>();
        for (CacheEventListener listener : cache.getCacheEventNotificationService().getCacheEventListeners()) {
            if (listener instanceof CacheGroupsListener) {
                ((CacheGroupsListener) listener).setCacheGroupMapping(_cacheGroups);
            }
        }
  }    


  // Removed the cache facade code including the cache group mapping on cache puts/updates.
  // When using a patched Element class containing the group keys the ammping could move into the listener above.

}