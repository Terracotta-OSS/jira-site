---

title: "Rare Exception on manual Cache#flush()"
layout: issue
tags: 
permalink: /browse/EHC-46

issue_key: EHC-46
issue_numeric_sort_key: 46
issuetype: "Bug"
project: "Ehcache Core"
project_key: "EHC"
status: "Closed"
resolution: "Fixed"
priority: ""
components: ""
labels: 
assignee: "drb"
reporter: "sourceforgetracker"
votes:  0
watchers: 0

created: "2009-09-21T15:07:39.000-0400"
updated: "2009-09-22T23:44:26.000-0400"
resolved: "2009-09-22T23:44:26.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

I get this intermittently when doing a Cache#flush():

Exception in thread "main" java.util.ConcurrentModificationException
 at java.util.ArrayList.writeObject(Unknown Source)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
 at java.lang.reflect.Method.invoke(Unknown Source)
 at java.io.ObjectStreamClass.invokeWriteObject(Unknown Source)
 at java.io.ObjectOutputStream.writeSerialData(Unknown Source)
 at java.io.ObjectOutputStream.writeOrdinaryObject(Unknown Source)
 at java.io.ObjectOutputStream.writeObject0(Unknown Source)
 at java.io.ObjectOutputStream.defaultWriteFields(Unknown Source)
 at java.io.ObjectOutputStream.defaultWriteObject(Unknown Source)
 at java.util.Collections$SynchronizedCollection.writeObject(Unknown Source)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
 at java.lang.reflect.Method.invoke(Unknown Source)
 at java.io.ObjectStreamClass.invokeWriteObject(Unknown Source)
 at java.io.ObjectOutputStream.writeSerialData(Unknown Source)
 at java.io.ObjectOutputStream.writeOrdinaryObject(Unknown Source)
 at java.io.ObjectOutputStream.writeObject0(Unknown Source)
 at java.io.ObjectOutputStream.writeObject(Unknown Source)
 at net.sf.ehcache.store.DiskStore.writeIndex(DiskStore.java:816)
 at net.sf.ehcache.store.DiskStore.flush(DiskStore.java:573)
 at net.sf.ehcache.Cache.flush(Cache.java:1509)
 at com.wiley.wispers.resolvedoi.EHCacheImpl.main(EHCacheImpl.java:655)

What seems to be happening is that the expiry thread is kicking in during the DiskStore#writeIndex() call, and, since #writeIndex() doesn't synchronize on the spoolLock, while DiskStore#expireElements() does, this causes a ConcurrentModificationException. It looks like DiskStore#writeIndex() needs some additional synchronization.

Work-around: catch exception and retry Cache#flush() operation (presuming the expiry thread runs less frequently than the time taken to persist the cache!).

Sourceforge Ticket ID: 2008055 - Opened By: ellispritchard - 1 Jul 2008 17:36 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
