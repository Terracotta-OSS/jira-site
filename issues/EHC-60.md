---

title: "CacheStatistics causes a NPE if used for fresh caches"
layout: issue
tags: 
permalink: /browse/EHC-60

issue_key: EHC-60
issue_numeric_sort_key: 60
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

Hi, 
net.sf.ehcache.management.CacheStatistics class has a method:

    public String getAssociatedCacheName() {
        return statistics.getAssociatedCacheName();
    }

If this method is called (through JMX or an administration interface of Spring context) before the cache is even used, then the statiscs member is not initialized and is null, and causes a NPE. 

How about this as a fix ?

    public String getAssociatedCacheName() {
        return (statistics == null ? "N/A" : statistics.getAssociatedCacheName();
    }

Regards,
Bulent Erdemir
Sourceforge Ticket ID: 1828351 - Opened By: bulenterdemir - 8 Nov 2007 15:29 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320
Originator: NO

Hi

The Javadoc says it should return null. I added the NPE guard but it return null if there is no statistics object.

The fix is in trunk and will be in ehcache-1.4 beta2.

Greg
Comment by: gregluck - 13 Nov 2007 08:05 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
