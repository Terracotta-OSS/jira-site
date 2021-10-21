import net.sf.ehcache.loader.CacheLoader;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;

public class Test implements CacheLoader
{
   Status status = Status.STATUS_UNINITIALISED;

   public static void main(String[] args)
   {
      Cache tmpCache = new Cache("test", 4, false, false, 0, 0);
      CacheManager.getInstance().addCache(tmpCache);

      Test myCacheLoader = new Test();
      Element e;

// Calling getWithLoader twice so the lastAccessTime is set on second call
      e = tmpCache.getWithLoader("001", myCacheLoader, null);
      System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());
      e = tmpCache.getWithLoader("001", myCacheLoader, null);
      System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());

      e = tmpCache.getWithLoader("002", myCacheLoader, null);
      System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());
      e = tmpCache.getWithLoader("002", myCacheLoader, null);
      System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());

      e = tmpCache.getWithLoader("003", myCacheLoader, null);
      System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());
      e = tmpCache.getWithLoader("003", myCacheLoader, null);
      System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());

// calling getWithLoader once to demonstrate issue
      e = tmpCache.getWithLoader("004", myCacheLoader, null);
      System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());

// Element 001 is the oldest element and should be evicted,
// But Element 004 does not have the access Time set, so it is evicted instead

      e = tmpCache.getWithLoader("005", myCacheLoader, null);
      System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());

      e = tmpCache.getQuiet("001");
      if (e == null)
         System.out.println("001 is NULL");
      else
         System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());

      e = tmpCache.getQuiet("004");
      if (e == null)
         System.out.println("004 is NULL, should still be in cache!!");
      else
         System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());

      e = tmpCache.getQuiet("005");
      if (e == null)
         System.out.println("005 is NULL");
      else
         System.out.println("Element " + e.getValue() + " has lastAccessTime of " + e.getLastAccessTime());

      CacheManager.getInstance().shutdown();
   }

/********************************************/
/* Trivial loader returning Key as Object   */
/********************************************/
   public Object load(Object o) throws CacheException
   {
      return o;
   }

   public Map loadAll(Collection collection)
   {
      HashMap<Object,Object> map = new HashMap<Object,Object>();
      for (Object o : collection)
      {
         map.put(o,o);
      }
      return map;
   }

   public Object load(Object o, Object o1)
   {
      return load(o);
   }

   public Map loadAll(Collection collection, Object o)
   {
      return loadAll(collection);
   }

   public String getName()
   {
      return "MyCacheLoader";
   }

   public CacheLoader clone(Ehcache ehcache) throws CloneNotSupportedException
   {
      throw new CloneNotSupportedException("ProgramCacheLoader.clone(Ehcache) not supported");
   }

   public void init()
   {
      status = Status.STATUS_ALIVE;
   }

   public void dispose() throws CacheException
   {
      status = Status.STATUS_SHUTDOWN;
   }

   public Status getStatus()
   {
      return status;
   }
}
