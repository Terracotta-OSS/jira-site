---

title: "Make cache.get() use the CacheLoader if it exists"
layout: issue
tags: 
permalink: /browse/EHC-55

issue_key: EHC-55
issue_numeric_sort_key: 55
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

The current behavious is to only use the CacheLoader if you call cache.getWithLoader(), but it should use it on get() as well as this is what is intuitive. Also more inline with the JCache spec. 

Sourceforge Ticket ID: 1987751 - Opened By: prophecyslides - 7 Jun 2008 23:25 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=693320
Originator: NO

Hi

I looked at this. You can use the JCache decorator if you want JCache behaviour. The ehcache Cache class is not and will not be compliant with JCache. The JCache decorator is.



Comment by: gregluck - 9 Jul 2008 07:20 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
