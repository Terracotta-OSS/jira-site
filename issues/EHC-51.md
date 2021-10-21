---

title: "overflowToDisk=\"true\" does not solve memory size problem"
layout: issue
tags: 
permalink: /browse/EHC-51

issue_key: EHC-51
issue_numeric_sort_key: 51
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

created: "2009-09-21T15:07:39.000-0400"
updated: "2009-09-22T23:44:26.000-0400"
resolved: "2009-09-22T23:44:26.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

I use ehcache 1.4.1.

We thought that overflowToDisk="true" will solve memory size problem.

However it does not. It seems that keys still are stored in memory, so memory grows indefinitely!
Can we somehow solve this problem?
Sourceforge Ticket ID: 2009688 - Opened By: astudnev - 3 Jul 2008 12:29 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
