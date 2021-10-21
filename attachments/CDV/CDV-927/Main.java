import java.util.concurrent.*;
 
 public class Main {
    public static final Main instance = new Main();
    private static final CyclicBarrier barrier = new CyclicBarrier(5);
 
 
    public void run() throws BrokenBarrierException, InterruptedException {
       System.out.println("Waiters: " + barrier.getNumberWaiting());
       System.out.println("Waiting for other nodes to join...");
       int cycles = 1000;   //get a metric every so many cycles
       int count = 0;
       long startTime = System.currentTimeMillis();
       while (true) {
          barrier.await();
          if (count == 0) { System.out.println("Started..."); }
          count++;
          if (count % cycles == 0) {
             long currentTime = System.currentTimeMillis();
             double elapsedTime = .001 * (currentTime - startTime);
             double hertz = cycles / elapsedTime;
             System.out.println("Speed (cycles/second) : " + hertz);
             startTime = System.currentTimeMillis();
          }
       }
    }
 
 
   
 
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException
    {
       instance.run();
    }
 }
 
