import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Lock {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread() {
				@Override
				public void run() {
					while (true) {
						Object o = Foo.get(System.currentTimeMillis() + "");
						if (o == this) {
							System.err.println("Really?");
						}
					}
				}
			}.start();
		}
	}

	static class Foo {
		static final Map map = new HashMap();

		static Object get(Object key) {
			synchronized (map) {
				return map.get(key);
			}

		}
	}
}
