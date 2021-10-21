---

title: "Remove hard dependency on javax/servlet/http/HttpSession class"
layout: issue
tags: 
permalink: /browse/CDV-23

issue_key: CDV-23
issue_numeric_sort_key: 23
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "SpringRuntime"
labels: 
assignee: "ekulesho"
reporter: "ekulesho"
votes:  0
watchers: 1

created: "2006-12-04T15:10:48.000-0500"
updated: "2012-07-27T19:59:13.000-0400"
resolved: "2006-12-27T20:36:53.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

GetBeanProtocolWithScope aspect is failing to load if class javax/servlet/http/HttpSession is not available.

Found while investigating user's issue from http://forums.terracotta.org/forums/posts/list/80.page

</div>

## Comments


{:.comment-heading}
### **Steve Harris** <span class="date">2006-12-04</span>

<div markdown="1" class="comment">

Look into it and let me know what it will take to fix and we can schedule it. If it's easy we can do it now and push it in the dot release.

</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2006-12-27</span>

<div markdown="1" class="comment">

Fixed in 2.2.1 branch

</div>



{% endraw %}
