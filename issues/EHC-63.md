---

title: "Synchronous SocketTimeoutException"
layout: issue
tags: 
permalink: /browse/EHC-63

issue_key: EHC-63
issue_numeric_sort_key: 63
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
updated: "2009-09-22T23:44:27.000-0400"
resolved: "2009-09-22T23:44:27.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

When I try to run distributed queues with
replicateAsynchronously=false and then quickly put a
large number of objects into peers i seem to encounter
some kinda of lock. Then I get the following error
message:
net.sf.ehcache.distribution.RemoteCacheException: Error
doing put to remote peer. Message was: Error
unmarshaling return header; nested exception is:
        java.net.SocketTimeoutException: Read timed out
        at
net.sf.ehcache.distribution.RMISynchronousCacheReplicator.replicatePutNotification(RMISynchronousCacheReplicator.java:135)
        at
net.sf.ehcache.distribution.RMISynchronousCacheReplicator.notifyElementUpdated(RMISynchronousCacheReplicator.java:170)
        at
net.sf.ehcache.event.RegisteredEventListeners.notifyElementUpdated(RegisteredEventListeners.java:103)
        at net.sf.ehcache.Cache.put(Cache.java:574)
        at net.sf.ehcache.Cache.put(Cache.java:522)

....

I don't have this problem with
replicateasynchronous=true, but the 1000ms time between
flushing the queue is too slow for my application.

dpatton@trafficland.com
Sourceforge Ticket ID: 1540322 - Opened By: nobody - 15 Aug 2006 01:16 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
