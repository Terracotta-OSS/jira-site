import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class TestTCLock implements Runnable {

	private ReentrantLock lock;
	private Condition condition;
	private String name;
	
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		Condition waiter = lock.newCondition();
		for (int i = 0; i < 10; i++) {
			TestTCLock test = new TestTCLock(lock, condition, "T" + i);
			Thread t = new Thread(test);
			t.start();
		}
		lock.lock();
		try {
			while (true) {
				System.out.println("Sending signal");
				condition.signal();
				try {
					waiter.awaitNanos(2000000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}

	public TestTCLock(ReentrantLock lock, Condition condition, String name) {
		this.lock = lock;
		this.condition = condition;
		this.name = name;
	}

	@Override
	public void run() {
		lock.lock();
		try {
			while (true) {
				condition.awaitUninterruptibly();
				System.out.println("  " + name + " got signal");
			}
		} finally {
			lock.unlock();
		}
	}

}
