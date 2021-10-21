---

title: "Hibernate 3.1 related doc refers to outdated cache regions"
layout: issue
tags: 
permalink: /browse/EHC-53

issue_key: EHC-53
issue_numeric_sort_key: 53
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

The documentation relating to Hibernate
(hibernate.html) is for Hibernate version 3.1.

In the section on 'Configuration with ehcache.xml' the
parts about Queries, StandardQueryCache and
UpdateTimestampsCache refer to the cache region names
'net.sf.hibernate.cache.StandardQueryCache' and
'net.sf.hibernate.cache.UpdateTimestampsCache'. 
However, in Hibernate 3.1 these are known as
'org.hibernate.cache.StandardQueryCache' and
'org.hibernate.cache.UpdateTimestampsCache' respectively.
Sourceforge Ticket ID: 1579761 - Opened By: nobody - 18 Oct 2006 14:58 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=693320

Hi

Updated the 1.2 documentation.

Greg
Comment by: gregluck - 20 Oct 2006 09:24 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
