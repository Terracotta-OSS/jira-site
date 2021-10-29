---

title: "tcbuild should run ivy by default"
layout: issue
tags: 
permalink: /browse/CDV-96

issue_key: CDV-96
issue_numeric_sort_key: 96
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Cannot Reproduce"
priority: "2 Major"
components: ""
labels: 
assignee: ""
reporter: "eellis"
votes:  0
watchers: 0

created: "2007-01-24T15:41:26.000-0500"
updated: "2007-02-21T14:23:43.000-0500"
resolved: "2007-01-24T16:02:00.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

We can't expect that other developers are as familiar with our system as we are.

</div>

## Comments


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-24</span>

<div markdown="1" class="comment">

I thought it did? I tried it yesterday and it automagically downloaded all the stuff? Did that change?

</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-01-24</span>

<div markdown="1" class="comment">

Works for me. The only thing that you need to run tcbuild once to fix Eclipse projects... I'd say it is quite minor.

</div>


{:.comment-heading}
### **jvoegele** <span class="date">2007-01-24</span>

<div markdown="1" class="comment">

tcbuild runs Ivy prior to the 'compile' target, or any target that depends on 'compile', unless the user explicitly passes the --no-ivy option.

</div>



{% endraw %}
