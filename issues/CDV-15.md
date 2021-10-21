---

title: "Cluster iBatis second level cache"
layout: issue
tags: 
permalink: /browse/CDV-15

issue_key: CDV-15
issue_numeric_sort_key: 15
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Won't Fix"
priority: "3 Minor"
components: "DSO:L1"
labels: 
assignee: "prodmgmt"
reporter: "drb"
votes:  0
watchers: 0

created: "2006-11-30T19:24:25.000-0500"
updated: "2012-07-27T20:00:52.000-0400"
resolved: "2006-12-03T02:25:18.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Add support to instrument iBATIS cache in Terracotta (possibly using the detached clone feature Terracotta engineering implemented some time ago). Hook into cache API boundaries and transaction boundaries (done with AspectWerks integrated in DSO) and do detach/attach on get/put to the cache. That would also resolve issue with cloning the entire cache on every call.

</div>

## Comments



{% endraw %}
