---

title: "Diskless Active/Passive"
layout: issue
tags: 
permalink: /browse/CDV-38

issue_key: CDV-38
issue_numeric_sort_key: 38
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
votes:  1
watchers: 1

created: "2007-01-04T18:03:56.000-0500"
updated: "2012-07-27T19:59:23.000-0400"
resolved: "2007-06-07T13:57:45.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Requirements:
   \1 Remove need for shared disk infrastructure.
   \1 Use of local disk ok. Continue to support large virtual heap.
   \1 Maintain current contract around ACID.
   \1 Specify diskless a/p mode in configuration file...
   \1 Should work in both persistent and non-persistent mode
   \1 Performance may only drop by TBD (20%) percentage

Optional:
   \1 No split the brain
  \1 Support abiity to specify N passive Terracotta Servers

Engineering Requirements
   \1 Synchronously Share State With a Passive - w/out need for disk infrastructure
   \1 Can add/remove passives without pause for Active

Use Cases:
   A large number of potential Open Terracotta deployments are for department level applications on 2 nodes with a requirement to keep new hardware purchases to an absolute minimum. Desired clustering deployment architecture will be both an L1 and L2 on each of the two nodes without access to a shared NFS. Adding a crossover cable to connect the L2s is acceptable. If either nodes dies, the other node takes over all L1 work and becomes the only (and primary) L2. When the dead node is restarted, it becomes a working L1 and the passive L2.


</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-22</span>

<div markdown="1" class="comment">

this feature will be in demo-able status by moraga release date.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-03-20</span>

<div markdown="1" class="comment">

Moving to Noriega, where more work will be done on this.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-06-07</span>

<div markdown="1" class="comment">

Feature complete 

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-06-07</span>

<div markdown="1" class="comment">

verify that this is complete, and that we have adequate tests including performance

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-06-25</span>

<div markdown="1" class="comment">

when perf numbers ready and tests checked into perf framework trunk ssign to Hung

</div>



{% endraw %}
