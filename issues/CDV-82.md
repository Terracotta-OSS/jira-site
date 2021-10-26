---

title: "Interception package optimization - redirect to target JP directly when possible"
layout: issue
tags: 
permalink: /browse/CDV-82

issue_key: CDV-82
issue_numeric_sort_key: 82
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Won't Fix"
priority: "2 Major"
components: "X-AspectWerkz"
labels: 
assignee: "jboner"
reporter: "jboner"
votes:  0
watchers: 0

created: "2006-05-30T18:32:43.000-0400"
updated: "2012-07-27T19:59:36.000-0400"
resolved: "2007-05-31T13:42:16.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

if:

    * no 'regular' advice in compiled JIT JP
      AND
    * all array lengths (member fields in JP) are 0
      THEN
      gen code block to call target member directly



</div>

## Comments


{:.comment-heading}
### **Taylor Gautier** <span class="date">2007-01-19</span>

<div markdown="1" class="comment">

Jonas -

Can you comment a little more on this?  does it still make sense to do?

</div>


{:.comment-heading}
### **Jonas Boner** <span class="date">2007-05-31</span>

<div markdown="1" class="comment">

no, since we are not using that feature in TC

</div>



{% endraw %}
