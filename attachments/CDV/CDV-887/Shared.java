import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class Shared {
	@Target(ElementType.FIELD)
	public @interface TCRoot {
	}

	@TCRoot
	private static Shared s_instance = new Shared();
	
	private int m_counter = 0;
	
	public synchronized int increment() {
		return ++m_counter;
	}

	public static void main(String [] args) {
		System.out.println("counter is: " + s_instance.increment());
	}
}
