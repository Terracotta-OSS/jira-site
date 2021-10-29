---

title: "CacheManager.shutdown is not threadsafe"
layout: issue
tags: 
permalink: /browse/EHC-94

issue_key: EHC-94
issue_numeric_sort_key: 94
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

created: "2009-09-21T15:07:43.000-0400"
updated: "2009-09-22T23:44:29.000-0400"
resolved: "2009-09-22T23:44:29.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

The synchronization on the CacheManager.shutdown method
is not suffiecient to prevent a shutdown instance being
returned by the getInstance method.

The problem occurs when a ThreadA calls getInstance()
while ThreadB is in the shutdown function looping over
the current caches. As ThreadB is in a block
synchronized on "this", ThreadA can proceed through the
getInstance() and create() functions because they are
synchronized on CacheManager.class. So ThreadA gets the
instance that is currently being shutdown by ThreadB.

The attached patch fixes this issue by synchronizing
the entire shutdown method on the CacheManager.class.
Sourceforge Ticket ID: 1261613 - Opened By: orthanc - 17 Aug 2005 03:46 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

Eddy,

Thanks. Patch accepted and applied.

Regards
Greg Luck
Comment by: gregluck - 16 Oct 2005 00:10 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
