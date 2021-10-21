---

title: "Unlocked DSO object does not become locked when shared"
layout: issue
tags: 
permalink: /browse/CDV-65

issue_key: CDV-65
issue_numeric_sort_key: 65
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Duplicate"
priority: "2 Major"
components: ""
labels: 
assignee: "serverteam"
reporter: "tgautier"
votes:  0
watchers: 0

created: "2006-06-19T18:13:25.000-0400"
updated: "2012-07-27T19:59:35.000-0400"
resolved: "2007-04-06T17:46:28.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Here is the scenario:

+ JVM 1 Thread 1acquires a lock on an object (due to a synchronized method call for example)
+ The locked object becomes shared as a DSO object
+ JVM2 Thread 1attempts to acquire the lock by calling the same synchronized method on the shared object
+ JVM2 Thread 1acuires the lock

This happens because the lock is granted before the object is shared.  It is not sufficient to suggest that the object be shared and then locked, as the gap between the share and the acquisition of the lock creates a race condition (suppose that it is required that JVM Thread 1 in particular is the first thread to acquire the lock)

There is a workaround, which is to create state in the object which reflects it acquisition of the "outer lock" thus when this state is changed the "outer lock" is known to have been acquired, and thus the JVM2 Thread 1 may proceed to acquire the outer lock.

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2006-08-16</span>

<div markdown="1" class="comment">

This is a known issue. For Lawton we should give a better error.

</div>



{% endraw %}
