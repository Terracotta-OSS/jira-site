---

title: "Need to handle Thread interrupt and InterruptedException during lock aquire and wait()"
layout: issue
tags: 
permalink: /browse/CDV-92

issue_key: CDV-92
issue_numeric_sort_key: 92
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "3 Minor"
components: "DSO:L1"
labels: 
assignee: "hhuynh"
reporter: "asi"
votes:  0
watchers: 0

created: "2006-11-11T20:26:56.000-0500"
updated: "2007-06-04T13:58:10.000-0400"
resolved: "2007-04-26T18:12:40.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

Currently we differ from the standard JDK behavior wrt Thread.interrupt() in the following ways.

1) when a Thread is interrupted we may throw TCRuntimeException() wrapped around InterruptedException even during lockAquire(). The correct behavior is to set the interrupted flag and not throw interrupted exception.
2) when a Thread is interrupted we may throw TCRuntimeException() wrapped around InterruptedException during wait(). The correct behavor is to throw interrupted exception AFTER the lock is granted.

This difference in behavior causes failures in FutureTaskTest occassionally. What happen is that when a FutureTask is shared and if one node try to cancel the task while the other node tries to run a task. Since a cancellation is done by interrupting a thread, if the interrupt happen when the FutureTask is requesting a dso lock, it will throw TCRuntimeException.

This also affects Reentrant lock behavior and fix this will fix that too.

</div>

## Comments


{:.comment-heading}
### **Antonio Si** <span class="date">2007-01-26</span>

<div markdown="1" class="comment">

1. The interrupt during locking is fixed. When an interrupt occurs while waiting for a lock, it will contiune to wait for the lock and
     when the lock is acqured, it will do a self interrupt.

2.  The second issue in which an interrupt occurs during a wait() will involve more work. If the lock is greedily awarded, it needs to be
      moved to the pending state locally. If the lock is not greedily awarded, it needs to be moved to the pending state and notify the
      server to move the lock to the pending state.

      This is still in progress.



</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-01-26</span>

<div markdown="1" class="comment">

There are other places that we need to handle Thread.interrupt() properly too. Except for lock manager where we deal with it as mentioned above, the rest of the places we should just set the interrupted flag if we were ever interrupted in the TC code base in user thread still completing the task we were in the  middle of doing. That way we are truly transparent. Two places where we have to handle this that comes to my mind are

1) RemoteObjectManagerImpl
2) RemoteTransactionManagerImpl (TransactionSequencer)

There may be other places too.

</div>


{:.comment-heading}
### **Antonio Si** <span class="date">2007-04-26</span>

<div markdown="1" class="comment">

This is fixed. There is also a InterruptTest for it.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-05-30</span>

<div markdown="1" class="comment">

verify that tests pass and close out

</div>



{% endraw %}
