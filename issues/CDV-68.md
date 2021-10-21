---

title: "expose honor-volatile setting in user level config..."
layout: issue
tags: 
permalink: /browse/CDV-68

issue_key: CDV-68
issue_numeric_sort_key: 68
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Open"
resolution: ""
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "prodmgmt"
reporter: "teck"
votes:  0
watchers: 0

created: "2006-06-12T20:14:31.000-0400"
updated: "2010-03-19T19:00:28.000-0400"
resolved: ""

---



{% raw %}



## Description

<div markdown="1" class="description">

In the DSO code base we have the ability to extend the "volatile" field modifier to have a DSO semantic (ie. you can read/write volatile fields of shared objects w/o explicit locking). There is no user level way (ie. config element) to enable this behaviour though

</div>

## Comments


{:.comment-heading}
### **Tim Eck** <span class="date">2007-04-06</span>

<div markdown="1" class="comment">

I thought I had comments on this before. The more I thought about this the less I think we should do this :-) 

From a completeness standpoint, it seems inconsistent to not promote volatile fields to have a DSO semantic, but I really think using volatile fields is way too fine grained for DSO use, but who ami I to say 

</div>


{:.comment-heading}
### **orion** <span class="date">2007-04-06</span>

<div markdown="1" class="comment">

I agree that it's silly to expose volatile fields...

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-09-27</span>

<div markdown="1" class="comment">

Look at this during config rewrite

</div>


{:.comment-heading}
### **Howard Mak** <span class="date">2008-09-23</span>

<div markdown="1" class="comment">

Terracotta complains (throws UnlockedSharedObjectException) when setting a volatile field in a DSO in the absence of any monitor locks.  This makes it harder to clusterize code already optimized for non-Terracotta setups.

i.e., http://forums.terracotta.org/forums/posts/list/1336.page

</div>



{% endraw %}
