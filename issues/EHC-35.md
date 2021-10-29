---

title: "JMS Replication does not reconnect after JMS outage"
layout: issue
tags: 
permalink: /browse/EHC-35

issue_key: EHC-35
issue_numeric_sort_key: 35
issuetype: "Bug"
project: "Ehcache Core"
project_key: "EHC"
status: "Open"
resolution: ""
priority: "3 Minor"
components: "ehcache-jmsreplication"
labels: 
assignee: ""
reporter: "sourceforgetracker"
votes:  0
watchers: 0

created: "2009-09-21T15:07:38.000-0400"
updated: "2010-01-06T18:57:13.000-0500"
resolved: ""




---

{% raw %}

## Description

<div markdown="1" class="description">

JBossMQ does not provide a properietary reconnect feature. If JMS is not available JMS replication will be gone forever. To investigate that, I moved most JMS code to JMSCachePeer.java. This is probalby not necessary: I merely  missed it on my first reconnect tests to call 'dispose' on JMSCachePeer, since without it the topic session will get stale and therfore unusable. Anyway, it works now quite reliable, except in some obscure situations when there is no JMS ReplicationThread created by ehcache, which is also fatal, but another story ;)
Sourceforge Ticket ID: 2808944 - Opened By: mbclr - 19 Jun 2009 10:32 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2010-01-06</span>

<div markdown="1" class="comment">

Assigning these issues to Greg, so that he can decide what to do with them.

</div>



{% endraw %}
