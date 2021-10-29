---

title: "improper iteration through ALL_CACHE_MANAGERS"
layout: issue
tags: 
permalink: /browse/EHC-37

issue_key: EHC-37
issue_numeric_sort_key: 37
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

created: "2009-09-21T15:07:38.000-0400"
updated: "2009-09-22T23:44:26.000-0400"
resolved: "2009-09-22T23:44:26.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

We get this exception sometimes:

     [java] java.lang.IndexOutOfBoundsException: Index: 200, Size: 200,
     [java]  at java.util.ArrayList.RangeCheck(ArrayList.java:546)
     [java]  at java.util.ArrayList.get(ArrayList.java:321)
     [java]  at java.util.Collections$SynchronizedList.get(Collections.java:1820)
     [java]  at net.sf.ehcache.CacheManager.detectAndFixDiskStorePathConflict(CacheManager.java:306)

We are on an old version of ehcache, but I see the same bug in the latest release:

        for (int i = 0; i < ALL_CACHE_MANAGERS.size(); i++) {
            CacheManager cacheManager = (CacheManager) ALL_CACHE_MANAGERS.get(i);

If the cache is removed in another thread between those lines, then you get that exception.

Since the latest ehcache is a copyOnWrite list, then all iterating through should be done with iterators, and it will be safe.  All calls of this:  

ALL\_CACHE\_MANAGERS.get

should be changed...

Thanks!
Sourceforge Ticket ID: 2835670 - Opened By: mchyzer - 11 Aug 2009 16:14 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Chris

Thanks. Done. Will be in 1.6.2.

Greg
Comment by: gregluck - 23 Aug 2009 06:01 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
