---

title: "Application Node (L1) to Terracotta Server (L2) Synchronized Replication"
layout: issue
tags: 
permalink: /browse/CDV-50

issue_key: CDV-50
issue_numeric_sort_key: 50
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "1 Critical"
components: ""
labels: 
assignee: "cfisher"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-07T16:26:14.000-0500"
updated: "2012-07-27T19:59:37.000-0400"
resolved: "2007-03-02T17:59:25.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Motivation:
Satisfy the needs of operators who require full ACID (Atomicity, Consistency, Isolation, and Durability) transactions between the L1 and L2 as an alternative to the current transaction replay approach.

Synchronized Replication Requirements:
   \1 Support L1 to L2 synchronized replication for additional coherence.
   \1 Transactions by the L1 are not completed ACK until written to permanent store/replicated.
   \1 Configurable in configuration file (tc-config.xml).


</div>

## Comments


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-15</span>

<div markdown="1" class="comment">

Have one of your peeps do this

</div>



{% endraw %}
