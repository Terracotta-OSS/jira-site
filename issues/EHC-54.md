---

title: "Sync-problem (?) calling a get if elements has been removed"
layout: issue
tags: 
permalink: /browse/EHC-54

issue_key: EHC-54
issue_numeric_sort_key: 54
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
updated: "2009-09-22T23:44:27.000-0400"
resolved: "2009-09-22T23:44:27.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Hello ;-)

I've tried to stress test the ehcache - diskstore in
order to get our site work with the ehcache.
We need to cache more than 100.000 html-snippets - each
at about 30 kb. And we have a concurrent acces of 10
requests per second.

So I designed a CacheStressTest that will simulate our
application behaviour in:

1. number of request
2. adds and removals of cache elements
3. concurrent user


Problem arises, if we remove some entries during the
test and other requests trie to get entries, there
comes a log.error from DiskStore that it cannot read
the ObjectInputStream properly.

Please debug your DiskStore in order to fix this
sync-problem.

I will attach our CacheStressTest to this bug; I
stripped down the maxCacheElements to 1000 as it shows
the bug very well.

Thanx in advance,

Sebastian
Sourceforge Ticket ID: 1424503 - Opened By: nobody - 5 Feb 2006 12:28 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
