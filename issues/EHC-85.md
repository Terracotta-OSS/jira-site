---

title: "Expiry error for eternal=true"
layout: issue
tags: 
permalink: /browse/EHC-85

issue_key: EHC-85
issue_numeric_sort_key: 85
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

created: "2009-09-21T15:07:42.000-0400"
updated: "2009-09-22T23:44:29.000-0400"
resolved: "2009-09-22T23:44:29.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

I use ehcache 1.1 with Hibernate 2.1.8

I disabled caching by using this config file:

<ehcache>
    <diskStore path="java.io.tmpdir"/>

    <defaultCache
        maxElementsInMemory="0"
        eternal="true"
        overflowToDisk="false"
        />

</ehcache>

Still I get many warnings: 
WARN  An item was expired by the cache while it was
locked (increase your cache timeout): 60



Sourceforge Ticket ID: 1204307 - Opened By: nobody - 18 May 2005 14:09 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=693320

Your config probably resulted in elements being immediately deleted. i.e. 
maxElementsInMemory will overflow to disk on the first insertion. But you have 
overflowToDisk= false therefore it will be discarded as it overflows.

The better way of disabling caching in Hibernate is to remove the cache directive 
from each mapping file.

In any case this is a Hibernate issue.
Comment by: gregluck - 7 Feb 2006 07:54 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
