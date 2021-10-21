import com.tc.aspectwerkz.reflect.ClassInfo;
import com.tc.aspectwerkz.reflect.FieldInfo;
import com.tc.aspectwerkz.reflect.impl.asm.AsmClassInfo;
import com.tc.aspectwerkz.reflect.impl.java.JavaClassInfo;

public class Test {

	public static void main(String[] args) {
		ClassInfo classInfo;

		classInfo = AsmClassInfo.getClassInfo(A.class.getName(), ClassLoader
				.getSystemClassLoader());
		print("asm", classInfo);

		classInfo = JavaClassInfo.getClassInfo(A.class);
		print("java", classInfo);
	}

	private static void print(String type, ClassInfo classInfo) {
		FieldInfo[] fields = classInfo.getFields();
		for (int i = 0; i < fields.length; i++) {
			FieldInfo fieldInfo = fields[i];
			System.err.println(type + ": " + fieldInfo.getName());
		}
	}
}

class A {
	Object AV;
	Object B7;
}
