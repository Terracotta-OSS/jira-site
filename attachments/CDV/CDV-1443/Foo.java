import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

public class Foo {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface MyAnnotation {
		//
	}

	@MyAnnotation
	public synchronized void foo() {
		//
	}

	public static void main(String[] args) throws Exception {
		Method method = Foo.class.getMethod("foo");

		Annotation[] annotations = method.getAnnotations();

		if (annotations == null || annotations.length == 0) {
			throw new AssertionError();
		}

	}

}
