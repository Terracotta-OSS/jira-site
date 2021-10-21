---

title: "Bug on key retrieval on cache expiration of Element"
layout: issue
tags: 
permalink: /browse/EHC-65

issue_key: EHC-65
issue_numeric_sort_key: 65
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

If you put an Element in a Cache, and wait until the 
expiration period where the Element is no longer in the 
Cache, a call to getKeys() still returns the Element key 
as being in the Cache.  

The JavaDoc lists the following for getKeys():

"Returns the keys of all elements in the cache The List 
returned is not live. It is a copy."

But I didn't read that as being that keys survive beyond 
the Element expiration in the Cache.  If that is the case, 
perhaps the documentation can be beefed up to include 
this conditional?

Email for code snippet if you'd like..

Code version = ehcache-0.8.jar

paul.jenkins@valtech.com

Sourceforge Ticket ID: 961575 - Opened By: nobody - 27 May 2004 13:58 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
