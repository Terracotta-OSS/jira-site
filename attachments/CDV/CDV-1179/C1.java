
public class C1 {
   public static final C1 ROOT = new C1();
   
   private final boolean m_f1 = true;
   private transient boolean m_f2 = true;
   
   public void repairTransient() {
      m_f2 = true;
   }
   
   public void test() throws Exception {
      System.out.println("repairing " + this);
      if (! m_f2) {
         throw new Exception("test failed for " + this);
      }
   }
   
   public static void main(String [] args) throws Exception {
      ROOT.test();
   }
}
