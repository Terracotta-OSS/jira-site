---

title: "Cluster iBATIS Generated POJOs (from O/R mapping engine)"
layout: issue
tags: 
permalink: /browse/CDV-44

issue_key: CDV-44
issue_numeric_sort_key: 44
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "1 Critical"
components: ""
labels: 
assignee: "asi"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-05T19:06:19.000-0500"
updated: "2012-07-27T19:59:15.000-0400"
resolved: "2007-02-28T19:47:56.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

Motivation:
  \* Create the foundation of an integration into the O/R mapping space.
  \* Drive transparency through real-world applications.

Requirements:
  \* Create a sample application that utilizes POJOs generated from iBATIS O/R mapping engine.
  \* We must be able to cluster the "data" POJOs generated from iBATIS, which means we can successfully read all fields in a second JVM.


</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-25</span>

<div markdown="1" class="comment">

I just spoke with Antonio and he has a few bugs in front of him before he can start working on this again.  He also mentioned that there is a complication similar to one found when looking into Hibernate support concerning a library they both use which would need to be supported.  Proxies also figure into this as well, so this might be a little complicated.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-25</span>

<div markdown="1" class="comment">

Setting the (tentative) due date to 2007-02-07 and bumping up the priority as it's high on the PRD priority list compared to the other frameworks we want to support.

</div>


{:.comment-heading}
### **Antonio Si** <span class="date">2007-02-28</span>

<div markdown="1" class="comment">

Got the basic support for iBatis, which uses a file persistence hsql database. There are 2 test cases for iBatis currently: IBatisSimpleTest and IBatisSimpleDaoTest.

</div>



{% endraw %}
