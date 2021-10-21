---

title: "Cache not alive error"
layout: issue
tags: 
permalink: /browse/EHC-91

issue_key: EHC-91
issue_numeric_sort_key: 91
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

In our project, we are using EhCache with Hibernate. Also we are using spring 2.0. When we are trying to shutdown application using tomcat, we are getting an error saying "java.lang.IllegalStateException - Cache is not alive". Could you please let me know how to get out of this situation?
Sourceforge Ticket ID: 2537692 - Opened By: kemparajuknk - 26 Jan 2009 13:43 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Hi

If you tell me the version of ehcache you are using that might help. 

The Exception is caused by an access to a Cache after it has been shutdown. This is what should happen. The question is why is an access to a cache happening after a shutdown. A stack trace would help.

What you are seeing is not a bug, but rather an interaction between Spring, Hibernate, Tomcat and Ehcache. Please post to the forum with your stack trace or create a support contract.


Comment by: gregluck - 3 Apr 2009 09:15 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
