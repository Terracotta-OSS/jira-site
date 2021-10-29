---

title: "element.getLastAccessTime returns 0 for element just created"
layout: issue
tags: 
permalink: /browse/EHC-86

issue_key: EHC-86
issue_numeric_sort_key: 86
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

According to the Java Doc of getLastAccessTime() of 
Element class, "Gets the last access time. Access means 
get or put. So a newly created Element will have a last 
access time equal to its create time." However, currently 
getLassAccessTime() returns 0 for a newly created element 
that was just "put" into cache that has never been "get".
Sourceforge Ticket ID: 1224325 - Opened By: nobody - 20 Jun 2005 18:26 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

Documentation fixed. Access means get.
Comment by: gregluck - 8 Feb 2006 07:17 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
