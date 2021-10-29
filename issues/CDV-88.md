---

title: "Evaluate auto-locking * *..*.*(..) impact on boot-jar"
layout: issue
tags: 
permalink: /browse/CDV-88

issue_key: CDV-88
issue_numeric_sort_key: 88
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Won't Fix"
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "hhuynh"
reporter: "steve"
votes:  0
watchers: 1

created: "2006-04-26T13:45:08.000-0400"
updated: "2007-06-11T18:02:55.000-0400"
resolved: "2007-06-08T13:34:48.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

We now auto-lock the world, which has the side effect of auto-locking boot-jar classes. Is this what we want? should we seperate the concepts?

</div>

## Comments


{:.comment-heading}
### **Tim Eck** <span class="date">2006-09-19</span>

<div markdown="1" class="comment">

I'm not aware of a highly negative impact, but of course it can be problematic (from a performance point of view) to overly autolock. 

If anyone cares to make a call about whether we should split out the locking section to have sections applicable in different contexts (ie. boot jar class, non boot-jar class, or both)....then I don't think it would be too much work to do. In my opinion, this is just going to add perceived complexity, require more docs and testing and such, which until we know we have a problem seems slightly foolish


</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-05-30</span>

<div markdown="1" class="comment">

verify that this has been resolved and has a test/test case

</div>


{:.comment-heading}
### **Hung Huynh** <span class="date">2007-06-06</span>

<div markdown="1" class="comment">

there hasn't any action/comment for this issue to close

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-06-08</span>

<div markdown="1" class="comment">

There is a new bug with more information and this should be addressed in Ortega

</div>



{% endraw %}
