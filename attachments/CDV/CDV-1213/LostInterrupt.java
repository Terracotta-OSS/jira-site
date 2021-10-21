import java.util.concurrent.LinkedBlockingQueue;


public class LostInterrupt {
   private final static LinkedBlockingQueue s_root = new LinkedBlockingQueue();

   public static void main(String [] args) throws Exception {
      final Thread t = new Thread() {
         public void run() {
            try {
               s_root.take();
               System.out.println("Done");
            } catch (InterruptedException e) {
               System.out.println("Interrupted");
            }
         }
      };
      t.start();

      for (int i = 5 ; i > 1 ; --i) {
         System.out.println(Integer.toString(i));
         Thread.sleep(1000);
      }

      System.out.println("Interrupting ...");
      t.interrupt();
      t.join(3000);

      if (t.isAlive()) {
         throw new Exception("interrupt() was lost.  test failed");
      }

      System.out.println("test passed");
   }
}
