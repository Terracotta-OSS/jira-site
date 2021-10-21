
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.concurrent.LockType;
import net.sf.ehcache.concurrent.Sync;
import net.sf.ehcache.constructs.blocking.BlockingCache;

/**
 *
 * @author Chris Dennis
 */
public class PureWriteLockingBlockingCache extends BlockingCache {

  public PureWriteLockingBlockingCache(final Ehcache cache, int numberOfStripes) throws CacheException {
    super(cache, numberOfStripes);
  }

  public PureWriteLockingBlockingCache(final Ehcache cache) {
    super(cache);
  }

  @Override
  protected Sync getLockForKey(final Object key) {
    return new PureWriteLockingSync(super.getLockForKey(key));
  }

  static class PureWriteLockingSync implements Sync {

    private final Sync lock;

    private PureWriteLockingSync(Sync lock) {
      this.lock = lock;
    }

    @Override
    public void lock(LockType type) {
      lock.lock(LockType.WRITE);
    }

    @Override
    public boolean tryLock(LockType type, long msec) throws InterruptedException {
      return lock.tryLock(LockType.WRITE, msec);
    }

    @Override
    public void unlock(LockType type) {
      lock.unlock(LockType.WRITE);
    }

    @Override
    public boolean isHeldByCurrentThread(LockType type) {
      return lock.isHeldByCurrentThread(LockType.WRITE);
    }
  }
}
