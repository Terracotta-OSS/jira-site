---

title: "expiryThread takes too long to end on DiskStore.dispose()"
layout: issue
tags: 
permalink: /browse/EHC-67

issue_key: EHC-67
issue_numeric_sort_key: 67
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

Using ehcache 0.8 from hibernate 2.1, and notice that
ehcache leaves its expiry and spool threads running
when the cache gets shut down. 

I notice in the browsable CVS that the version of
DiskStore that is tagged ehcahe\_08, as well as the most
recent doesn't wake up very often, at least I would
have expected the threads to have stopped after a
couple minutes with my settings, and they last much
longer than that.

Cache creates a new DiskStore with
diskStore = new DiskStore(this,
configuration.getDiskCachePath());

This does not pass in the supplied
expiryThreadInterval, so DiskStore's default interval
of 5 \* 60 \* 5 seconds is used (25 minutes!). This is a
very long time, and probably not what the developer
specifies. I know hibernate specifies 120 seconds by
default.

Also, it would be nice if, on DiskStore.dispose(), the
expiryThread and spoolThread were woken up so they
could exit in a timely manner.

Sourceforge Ticket ID: 982383 - Opened By: zacjacobson - 30 Jun 2004 01:48 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
