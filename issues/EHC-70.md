---

title: "SelfPopulatingCache should not change thread name"
layout: issue
tags: 
permalink: /browse/EHC-70

issue_key: EHC-70
issue_numeric_sort_key: 70
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

This is similar to bug 1513436. SelfPopulatingCache and UpdatingSelfPopulatingCache should not change the current thread name. I can see where this is useful for debugging, but it has a side-effect in that it messes with a resource that really doesn't belong to it.
Sourceforge Ticket ID: 1831446 - Opened By: scotte - 14 Nov 2007 00:17 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320
Originator: NO

Scotte

I think I agree.

Gone.

This is in trunk and will be in 1.4 beta2.

Thanks for the report.

Greg
Comment by: gregluck - 23 Nov 2007 08:00 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
