---

title: "Bug in RMIAsynchronousCacheReplicator.clone"
layout: issue
tags: 
permalink: /browse/EHC-48

issue_key: EHC-48
issue_numeric_sort_key: 48
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

// replicateRemovals used twice, but should be 

boolean replicatePuts,
            boolean replicatePutsViaCopy,
            boolean replicateUpdates,
            boolean replicateUpdatesViaCopy,
            boolean replicateRemovals

<code>
return new RMIAsynchronousCacheReplicator(replicatePuts, replicateUpdates,
                replicateUpdatesViaCopy, replicateRemovals, replicateRemovals, asynchronousReplicationInterval);
</code>

Sourceforge Ticket ID: 2804387 - Opened By: mcbain4711 - 10 Jun 2009 20:04 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
