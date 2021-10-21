---

title: "Cluster Transactions (JTA)"
layout: issue
tags: 
permalink: /browse/CDV-9

issue_key: CDV-9
issue_numeric_sort_key: 9
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "In Progress"
resolution: ""
priority: "3 Minor"
components: "DSO:L2"
labels: 
assignee: "prodmgmt"
reporter: "drb"
votes:  2
watchers: 6

created: "2006-11-30T18:52:36.000-0500"
updated: "2010-03-19T18:58:56.000-0400"
resolved: ""

---



{% raw %}


## Attachments
  
* <em>jhartley</em> (26.000 k, image/png) [DSO+and+Frameworks.png](/attachments/CDV/CDV-9/DSO+and+Frameworks.png)
  



## Description

<div markdown="1" class="description">

Cluster Transactions (JTA)

Clustering transactions is not a perfect fit for DSO. However, JTA and other such technologies that are not great use cases for DSO are used in many application frameworks popular with developers. Staying within the DSO sweet spot keeps us focused and helps ensure DSO is only used where it will be successful. Expanding DSO capabilities outside of the sweet spot potentially makes life much easier for customers using application frameworks but adds significant technical challenges to DSO engineering.

See attached DSO and Frameworks.png

Two fundamental approaches to handling transations are to use an object graph copy or use object history. An object history implementation will better handle edge cases and is our preferred solution. In the meantime, it would be possible to implement an AOP solution using AspectJ.


Object Graph Copy

At the start of a JTA transaction, DSO makes a copy of the object graph for the transaction. The transaction then works on the copy, and the copy is made live if the transaction completes successfully. If the transaction fails, DSO just discards the copy. Also, if DSO detects that someone else has modified anything in the object graph, DSO throws an exception and transaction fails.

This optimistic locking will in some cases provide substantial performance gains over regular locking, but this option should be configured by the user.

</div>

## Comments


{:.comment-heading}
### **njain** <span class="date">2007-01-10</span>

<div markdown="1" class="comment">

I have following opinion. When we are talking about transaction, it is well understood that first all the locks will be acquired on the objects (or their counter parts, which are lock objects in case of TC) as and when they are accessed. Locks will be released only at the end of the transaction. While using transaction, applications as such do not define when to acquire and release the locks. It is transaction isolation level that governs locking behavior. So if we are talking about only transactional locks, I think reordering should not come into picture as application is controlling the locks.

   Most likely use case of transactional behavior that I am assuming is that it will be used for in memory data base entities. In such application transactional semantic should match with the database transactional semantic. I believe above described locking behavior should match with that. 

    If any dead lock occurs, I think same should be case at database level as well (if a transactional database is used instead). Only difference would be where it occurs.

    If you take this point, then what I am concerned about is the interleaving of java synchronized locks and transactional locks.

    Not sure if I make sense.

</div>


{:.comment-heading}
### **njain** <span class="date">2007-01-11</span>

<div markdown="1" class="comment">

I suggest following approach to the pessimistic transaction -

i) Consider an application that supports only distributed transactional objects (e.g hibernate with JBossCahe). Such application guarantees isolation level semantic for pojos accessed inside transaction, while all other object locking behavior (the lock acquired by java synchronized keyword) remains local to that machine. This application will have two types of locks

    a) Lock objects used by distributed transactional framework that provides read/write locking semantic. Application logic does not specify where to acquire/release these locks. Transaction boundary and isolation level governs when to acquire/release a lock.

    b) objects are themselves locked by JVM monitorEnter instruction. These objects could be transactional pojos or any other application object. Application logic governs when to acquire and release these locks

these two type of locks do not interfere with each other and acquired and released without any knowledge of other type of lock.

ii) In terracotta, you are extending the semantic of object locking (monitorEnter, wait, notify etc.) across JVM. If terracotta supports transactional pojo's there would still be two types of locks

    a) Lock objects used by distributed transactional framework that provides read/write locking semantic. Application logic does not specify where to acquire/release these locks. Transaction boundary and isolation level governs when to acquire/release a lock.

    b) Lock objects used by terracotta to support distributed semantic (monitorEnter, wait etc.). These locks can be acquired on transactional pojos or any other application object. Application logic governs when to acquire and release these locks


Again to keep consistent with JVM semantic of local locks not shared with transactional locks, a) and b) should not interfere with each other. One type of locks should be released independent of another type of locks.

If we take this approach, we are not causing any lock reordering for b) type of locks. For a) types of lock behavior is expected and we would be supporting what actually is required for transactional pojos

Terracotta will use two locks for each objects. One lock will be managed by new JTATransaction manager and another lock will be used by existing ClientTransactionManager. These two locks will be managed independently

Suggest, what you think?

</div>


{:.comment-heading}
### **orion** <span class="date">2008-06-27</span>

<div markdown="1" class="comment">



Begin forwarded message:

> From: forums@terracottatech.com
> Date: June 25, 2008 1:18:58 PM PDT
> To: orion@terracotta.org
> Subject: [Terracotta] New Topic: Watching CDV-9? Published Roadmap?
>
> <html>
> <head>
> <base href="http://forums.terracotta.org/forums/">
> <style type="text/css">@import url( http://forums.terracotta.org/forums//templates/default/styles/style.css 
>  );</style>
> </head>
> <body>
> I would like to watch CDV-9 in the JIRA issue tracker.  Is that  
> possible?  I am very interested in this feature, and very anxious  
> for it to be complete.
> <br/>
> <br/> On a related note, is the roadmap published somewhere?  I have  
> tried searching for it, but to no avail.
> <br/>
> <br/> Thanks in advance!
> <br/>
> <br/> Steve
> <br>
> ---------<br>
> Read this topic online: http://forums.terracotta.org/forums/posts/list/1176.page#7187 
> <br>
> Unsubsribe to this topic: http://forums.terracotta.org/forums/forums/unwatchForum/3.page 
> <br>
> </body>
> </html>



</div>



{% endraw %}
