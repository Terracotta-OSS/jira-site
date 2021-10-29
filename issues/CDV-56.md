---

title: "Application Node (L1) to Terracotta Server (L2) Heartbeat"
layout: issue
tags: 
permalink: /browse/CDV-56

issue_key: CDV-56
issue_numeric_sort_key: 56
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "hhuynh"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-08T00:41:15.000-0500"
updated: "2008-04-18T17:52:56.000-0400"
resolved: "2008-04-09T19:22:52.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

Motivation:
Assist operators detect a networking error that could cause cluster problems.

Requirements:
   \* Detect half open L1 to L2 socket (seems open but not communicating) to facilitate quicker failures.


</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-16</span>

<div markdown="1" class="comment">

What should happen in the client when we detect arrhythmia?  Do we blow up?  Should we do this on the server as well?  What is the behavior there if so?

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-16</span>

<div markdown="1" class="comment">

On both sides it has to properly disconnect. In the server that will mean locks get released etc. on the client it means alex's new disconnect event will occur. Thing is, I didn't mean to assign this to you :-)
This was supposed to be assigned to tim. Sorry


</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

Updating Due date to last day of Moraga dev iteration

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-03-05</span>

<div markdown="1" class="comment">

This is 99% certain to miss Moraga. We have higher priority issues/bugs. I'm assigning to IRB and removing due date.

</div>


{:.comment-heading}
### **Tim Eck** <span class="date">2007-08-14</span>

<div markdown="1" class="comment">

assigning this back to PM -- it doesn't have a target so I want it out of my view 

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2008-04-02</span>

<div markdown="1" class="comment">

This is done with the healthchecker. Please update with relevant info such as revision etc. thanks

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2008-04-09</span>

<div markdown="1" class="comment">

Implemented health check between the L1 to L2, L2 to L1 and L2 to L2 in networked Active-passive.

</div>



{% endraw %}
