/*
 *	Scratchpad.
 */

package tc;

import java.util.concurrent.BlockingQueue;

import org.terracotta.api.TerracottaClient;
import org.terracotta.cluster.ClusterEvent;
import org.terracotta.cluster.ClusterListener;


/**
 * Simple test case demonstrating an unhonoured interrupt on the nested {@link BlockingQueue} and
 * consequent blocking on the join() if the L2 server is offline.
 * 
 * @author Nicolas Estrada.
 */

public class BlockingQueueHaltTest implements Runnable {

	/**
	 * {@link TerracottaClient}.
	 */
	protected static TerracottaClient	client;

	// Queue from which to read
	private final BlockingQueue<String>	queue;


	/**
	 * Constructor.
	 */

	public BlockingQueueHaltTest(BlockingQueue<String> queue) {
		this.queue = queue;
	}


	/*
	 * @see java.lang.Runnable#run()
	 */

	@Override
	public void run() {

		Thread driver = Thread.currentThread();

		while (!driver.isInterrupted()) {

			String msg = null;

			try {
				msg = queue.take();
			} catch (InterruptedException ie) {
				// Preserve interrupt status
				driver.interrupt();
			}

			if (msg != null) System.out.println("Taken message: " + msg);

		}
	}


	// Cluster listener implementation
	private static class ClusterListenerImpl implements ClusterListener {

		protected volatile Thread	queueTaker;


		protected ClusterListenerImpl() {}


		@Override
		public void nodeJoined(ClusterEvent event) {}


		@Override
		public void nodeLeft(ClusterEvent event) {}


		@Override
		public void operationsDisabled(ClusterEvent event) {

			System.out.println("BlockingQueueHaltTest.ClusterListenerImpl.operationsDisabled()");

			new Thread(new Runnable() {

				@Override
				public void run() {

					synchronized (BlockingQueueHaltTest.class) {

						if (queueTaker != null) {

							System.out.println("Stopping queue taker" + queueTaker);
							Thread oldTaker = queueTaker;

							queueTaker.interrupt();

							try {
								queueTaker.join();
							} catch (InterruptedException ie) {
								// Preserve interrupt status
								Thread.currentThread().interrupt();
							}

							queueTaker = null;

							System.out.println(oldTaker + " queue taker stopped");

						}
					}
				}
			}, "Stopper").start();

		}


		@Override
		public void operationsEnabled(ClusterEvent event) {

			System.out.println("BlockingQueueHaltTest.ClusterListenerImpl.operationsEnabled()");

			new Thread(new Runnable() {

				@Override
				public void run() {

					synchronized (BlockingQueueHaltTest.class) {

						if (queueTaker == null) {

							System.out.println("Starting queue taker");

							String name = BlockingQueueHaltTest.class.getSimpleName();
							BlockingQueue<String> queue = client.getToolkit()
									.getBlockingQueue(name);

							queueTaker = new Thread(new BlockingQueueHaltTest(queue), name);
							queueTaker.start();

							System.out.println(queueTaker + " queue taker started");

						}
					}
				}
			}, "Starter").start();

		}
	}


	/*
	 * Main test harness.
	 */

	public static void main(String[] args) {

		client = new TerracottaClient("localhost:9510");

		client.getToolkit().getClusterInfo().addClusterListener(new ClusterListenerImpl());

		try {
			Thread.currentThread().join();
		} catch (InterruptedException ignored) {}

	}
}
