import java.util.concurrent.locks.ReentrantLock;


public class Blocker extends ReentrantLock {
   public static final Blocker s_instance = new Blocker();

   public static void main(String [] args) throws Throwable {
      // Make sure instance is in locked state
      s_instance.tryLock();

      final Thread t1 = new Thread() {
         public void run() {
            try {
               System.out.println("Blocking ...");
               s_instance.lockInterruptibly();
            } catch (InterruptedException e) {
            } finally {
               System.out.println("Unblocked");
            }
         }
      };

      t1.start();
      Thread.sleep(2000);
      System.out.println("Unblocking ...");
      t1.interrupt();
      t1.join();
   }
}
