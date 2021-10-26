---

title: "Issues with ReentrantLock"
layout: issue
tags: 
permalink: /browse/CDV-37

issue_key: CDV-37
issue_numeric_sort_key: 37
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "hhuynh"
reporter: "tgautier"
votes:  2
watchers: 2

created: "2006-12-21T13:00:42.000-0500"
updated: "2007-06-06T13:07:34.000-0400"
resolved: "2007-05-01T19:35:21.000-0400"

---



{% raw %}


## Attachments

* <em></em> (304.000, text/x-java) [LockContainer.java](/attachments/CDV/CDV-37/LockContainer.java)

* <em></em> (919.000, text/plain) [ManagerUtil-monitorEnter-fix.patch](/attachments/CDV/CDV-37/ManagerUtil-monitorEnter-fix.patch)

* <em></em> (1.000 k, text/x-java) [Node1.java](/attachments/CDV/CDV-37/Node1.java)

* <em></em> (861.000, text/x-java) [Node2.java](/attachments/CDV/CDV-37/Node2.java)

* <em></em> (3.000 k, text/plain) [ReentrantLock-JDK1.5-mismatch.patch](/attachments/CDV/CDV-37/ReentrantLock-JDK1.5-mismatch.patch)

* <em></em> (611.000, text/xml) [tc-config.xml](/attachments/CDV/CDV-37/tc-config.xml)




## Description

<div markdown="1" class="description">

Part of the 1.5 java.util.concurrent classes.

</div>

## Comments


{:.comment-heading}
### **njain** <span class="date">2007-01-05</span>

<div markdown="1" class="comment">

I was analyzing the current implementation if ReentrantLock and there seems to be some inconsistencies between terracotta's implementation of ReentrantLock and JDK's implementation -

i) The 'tryLock' implementation of JDK throws InterruptedException, if thread is interrupted while waiting, while Terracotta's implementation of same method does not care about interrupted status

ii) In JDK's await() implementation, if a thread is interrupted after wait() is completed and before lock is acquired, thread interrupted status is set to 'true' before returning. If thread is interrupted while waiting, an InterrutedException is thrown. The TC implementation, always throws the exception if thread is interrupted in await() method.

iii) JDK's awaitUninterruptibly implementation maintains the thread interrupted status before the method returns, while TC implementation clears thread interrupted status before returning.

iv) If ReentrantLock object instance is not shared in cluster, once a thread has acquired the lock, all other thread trying to acquire the lock at the same time will block at 'lock.wait()' statement inside 'ReentrantLock.lock()'  method in TC implementation. This will lead to incorrect statistics about number of thread waiting and waiting thread info. Waiting threads will not be added in 'waitingQueue' and 'numQueued' will not be incremented.

If anaylsis looks correct, I can provide a patch. 

If no one is working on this issue, I can take up the job of supporting ReentrantReadWrite lock.

Nitin

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-05</span>

<div markdown="1" class="comment">

Definitely some known issues with re-entrant locks as far as supported behavior. I believe we basically did enough to support the higher level classes (i.e. blocking q and current hashmap). The guys who usually work on this stuff on are vaca till monday so I'll have one of them work with you on it then. In the mean time whipping up a few tests that expose the issues is a great way to start.

</div>


{:.comment-heading}
### **njain** <span class="date">2007-01-08</span>

<div markdown="1" class="comment">

I could not produce issue number 1). I did a incorrect analysis. for other issues please find java programs to reproduce. I did not see any option to attach the files. So Pasting the code of three java classes here. One class file is created for each issue.

Issue2 -
----------

package test;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Issue2 \{
  private ReentrantLock lock = new ReentrantLock(true);
  Condition cond = lock.newCondition();
  public static void main(String args[]) throws Exception \{
    new Issue2().TestLockWait();
  \}

  public void TestLockWait() throws Exception \{
    /** This thread prints the interrupted exception, if thread is interrupted while waiting 
     * The thread will end without any exception in JDK while exception trace will be printed in TC**/

    Thread thread1 = new Thread("Thread1") {
      public void run() {
        lock.lock();
        try {
          cond.await();
        }catch(Exception e){
          System.out.println(getName() + "interrupted while waiting");
          e.printStackTrace();
        }
      }
    };



    thread1.start();
    /**
     * 1. Wait so that Thread1 acquires the lock first 
     * 2. acquire the lock,  
     * 3. send signal to Thread1 so that it comes out of wait and starts the procedure of acquiring lock 
     * 4. Interrupt Thread1
     * 5. unlock, so that Thread1 grabs it 
     **/
    Thread.sleep(1000);
    lock.lock();
    cond.signalAll();
    thread1.interrupt();
    lock.unlock();
  \}
\}

Issue 3 -
------------

package test;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Issue3 \{
  private ReentrantLock lock = new ReentrantLock(true);
  Condition cond = lock.newCondition();
  public static void main(String args[]) throws Exception \{
    new Issue3().TestLockWait();
  \}

  public void TestLockWait() throws Exception \{
    /** This thread prints the interrupted status. Incase of JDK it will print true, in case of TC it will print false **/

    Thread thread1 = new Thread("Thread1") {
      public void run() {
        lock.lock();
        cond.awaitUninterruptibly();
        System.out.println(getName() + " interrupted status " + Thread.interrupted());
      }
    };



    thread1.start();
    /**
     * 1. Wait so that Thread1 acquires the lock first 
     * 2. acquire the lock,  
     * 3. Interrupt Thread1
     * 4. send signal to Thread1 so that it comes out of wait and starts the procedure of acquiring lock 
     * 5. unlock, so that Thread1 grabs it 
     **/
    Thread.sleep(1000);
    lock.lock();
    thread1.interrupt();
    cond.signalAll();
    lock.unlock();
  \}
\}

Issue 4 -
-----------

package test;

import java.util.concurrent.locks.ReentrantLock;

public class Issue4 \{
  private ReentrantLock lock = new ReentrantLock(true);

  public static void main(String args[]) throws Exception \{
    new Issue4().TestLockWait();
  \}

  public void TestLockWait() throws Exception \{
    /** one of Thread1 and Thread2 acquires the lock, while other waits in the queue
     * JDK's implementation prints the waiting thread as 1, while TC implementation prints 0.
     */
    
    new Thread("Thread1") {
      public void run() {
        lock.lock();
      }
    }.start();
    

    new Thread("Thread2") {
      public void run() {
        lock.lock();
      }
    }.start();

    /** sleep for some time so that both Thread1 and Thread2 are done with their run method execution**/
    Thread.sleep(1000);
    
    System.out.println("Number of waiting thread = " + lock.getQueueLength());
  \}
\}



</div>


{:.comment-heading}
### **njain** <span class="date">2007-01-11</span>

<div markdown="1" class="comment">

What is the procedure of submitting a patch

</div>


{:.comment-heading}
### **jvoegele** <span class="date">2007-01-11</span>

<div markdown="1" class="comment">

Nitin, there is a wiki page that should give you the information you need:

    http://www.terracotta.org/confluence/display/orgsite/Contributors

Please let me know if you need any further details.

</div>


{:.comment-heading}
### **njain** <span class="date">2007-01-12</span>

<div markdown="1" class="comment">

I do not see any option to attach the file in bug.

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-12</span>

<div markdown="1" class="comment">

For me it's on the bottom left under operations. Might be a permissions problem. We'll look into it.

</div>


{:.comment-heading}
### **njain** <span class="date">2007-01-15</span>

<div markdown="1" class="comment">

Attached diff files contains the fixes of above mentioned 3 issues. 

ReentrantLock had one more bug -

The RenentrantLock.lock() method of JDK maintains the interrupted status of thread and does not throw any exception, if a thread is interrupted while waiting inside lock() method. While TC RenentrantLock throws an exception if thread is interrupted during wait.

This issue is also fixed in the attached patch.

To fix issue iv), I have commented lock.wait() in ReentrantLock.lock() method. I did not see any reason for waiting on this lock object. Suggest, if it breaks any thing.

the diff is take against trunk.

</div>


{:.comment-heading}
### **Antonio Si** <span class="date">2007-01-15</span>

<div markdown="1" class="comment">

We actually need the commented code in the lock() method. Consider this scenario where we have 2 nodes.

In node 1: We have a ReentrantLock in an unshared state. One thread in node 1 lock the RentrantLock. Since the ReentrantLock is not shared, ManagerUtil.monitorEnter() will have no effect and node 1 will lock the ReentrantLock using UnsafeUtil.monitorEnter().
Now, another thread in node 1 may come in and make the same ReentrantLock shared.

In node 2: after the second thread of node 1 make the ReentrantLock shared, node 2 can try to lock the ReentrantLock. If we do not have the lock.wait() code segment, node 2 can enter ManagerUtil.monitorEnter(). Since the first thread of node 1 does not lock the ReentrantLock in a cluster fashion (recalling that when first thread of node 1locks the ReentrantLock, the ReentrantLock is in an unshared state), node 2 can lock the ReentrantLock in a cluster mode, which is not correct.

With the lock.wait() code, when node2 tries to lock the ReentrantLock, it will have to wait because some other node already locks it in an unshared state.

I am still reviewing the rest of the fixes.


</div>


{:.comment-heading}
### **Antonio Si** <span class="date">2007-01-17</span>

<div markdown="1" class="comment">

The fix for issues ii and iii described above seems fine to me. Thanks.

To fix the interrupt status problem of the lock() method, I think it is probably better to fix it at the implementation of ManagerUtil.monitorEnter() rather than at the ReentrantLock level as ManagerUtil.monitorEnter() is also used in the regular synchronized() block. By fixing it in the ManagerUtil.monitorEnter(), it will work in both synchronized() block and ReentrantLock.

In a regular synchronized() block, if a thread is interrupted while it is blocking at the JVM monitorEnter instruction, it will maintain the interrupt status, but will continue to block until it grab the monitor. So, it is similar to ReentrantLock.lock(). So, if we fix it at the ManagerUtil.monitorEnter to respect this behavior, it will be respected in both synchronized() and ReentrantLock.lock().


</div>


{:.comment-heading}
### **njain** <span class="date">2007-01-18</span>

<div markdown="1" class="comment">

Thanks for your comments. I will work on fix as suggested by you.

Regarding Issue no 4, I think following should occur

If lock.wait() commented
1. Thread1 in Node1 locks ReentrantLock in unshared mode.
2. Thread2 in Node1 tries to lock ReentrantLock in shared mode. It will enter inside MangerUtil.monitorEnter() and will be successful as ReentrantLock is still unlocked in shared mode
3. Thread2 will block at UnsafeUtil.monitorEnter, till Thread1 release the lock
4. Thread3 at Node2 tries to lock ReentrantLock in shared mode and blocks at ManagerUtil.monitorEnter as Thread2 already locked it.

Eventually both Thread2 and Thread3 will block, till Thread1 release the lock

If lock.wait is present 
1. Thread1 in Node1 locks ReentrantLock in unshared mode.
2. Thread2 in Node1 tries to lock ReentrantLock in shared mode. It blocks at lock.wait() till Thread1 releases the lock.
3. Thread3 at Node2 tries to lock ReentrantLock in shared mode, 
	a) successfully passes through lock.wait() as it is a JVM local object (unshared)
	b) enters ManagerUtil.monitorEnter and successfully acquires the lock as ReentrantLock is still not locked in shared mode
	c) passes through UnsafeUtil.monitorEnter as well.
Eventually Thread3 acquires the lock, which is incorrect.

Please find attached test files Node1, Node2, LockContainer and tc-config.xml to validate the understanding. Node1 (consists of Thread1 and Thread2) and Node2 (consists of Thread3) will run in different JVMs.


</div>


{:.comment-heading}
### **Antonio Si** <span class="date">2007-01-19</span>

<div markdown="1" class="comment">

Thanks for your comments on issue #4. I have the scenario mixed up. Consider this scenario if lock.wait() is commented out:

1. Thread 1 in node 1 locks ReentrantLock in unshared mode. So, ManagerUtil.monitorEnter() has no effect.
2. Thread 2 in node 2 makes the ReentrantLock shareable and locks the ReentrantLock. Now, since the ReentrantLock is shared,
     it executes ManagerUtil.monitorEnter() successfully since thread 1 locks the ReentrantLock in unshared state. Now, when thread
    2 tries executing UnsafeUtil.monitorEnter(), it is blocked.
3. Now, thread 1 tries to lock the ReentrantLock again. Now, since the ReentrantLock is shared, thread 1 tries executing
    ManagerUtil.monitorEnter(). Now, since thread 2 has executed ManagerUtil.monitorEnter() successfully, thread 1 is blocked at
    ManagerUtil.monitorEnter(). 

A deadlock has occurred. That is why we introduced a lock.wait() before ManagerUtil.monitorEnter() to block if there is another thread lock the ReentrantLock in unshared state.

</div>


{:.comment-heading}
### **njain** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

Find attached the fix for for ManagerUtil.monitorEnter. If thread is interrupted in monitorEnter, it does not exit. Instead it reenters to acquire the lock and sets the thread interrupted before returning from call

This diff is taken against trunk

Issue 4:
Your scenario is clear. But I think the issue that I mentioned still exists.
Eventaully two thread might endup acquiring the same lock in distributed environment.

Can we try to lock the ReentrantLock by Thread1 in shared mode as soon as ReentrantLock is made shared by Thread2? Thread2 will block eventually in ManagerUtil.monitorEnter and any other thread on some other machine would also block at ManagerUtil.monitorEnter in this case.

I think it can be done and this would solve all the issues metioned in this thread.



</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

Nitin,

Thanks for your patches. Regarding setting the interrupted flag in monitorEnter, that might not work since today we wrap Interrupted exception with runtime exception. Also the lock is not acquired on interrupt which is different from how the VM behaves.

I guess what Antonio ment by fixing it in monitorEnter is that this need to be fixed in LockManager outside the scope of Re-entrant lock. I am going to raise a separate JIRA task to fix interrupted state for all regular synchronized block. Fixing that should fix this particular issue for reentrant lock.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-27</span>

<div markdown="1" class="comment">

Is there anything else which needs to be done with this?

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-04-05</span>

<div markdown="1" class="comment">

Saravanan, can you summerise this issue?  It morphed into more than one I believe.
What actually remains to be done? What has been completed? 
Then assign back to Issue Review Board. Thanks

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-05-01</span>

<div markdown="1" class="comment">

The JIRA that was raised to fix Interrupted Exception is fixed now.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-05-30</span>

<div markdown="1" class="comment">

Please verify that this has been resolved

</div>



{% endraw %}
