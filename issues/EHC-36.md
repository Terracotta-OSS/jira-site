---

title: "Concurrency issue"
layout: issue
tags: 
permalink: /browse/EHC-36

issue_key: EHC-36
issue_numeric_sort_key: 36
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
updated: "2009-09-22T23:44:25.000-0400"
resolved: "2009-09-22T23:44:25.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

This bug is related to 2824181 and  2827708. When the test from those bugs is run continuously, eventually it catches situation when not all keys are found in cache (cacheHits != 5000). This happens even when there were no disk spooling (cache's maxElementsInMemory increased to 10000).

The attached test runs the one from 2824181 continuously, but also contains some speed-up modifications.
Sourceforge Ticket ID: 2831182 - Opened By: vgarnashevich - 2 Aug 2009 17:12 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
