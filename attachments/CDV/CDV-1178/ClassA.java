public class ClassA implements TerracottaOnLoadable {
   private static final ClassA s_root = new ClassA();

   private transient boolean m_bShouldBeTrue = true;

   public String toString() {
      return '[' + getClass().getName() + '(' + m_bShouldBeTrue + ")]";
   }

   public void onTerracottaLoad() {
      m_bShouldBeTrue = true;
      System.out.println("onTCLoad() invoked on " + this);
   }

   public void test() throws Exception {
      if (! m_bShouldBeTrue) {
         throw new Exception("transient was not repaired for " + this);
      }
      System.out.println("test passed on " + this);
   }

   public static void main(String [] args) throws Exception {
      // This test will always succeed
      s_root.test();
   }
}
