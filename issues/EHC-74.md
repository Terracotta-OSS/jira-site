---

title: "SelfPopulatingCache, ThreadName"
layout: issue
tags: 
permalink: /browse/EHC-74

issue_key: EHC-74
issue_numeric_sort_key: 74
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

created: "2009-09-21T15:07:41.000-0400"
updated: "2009-09-22T23:44:28.000-0400"
resolved: "2009-09-22T23:44:28.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Sometimes the ThreadName is not unset correctly.
Sourceforge Ticket ID: 1033061 - Opened By: bdargan - 23 Sep 2004 01:14 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=693320

Added some missing finally clauses which set the thread name back to 
the old thread name. I have added a test for thread name.

I have set the priority of this bug to 2, because it is only used for 
debugging.
Comment by: gregluck - 25 Sep 2004 07:11 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
