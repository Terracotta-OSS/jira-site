import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import junit.framework.Assert;
import junit.framework.TestCase;


public class SimpleRWLockTest extends TestCase {
	// this is a Terracotta root
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void test() throws InterruptedException {
		boolean res = lock.readLock().tryLock(10, TimeUnit.MILLISECONDS);
		Assert.assertTrue(res);
		
		res = lock.readLock().tryLock(10, TimeUnit.MILLISECONDS);
		Assert.assertTrue(res);
		
		res = lock.writeLock().tryLock(10, TimeUnit.MILLISECONDS);
		Assert.assertFalse(res);
	}
}
