---

title: "Synchronous bootstrap and manual peer discovery combo"
layout: issue
tags: 
permalink: /browse/EHC-73

issue_key: EHC-73
issue_numeric_sort_key: 73
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

Version 1.2.3

When using:
- Manual peer discovery
- Synchronous bootstrapping (RMIBootstrapCacheLoader)

When we start the first server of a group, there is 
of course no other peer in the group so an Exception 
(caused by "connection refused") will be thrown 
during load method execution and the CacheManager 
instantiation will fail.

Proposal:
  
Add a try catch around the doLoad call in load method 
of RMIBootstrapCacheLoader (like the run method of 
BootstrapThread)


Sourceforge Ticket ID: 1585015 - Opened By: pgalley22 - 26 Oct 2006 11:13 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

Patrick

I catch the exception and log it as a warning on cache.bootstrap in the CacheManager. If you think it should be an info message let me know.

The fix is in trunk and will be in ehcache-1.2.4. Thanks for the bug report.

Greg 
Comment by: gregluck - 29 Oct 2006 06:15 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
