public class ClassB extends ClassA {
   private static final ClassB s_root = new ClassB();

   public static void main(String [] args) throws Exception {
      // This test will fail the 2nd time it's run
      s_root.test();
   }
}
