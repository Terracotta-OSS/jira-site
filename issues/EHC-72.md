---

title: "IBM JDK 1.4 is not detected"
layout: issue
tags: 
permalink: /browse/EHC-72

issue_key: EHC-72
issue_numeric_sort_key: 72
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

created: "2009-09-21T15:07:40.000-0400"
updated: "2009-09-22T23:44:28.000-0400"
resolved: "2009-09-22T23:44:28.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

I am running Websphere 5.1 and using ehcache v0.9

In the doc it states that in JDK1.4 commons-collection
is not required.

However when I run ehcache it tries to load a class
from commons collection.



WebSphere Platform 5.1 [BASE 5.1.0.3 cf30412.02] [JDK
1.4.1 b0344.02]  running with process name
localhost\localhost\server1 and process id 1128
Host Operating System is Windows 2000, version 5.0
Java version = J2RE 1.4.1 IBM Windows 32 build
cn1411-20031011 (JIT enabled: jitc), Java Compiler =
jitc, Java VM name = Classic VM



java.lang.NoClassDefFoundError:
org/apache/commons/collections/LRUMap
 at java.lang.ClassLoader.defineClass0(Native Method)
 at
java.lang.ClassLoader.defineClass(ClassLoader.java(Compiled
Code))
 at
java.security.SecureClassLoader.defineClass(SecureClassLoader.java(Compiled
Code))
 at
com.ibm.ws.classloader.CompoundClassLoader.\_defineClass(CompoundClassLoader.java:446)
 at
com.ibm.ws.classloader.CompoundClassLoader.findClass(CompoundClassLoader.java(Compiled
Code))
 at
com.ibm.ws.classloader.CompoundClassLoader.loadClass(CompoundClassLoader.java(Compiled
Code))
 at
java.lang.ClassLoader.loadClass(ClassLoader.java(Compiled
Code))
 at net.sf.ehcache.Cache.initialise(Cache.java:213)
 at
net.sf.ehcache.CacheManager.addCacheNoCheck(CacheManager.java:297)
 at
net.sf.ehcache.CacheManager.configure(CacheManager.java:164)
 at
net.sf.ehcache.CacheManager.<init>(CacheManager.java:121)
 at
net.sf.ehcache.CacheManager.create(CacheManager.java:235)
 at  at
com.bmd.selfserviceejb.cache.BaseCacheManager.<clinit>(BaseCacheManager.java:42).null(Unknown
Source)
 


And my cache settings

    <defaultCache
        maxElementsInMemory="0"
        eternal="false"
        timeToIdleSeconds="0"
        timeToLiveSeconds="0"
        overflowToDisk="false" />

 <cache name="dao" 
     maxElementsInMemory="1000" 
     eternal="false" 
     timeToIdleSeconds="300" 
     timeToLiveSeconds="600" 
     overflowToDisk="false" />

Sourceforge Ticket ID: 1025128 - Opened By: nobody - 9 Sep 2004 13:54 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
