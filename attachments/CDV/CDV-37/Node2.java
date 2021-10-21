package test;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Node2 {
  LockContainer container = new LockContainer();
  private ReentrantLock lock = new ReentrantLock(true);
  Condition cond = lock.newCondition();
  public static void main(String args[]) throws Exception {
    new Node2().TestLockWait();
  }

  public void TestLockWait() throws Exception {
    /** Thread prints the interrupted status. Incase of JDK it will print true, in case of TC it will print false **/
    Thread thread1 = new Thread("Thread3") {
      public void run() {
        try {
          container.lock.lock();
          System.out.println(getName() + " acquired lock");
        }catch(Exception e){
          e.printStackTrace();
        }
      }
    };


    thread1.start();
  }
}
