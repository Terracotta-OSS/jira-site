---

title: "Add Calendar and GregorianCalendar classes to dso boot jar by default"
layout: issue
tags: 
permalink: /browse/CDV-62

issue_key: CDV-62
issue_numeric_sort_key: 62
issuetype: "New Terracotta Integration Module"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Open"
resolution: ""
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "prodmgmt"
reporter: "ekulesho"
votes:  0
watchers: 0

created: "2006-07-13T11:10:26.000-0400"
updated: "2010-03-19T18:59:33.000-0400"
resolved: ""




---

{% raw %}

## Description

<div markdown="1" class="description">

Calendar and GregorianCalendar classes are used by some demos from Spring WebFlow distribution and it would be really nice if those would work out of the box without requiring customer to tweak boot jar.

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2006-07-13</span>

<div markdown="1" class="comment">

PM input please.

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2006-09-29</span>

<div markdown="1" class="comment">

This should be a simple fix.

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2006-10-19</span>

<div markdown="1" class="comment">

This doesnt seem to be a simple fix and we are getting more and more new requirements poring in. I am moving this to Moraga.

</div>


{:.comment-heading}
### **Tim Eck** <span class="date">2007-01-11</span>

<div markdown="1" class="comment">

As a general rule we've been keeping the default set of classes included in the boot jar as small as possible. It's a tradeoff of convenience versus performance. 

</div>



{% endraw %}
