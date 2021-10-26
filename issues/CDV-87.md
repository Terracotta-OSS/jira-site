---

title: "Should not throw UnlockedSharedObjectException when an object is no longer referenced by a shared object"
layout: issue
tags: 
permalink: /browse/CDV-87

issue_key: CDV-87
issue_numeric_sort_key: 87
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Reopened"
resolution: ""
priority: "2 Major"
components: "DSO:L1,DSO:L2"
labels: 
assignee: "interfaces"
reporter: "asi"
votes:  1
watchers: 2

created: "2006-05-26T01:48:01.000-0400"
updated: "2011-12-16T18:37:21.000-0500"
resolved: ""

---



{% raw %}



## Description

<div markdown="1" class="description">

Currently, once an object A is referenced by a shared object B, object A is still considered as a DSO shared object even when the reference from object B is removed and object A is not referenced by any other shared object.

If somehow, the state of object A got mutated, an UnlockedSharedObjectException is thrown.

It seems like we need a way to detect if an object is no longer referenced by any shared object and allow mutation on the object without holding a DSO lock.



</div>

## Comments


{:.comment-heading}
### **Steve Harris** <span class="date">2006-06-01</span>

<div markdown="1" class="comment">

This isn't really a bug. Once an object is shared it is shared for life. the same object could be being used on multiple JVM's and changed on multiple JVM's. It can also be removed and added back in.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2006-07-05</span>

<div markdown="1" class="comment">

moving tasks not completed in Judah to Kirkham

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2006-07-31</span>

<div markdown="1" class="comment">

Not a bug...

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2006-07-31</span>

<div markdown="1" class="comment">

It is true that the object should still be monitored for changes even if it becomes unshared. But this bug was raised to track an issue which we came across while intrumenting LinkedBlockingQueue or one of those 1.5 collections.

Basically, the code was removing some internal nodes and setting it to null to help the GC. But it was doing it outside the sync block as once it removed the references, there was no way to reach those objects. We will throw this exception in dso in those cases, as according to dso any changes to these shared object should happen with a lock. (even though the objects will only exisit in the current threads local stack)

Anyways, I dont have a good solution for this problem. But nevertheless I would like to keep this bug around just to track it.

//===================code sample to illustrate the problem====================

remove(object o) \{
   node n = head;
   synchronized(this) \{
      while(n != null && n.value != o) {
         n = n.next;
      }
      if(n != null) {
         if(n.prev != null) n.prev.next = n.next;
         if(n.next != null) n.next.prev = n.prev;
      }
   \}
  // problem code -- here --- n is not referenceable at this point. So it is ok to modify it out side the sync block. But dso will throw an exception
  n.next = null;
  n.prev = null;
  n.value = null;
\}



</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2006-07-31</span>

<div markdown="1" class="comment">

Please see my other comment.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2006-07-31</span>

<div markdown="1" class="comment">

OK we can keep it around, but moving out to a later release.

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2008-05-02</span>

<div markdown="1" class="comment">

I dont understand why LBQ threw an exception since its a logical structure. Other than that, I dont know if we can figure that this object is not reachable that easily.

Anyways someone from transparency team should at the least evaluate why LBQ threw that exception.

cheers,
Saro

</div>


{:.comment-heading}
### **Andrei Costescu** <span class="date">2009-10-27</span>

<div markdown="1" class="comment">

Issue should be solved to avoid modifying business logic just in order to use terracotta. I have a shared work queue that contains objects created on the local stack. One client will read&remove all objects from that queue from time to time (thus remaining the only one holding a reference to those objects) inside a shared lock. Then it starts using those objects outside any lock, to avoid holding the lock while doing some of the work. This shouldn't result in exceptions, as the objects are no longer accessible from roots and no other clients have them.

Regards,
Andrei

</div>



{% endraw %}
