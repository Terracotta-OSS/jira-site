---

title: "cacheManagerPeerProviderFactory TTL = 0  and replication"
layout: issue
tags: 
permalink: /browse/EHC-44

issue_key: EHC-44
issue_numeric_sort_key: 44
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

with ehcache 1.5.0, the RMICacheManagerPeerProviderFactory with properties timeToLive=0 replicates messages on different machines of a same multicast group. The doc defines that the replication should be limited to localhost.

the project submitted reproduced this behavior.
Sourceforge Ticket ID: 2812067 - Opened By: viben - 25 Jun 2009 11:21 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
