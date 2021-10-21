package qa;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
	// Roots
	private Map<String, String> map = new ConcurrentHashMap<String, String>();
	private Object[] array = new Object[4];

	public void run() {
		map.put("key1", "value1");
		map.put("key2", "value2");

		synchronized (array) {
			Arrays.fill(array, "filler");
			System.out.println("before: " + Arrays.asList(array));
			array = map.entrySet().toArray(array);
			System.out.println("after: " + Arrays.asList(array));
		}
	}

	public static void main(String[] args) {
		new Test().run();
	}
}
