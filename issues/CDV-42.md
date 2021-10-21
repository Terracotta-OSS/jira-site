---

title: "Package Both Platform Scripts"
layout: issue
tags: 
permalink: /browse/CDV-42

issue_key: CDV-42
issue_numeric_sort_key: 42
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "juris"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-05T18:36:26.000-0500"
updated: "2012-07-27T19:59:44.000-0400"
resolved: "2007-02-06T16:42:14.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Rather than put platform specific scripts into the Linux and Windows kits, simply put both set of scripts (.sh and .bat) into all kits.  In addition to reducing the number of moving parts in the kits, this will let Windows users with Cygwin use .sh scripts and make it possible to move installations between platforms.


</div>

## Comments


{:.comment-heading}
### **Juris Galang** <span class="date">2007-02-06</span>

<div markdown="1" class="comment">

Our kits (from the trunk branch) now contains both .sh and .bat files.

</div>



{% endraw %}
