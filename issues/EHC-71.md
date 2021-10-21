---

title: "shutdown() not synchronized"
layout: issue
tags: 
permalink: /browse/EHC-71

issue_key: EHC-71
issue_numeric_sort_key: 71
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

created: "2009-09-21T15:07:40.000-0400"
updated: "2009-09-22T23:44:28.000-0400"
resolved: "2009-09-22T23:44:28.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

In EHCACHE 1.2.4 the CacheManager sets up a shutdown hook during init.

The run() method in the hook has a synchronized block preventing it from being run in parallel.

However, if the shutdown() method is called explicitly from outside (like Hibernate does) there will be a race condition.

This would be avoided if shutdown() also was synchronized.



Sourceforge Ticket ID: 1704281 - Opened By: jgrape - 20 Apr 2007 12:50 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
