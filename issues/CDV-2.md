---

title: "Performance Enhancements"
layout: issue
tags: 
permalink: /browse/CDV-2

issue_key: CDV-2
issue_numeric_sort_key: 2
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "3 Minor"
components: "DSO:L2"
labels: 
assignee: "ssubbiah"
reporter: "drb"
votes:  0
watchers: 0

created: "2006-11-30T18:17:05.000-0500"
updated: "2012-07-27T20:00:53.000-0400"
resolved: "2007-01-15T13:54:15.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

In persistent mode under load we have a few pipeline stalls in the server. We will be allowing better
flow through the system by decoupling apply and write to disk and prevent things from going pending
that don't need to.

</div>

## Comments


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-01-15</span>

<div markdown="1" class="comment">

Many other improvements along with this one was made for improving performance of persistence mode.

</div>



{% endraw %}
