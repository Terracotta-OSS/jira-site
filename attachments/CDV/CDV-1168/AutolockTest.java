
public class AutolockTest {
   private static final boolean ENABLE_WORKAROUND = false;

   private static final AutolockTest s_root = new AutolockTest();

   private int m_counter = 0;

   public static void main(String [] args) throws Exception {
      s_root.runMethod();
   }

   private void runMethod() {
      new Thread() {
         public void run() {
            if (ENABLE_WORKAROUND) {
               safeMethod();
            } else {
               synchronized (this) {
                  synchronized (AutolockTest.this) {
                     unsafeMethod();
                  }
               }
            }
         }
      }.start();
   }
   
   private void unsafeMethod() {
      System.out.println(++m_counter);
   }
   
   private synchronized void safeMethod() {
      unsafeMethod();
   }
}
