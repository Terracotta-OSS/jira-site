
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import org.junit.Before;
import org.junit.Test;
import org.terracotta.api.ClusteringToolkit;

public class TerracottaTest {
	private ClusteringToolkit toolkit;
	private org.terracotta.api.TerracottaClient terracottaClient;

	@Before
	public void setUp() {
		terracottaClient = new org.terracotta.api.TerracottaClient("localhost:9510");
		toolkit = terracottaClient.getToolkit();
	}

	@Test
	public void testBlockingQueueToArray() {
		final String queueName = "queue";
		final byte[] bytes = new byte[0];

		BlockingQueue<byte[]> blockingQueue = toolkit.getBlockingQueue(queueName);

		blockingQueue.add(bytes);

		Object[] array = blockingQueue.toArray();
		for (Object object : array) {
			byte[] byteArray = (byte[]) object;
		}

		//check if it works when fetching the queue again
		blockingQueue = toolkit.getBlockingQueue(queueName);
		blockingQueue.add(bytes);
		array = blockingQueue.toArray();
		for (Object object : array) {
			byte[] byteArray = (byte[]) object;
		}
	}

	@Test
	public void testBlockingQueueToArrayNoneDefaultCapacity() {
		final String queueName = "queueLowCapacity";
		final byte[] bytes = new byte[0];

		final BlockingQueue<byte[]> blockingQueue = toolkit.getBlockingQueue(queueName, 10);

		blockingQueue.add(bytes);

		final Object[] array = blockingQueue.toArray();
		for (Object object : array) {
			byte[] byteArray = (byte[]) object;
		}
	}

	@Test
	public void testBlockingQueueIterator() {
		final String queueName = "queue";
		final byte[] bytes = new byte[0];

		final BlockingQueue<byte[]> blockingQueue = toolkit.getBlockingQueue(queueName);

		blockingQueue.add(bytes);

		final Iterator<byte[]> iterator = blockingQueue.iterator();
		while (iterator.hasNext()) {
			byte[] byteArray = iterator.next();
		}
	}

	@Test
	public void testBlockingQueuePeek() {
		final String queueName = "queue";
		final byte[] bytes = new byte[0];

		final BlockingQueue<byte[]> blockingQueue = toolkit.getBlockingQueue(queueName);

		blockingQueue.add(bytes);

		final byte[] head = blockingQueue.peek();
	}

	@Test
	public void testBlockingQueuePoll() {
		final String queueName = "queue";
		final byte[] bytes = new byte[0];

		final BlockingQueue<byte[]> blockingQueue = toolkit.getBlockingQueue(queueName);

		blockingQueue.add(bytes);

		final byte[] head = blockingQueue.poll();
	}

	@Test
	public void testBlockingQueueTake() throws InterruptedException {
		final String queueName = "queue";
		final byte[] bytes = new byte[0];

		final BlockingQueue<byte[]> blockingQueue = toolkit.getBlockingQueue(queueName);

		blockingQueue.add(bytes);

		final byte[] head = blockingQueue.take();
	}
}
