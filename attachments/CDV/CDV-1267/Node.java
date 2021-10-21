import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class Node {
   public static final boolean ENABLE_WORKAROUND = false;

   public static class Dso {
   }

   public static class Task {
      private final Method m_meth;
      private final Object [] m_args;

      public Task(Method meth, Object [] args) {
         m_meth = meth;
         m_args = args;
      }

      public void foo(Dso arg1) throws Exception {
         if (arg1 == null) {
            throw new Exception("DSO field not faulted in!");
         }
         System.out.println("arg1 = " + arg1);
      }

      public synchronized void run() throws Exception {
         if (ENABLE_WORKAROUND) {
            // Touch array to force contents to be paged in
            for (int i = m_args.length - 1 ; i >= 0 ; --i) {
               Object o = m_args[0];
            }
         }
         m_meth.invoke(this, m_args);
      }
   }

   public static final LinkedBlockingQueue<Task> RPC_QUEUE = new LinkedBlockingQueue<Task>();
   public static final AtomicInteger NODE_ID = new AtomicInteger();
      

   public static void main(String [] args) throws Exception {
      if (NODE_ID.getAndIncrement() == 0) {
         // consumer
         while (true) {
            System.out.println("Consumer waiting ...");
            Task dso = RPC_QUEUE.take();
            dso.run();
         }
      } else {
         // producer
         System.out.println("Producer writing ...");
         RPC_QUEUE.put(
               new Task(
                  Task.class.getMethod(
                     "foo",
                     new Class [] { Dso.class }),
                  new Object [] { new Dso() }
                  ));
      }
   }
}

