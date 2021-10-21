---

title: "Notifies listeners 2 times with clearOnFlush='false'"
layout: issue
tags: 
permalink: /browse/EHC-38

issue_key: EHC-38
issue_numeric_sort_key: 38
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

With the configuration option clearOnFlush='false' an element could be in memory and disk. The private method Cache.remove(...)  removes the copy from memory and from disk and call listeners, first time for the element memory copy and second time for the element disk copy. It should call the listeners just one time. The patch is an ' else ' in the line 1730 in Cache.java.
Sourceforge Ticket ID: 2831486 - Opened By: jonathanbarbero - 3 Aug 2009 13:40 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
