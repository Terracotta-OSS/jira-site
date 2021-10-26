---

title: "Silent failure if ehcache.xml not found"
layout: issue
tags: 
permalink: /browse/EHC-83

issue_key: EHC-83
issue_numeric_sort_key: 83
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

My threads were just stopping and when I investigating
further, I found that I'd not got ehcache.xml in the
classpath.  

  As a result, manager.getCache("MMSPCache")  was
returning a null, which lead to the threads dying with
NPE's (but not being seen as they were spun-off worker
threads)!

  I'd suggest that the return from getCache() should be
an exception or failing that, explictly documented in
the Javadoc.


Sourceforge Ticket ID: 1075256 - Opened By: gwynevans - 29 Nov 2004 13:54 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

Hi, Could I get you to submit a test
Comment by: gregluck - 7 Mar 2005 01:30 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
