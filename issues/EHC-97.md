---

title: "RMICachePeer: CacheEventListener must be CacheReplicator"
layout: issue
tags: 
permalink: /browse/EHC-97

issue_key: EHC-97
issue_numeric_sort_key: 97
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

created: "2009-09-21T15:07:43.000-0400"
updated: "2009-09-22T23:44:29.000-0400"
resolved: "2009-09-22T23:44:29.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

RMIAsynchronousCacheReplicator and 
RMISynchronousCacheReplicator implement 
CacheEventListener as a CacheReplicator.  They use 
RMICachePeer to handle the notifications.  The 
problem is that when RMICachePeer receives a 
notification to put or remove an Element it 
intentionally does not call the local 
CacheEventListeners.  This is to prevent circular 
notifications to CacheReplicators.  But, it does not 
allow a CacheEventListener to not be a 
CacheReplicator.  With this RMI implementation all 
CacheEventListeners whether they extend 
RMISynchronousCacheReplicator or are an additional 
configured CacheEventListener receive notifications 
only when a cache change is made directly to the 
Cache.  They do not receive notifications from a 
CachePeer.  This essentially makes all 
CacheEventListeners CacheReplicators.

A way to correct this is to separate the concerns of 
Cache replication from Cache event listening.  This 
could be be done by Cache having a list of 
CacheReplicatorFactorys separate from a list of 
CacheEventListenerFactorys.  CacheReplicators are not 
notified of Cache changes from remote peers.  
CacheEventListeners are notified of Cache changes 
regardless of where they were initiated.
Sourceforge Ticket ID: 1480027 - Opened By: amcnutt - 1 May 2006 21:14 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
