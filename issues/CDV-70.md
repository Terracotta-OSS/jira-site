---

title: "Partial collections for queue"
layout: issue
tags: 
permalink: /browse/CDV-70

issue_key: CDV-70
issue_numeric_sort_key: 70
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "DSO:L1,DSO:L2"
labels: 
assignee: "hhuynh"
reporter: "tgautier"
votes:  0
watchers: 0

created: "2007-01-15T21:28:31.000-0500"
updated: "2007-06-04T13:58:07.000-0400"
resolved: "2007-02-17T00:21:38.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Currently client side queues don't have partial collections.  Implementing partial collections could have performance increase by not requiring that the entire queue is materialized in all VMs that have a reference to it.

</div>

## Comments


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-02-17</span>

<div markdown="1" class="comment">

This is fixed for 2.2.1 ;)

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-05-30</span>

<div markdown="1" class="comment">

Verify that this has been resolved and has a test

</div>



{% endraw %}
