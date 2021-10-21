package test;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Node1 {
  LockContainer container = new LockContainer();
  private ReentrantLock lock = new ReentrantLock(true);
  Condition cond = lock.newCondition();
  public static void main(String args[]) throws Exception {
    new Node1().TestLockWait();
  }

  public void TestLockWait() throws Exception {
    /** Thread prints the interrupted status. Incase of JDK it will print true, in case of TC it will print false **/
    Thread thread1 = new Thread("Thread1") {
      public void run() {
        try {
          lock.lock();
          System.out.println(getName() + " acquired lock");
        }catch(Exception e){
          e.printStackTrace();
        }
      }
    };

    Thread thread2 = new Thread("Thread2") {
      public void run() {
        try {
          lock.lock();
          System.out.println(getName() + " acquired lock");
        }catch(Exception e){
          e.printStackTrace();
        }
      }
    };

    thread1.start();
    Thread.sleep(1000);
    synchronized(container){
      container.lock = lock;
    }
    thread2.start();
  }
}

