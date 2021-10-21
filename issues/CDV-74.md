---

title: "StringBuilder/StringBuffer is slow in JDK 1.6"
layout: issue
tags: 
permalink: /browse/CDV-74

issue_key: CDV-74
issue_numeric_sort_key: 74
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "teck"
reporter: "teck"
votes:  0
watchers: 0

created: "2007-01-16T20:08:49.000-0500"
updated: "2012-07-27T19:59:47.000-0400"
resolved: "2007-02-13T20:27:10.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

StringBuilder.toString() (and probably other critical places) use the (char[], int, int) constructor of String. In 1.6, this string constructor uses a new method (Arrays.copyOfRange()) which ends up calling on the DSO ArrayManager. 

The copyOfRange() methods in java.util.Arrays cannot ever be operating on a "shared" destination array when they call System.arraycopy() and thus do not need to go through ArrayManager.

A new test should be added for StringBuilder, much like com.tctest.StringBufferTest. At least one of the test methods should execute toString() in a loop to measure a speedup

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

Updating Due date to last day of Moraga dev iteration

</div>



{% endraw %}
