package tutorial;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptibleLocking2 implements Runnable {
	public static final InterruptibleLocking2 instance = new InterruptibleLocking2();
	private Lock lock = new ReentrantLock(); // new InterruptibleLock();
	private volatile transient boolean giveupLock;

	private static void print(String message) {
		System.out.println(new Date() + ": " + Thread.currentThread().getName() + ": " + message);
	}

	public void run() {
		print("I'm waiting for the lock, interruptibly... ");
		try {
			lock.lockInterruptibly();
			try {
				giveupLock = false;
				print("Acquired lock. Sleeping 20 sec.");
				try {
					Thread.sleep(20000);
				}
				catch (InterruptedException ie) {
					print("sleep interrupted.");
				}
			}
			finally {
				print("Releasing lock...");
				lock.unlock();
			}
		}
		catch (InterruptedException ie) {
			print("Interrupted while waiting for lock");
		}
		print("Finished");
	}

	public static void main(String[] args) throws InterruptedException {
		print("Main started");
		instance.giveupLock = true;
		Thread t1 = new Thread(instance, "T1");
		t1.start();
		Thread.sleep(1000);
		if (instance.giveupLock) {
			print("interrupting T1");
			t1.interrupt();
		}
		print("Main waiting for all threads to finish");
	}
}
