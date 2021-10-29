---

title: "source repo is borken! -- dependencies project lists libraries at the root instead of under lib"
layout: issue
tags: 
permalink: /browse/CDV-95

issue_key: CDV-95
issue_numeric_sort_key: 95
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: ""
reporter: "eellis"
votes:  0
watchers: 0

created: "2007-01-24T15:40:27.000-0500"
updated: "2007-02-21T14:24:18.000-0500"
resolved: "2007-01-24T16:00:17.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">



</div>

## Comments


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-01-24</span>

<div markdown="1" class="comment">

Hmmm, I have no idea what the problem is. I'm working on Windows and the build won't compile.  

</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-01-24</span>

<div markdown="1" class="comment">

Gary and I were able to get this working. I don't know what the exact issue is but it's related to Cygwin and Eclipse. I had downloaded trunk into the cygwin home directory. This caused the compilation errors. Once I moved trunk to the windows desktop everything worked fine.

</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-01-24</span>

<div markdown="1" class="comment">

We may want to create a new issue realted to running trunk from cygwin/home.

</div>



{% endraw %}
