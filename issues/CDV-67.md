---

title: "L2 Partial Collections: Support reading and writing of Sets in small chunks"
layout: issue
tags: 
permalink: /browse/CDV-67

issue_key: CDV-67
issue_numeric_sort_key: 67
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "npurwar"
reporter: "ssubbiah"
votes:  2
watchers: 2

created: "2006-06-14T18:29:25.000-0400"
updated: "2009-04-10T17:58:33.000-0400"
resolved: "2008-07-29T18:37:05.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

We currently treat collections as one big thing. If they are very large and we need to write them to disk and read them from disk in one big chunk then we will be in trouble. We need to be able to read and write parts of large collections. 

Currently we support Partical collections for regular HashMaps ( IdentityHashMaps, Hashtable, Properties, HashMaps etc.) . This needs to be extended for Sets.

Sets are easier than the Lists coz Sleepycat doesnt provide good support for Lists.

</div>

## Comments


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2009-04-07</span>

<div markdown="1" class="comment">

Completed.

</div>



{% endraw %}
