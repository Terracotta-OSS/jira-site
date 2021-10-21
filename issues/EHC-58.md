---

title: "Large TTL values cause expire time to be incorrect"
layout: issue
tags: 
permalink: /browse/EHC-58

issue_key: EHC-58
issue_numeric_sort_key: 58
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
updated: "2009-09-22T23:44:27.000-0400"
resolved: "2009-09-22T23:44:27.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

I was attempting to work with a large expire time TTL of 2592000 (30 
days) for a disk-persistent cache, but the expire time was always 
returning "expired". Upon investigation, I believe it might be due to the 
net.sf.ehcache.Element.ONE\1SECOND constant, which is an int. When used  
as

long ttlExpiry = creationTime + timeToLive \1 ONE\1SECOND

in the getExpirationTime() method, this causes the integer result of 
(timeToLive \1 ONE\1SECOND) to overflow into a negative value.

Changing the ONE\1SECOND constant into a long fixed the problem for 
me.
Sourceforge Ticket ID: 1548314 - Opened By: msqr - 29 Aug 2006 04:09 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=693320

Matt

That one must have got introduced when I switched over to
Element based expiry.

Thanks for finding it.

The fix is in trunk and will be in ehcache-1.2.3.

Greg
Comment by: gregluck - 1 Sep 2006 07:45 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
