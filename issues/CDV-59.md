---

title: "Replicated Hashmap"
layout: issue
tags: 
permalink: /browse/CDV-59

issue_key: CDV-59
issue_numeric_sort_key: 59
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Open"
resolution: ""
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "prodmgmt"
reporter: "fhanik"
votes:  0
watchers: 1

created: "2007-01-10T22:18:51.000-0500"
updated: "2010-03-19T18:58:57.000-0400"
resolved: ""

---



{% raw %}



## Description

<div markdown="1" class="description">

In parallel to creating a complete session implementation, I have an idea and would like to start an effort to solve the same problem but using less code.
The idea is using a replicated hashmap

Problem:
Enable session replication (and context attribute replication) in servlet containers that use HashMap or AbstractMap as the base for session date

Solution:
Create an implementation of the hash map that is suitable for storing sessions in

Here are some details:
1. implement the map using the TC API instead of byte code manipulation
2. map.put() triggers "root" object behavior.(replicates data to L2)
3. map.get will retrieve the object directly from memory or L2
4. map.keySet and map.entrySet only returns data that is loaded into the current heap
5. map.remove removes locally and on L2 (and on other maps if needed)
6. map.clear - only removes objects stored in heap from L2

Locking would be done using a valve and would also solve the cross context invokation problem
No manipulation of the session id as this would also support JVM route rewriting

Problems not solved:
Locking for async servlets


I'll fill in more info as needed.

</div>

## Comments


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-01-15</span>

<div markdown="1" class="comment">

By the way, please note that in case if some node die, its local keyset should be picked up by some other node.

It is also unclear/tricky, how remove will be propagated. Say, some node called remove on instance that is not in its local collection. So, it will go to L2, but how it will get to local collection on node that own that instance?

I can only think of creating collections/maps per node. So nodes would have "local" map/keyset that is inside shared map (and that inner map can be write-locked eagerly by its owner). Then, if one of the nodes die, its map can be copied into some other node map (i.e. triggered by newly introduced cluster events, or by some monitoring thread).

Another issue to think about, is how put will be propagated if aggregated map already has value for that key.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

Updating Due date to last day of Moraga dev iteration

</div>



{% endraw %}
