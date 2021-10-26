---

title: "NPE if no default cache in ehcache.xml"
layout: issue
tags: 
permalink: /browse/EHC-69

issue_key: EHC-69
issue_numeric_sort_key: 69
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

I removed the default cache settings from my ehache.xml
file since I do not need it as I have named caches only.

<!--    <defaultCache
        maxElementsInMemory="0"
        eternal="false"
        timeToIdleSeconds="0"
        timeToLiveSeconds="0"
        overflowToDisk="false" />-->

 <cache name="dao" 
     maxElementsInMemory="1000" 
     eternal="false" 
     timeToIdleSeconds="300" 
     timeToLiveSeconds="600" 
     overflowToDisk="false" />

I get a NPE exception when creating the CacheManager.

Caused by: java.lang.NullPointerException
 at
net.sf.ehcache.config.Configuration$DefaultCache.access$100(Configuration.java:308)
 at
net.sf.ehcache.config.Configuration.getDefaultCache(Configuration.java:129)
 at
net.sf.ehcache.CacheManager.configure(CacheManager.java:159)
 at
net.sf.ehcache.CacheManager.<init>(CacheManager.java:121)
 at
net.sf.ehcache.CacheManager.create(CacheManager.java:235)
 at  at
com.bmd.selfserviceejb.cache.BaseCacheManager.<clinit>(BaseCacheManager.java:42).null(Unknown
Source)
 ... 49 more


I think it should throw a CacheException instead or
being able to work without a default cache.
Sourceforge Ticket ID: 1025130 - Opened By: nobody - 9 Sep 2004 13:59 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

Currently when the default cache is used to create a new cache it emits a 
warning level log message. I think this is a good balance of convenience 
versus safety.

Originally I forced each cache to have a configuration. This turned out 
not to be convenient for the Hibernate people, who often forget to 
configure caches.

I agree that we can do better than an NPE. I have changed Configuration 
and CacheManager so that a CacheException is thrown with a message 
"Illegal configuration. No default cache is configured."

I have also created a new test 
ConfiguratorTest#testLoadConfigurationFromFileNoDefault() to check that 
a CacheException is thrown.

Thanks for your bug report.
Comment by: gregluck - 20 Sep 2004 02:58 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
