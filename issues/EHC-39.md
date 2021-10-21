---

title: "Ad: Ehcache returns old value in case of quick value updates"
layout: issue
tags: 
permalink: /browse/EHC-39

issue_key: EHC-39
issue_numeric_sort_key: 39
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

I wasn't able to comment in any way in the old bug (ID: 2824181) so I created another one.

I don't think it's a problem with ConcurrentHashMap in MemoryStore.
The problem is not showing with following cache settings in my original JUnit Test:
        Cache cache = new Cache("someName", 200000, true, true, 100000, 1000000);

The difference from the original test is the number ofmaxElementsInMemory. So it seems the "get old value" problem lies in overflowing the MemoryStore to disk. I've tried to track this problem myself (unfortunately I don't know the code well enough), and my bet (more of a hunch) for the source of the problem would be the spool in the DiskStore.

IMO, the synchronized on MemoryStore::put() is a bit too harsh to get rid of the problem. If a "synchronized" has to be used as a temporary measure. It's better to put it on DiskStore::put() (I have not tested if that fixes the problem).

One extra note:
I've found that the initial value of spool variable is "new HashMap()" while everywhere else when you need to clear the spool, you use "Collections.synchronizedMap(new HashMap())". I think it would be a good idea to use the synchronizedMap in the initializer as well. (Or use the unsynchronized one in the "removeAll()" and "swapSpoolReference()" and lock externally).
Sourceforge Ticket ID: 2827708 - Opened By: oninofaq - 27 Jul 2009 10:44 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
