---

title: "Hibernation usage doc error"
layout: issue
tags: 
permalink: /browse/EHC-62

issue_key: EHC-62
issue_numeric_sort_key: 62
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
updated: "2009-09-22T23:44:27.000-0400"
resolved: "2009-09-22T23:44:27.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

In documentation section
Ensuring ehcache is enabled
on page
http://ehcache.sourceforge.net/documentation/#mozTocId635920
The line that reads
<property
name="hibernate.cache.provider\1class">net.sf.ehcache.hibernate.Provider</property>

should instead read
<property
name="hibernate.cache.provider\1class">net.sf.hibernate.cache.EhCacheProvider</property>

Sourceforge Ticket ID: 1247019 - Opened By: nobody - 28 Jul 2005 19:16 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=693320

Ok. I have updated the documentation and also deprecated the Provider and 
Plugin. In addition a warning will be issued advising users to change to the 
Hibernate provided plugin.

In trunk and will be in ehcache-1.2
Comment by: gregluck - 7 Mar 2006 00:02 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
