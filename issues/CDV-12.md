---

title: "Display Object Graph on Non-Portable Error"
layout: issue
tags: 
permalink: /browse/CDV-12

issue_key: CDV-12
issue_numeric_sort_key: 12
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "1 Critical"
components: "X-Common Code"
labels: 
assignee: "teck"
reporter: "drb"
votes:  0
watchers: 0

created: "2006-11-30T19:15:51.000-0500"
updated: "2012-07-27T19:59:45.000-0400"
resolved: "2007-02-23T17:33:28.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

When a non-portable exception is encountered, the specific object that tripped the error is not always the best one to declare as a DSO transient field. Sometimes an object higher up the graph is a more logical choice. To make it easier to identify the most appropriate object to exclude from sharing, Sessions Configurator should display the whole known object graph on non-portable error.

Display:

    * Show object graph containing the offending object
    * All parent objects of the offending object should be expanded
    * Highlight (possibly in red) the offending object
    * Position the highlighted object for easy viewing (possibly in center of page)
    * If information about other offending object is available, highlight them too (possibly in yellow)

You set a root and fire up your client. No locks, no transients, no bootjar classes. Errors are logged and at shutdown a suggested config file is spit to the log, or the specified config file is just updated. This could be the development mode of operation.



</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

Updating Due date to last day of Moraga dev iteration

</div>


{:.comment-heading}
### **Tim Eck** <span class="date">2007-02-23</span>

<div markdown="1" class="comment">

this is done now. There is a textual representation of the graph logged to the TC log file whenever one attempts to share a non-portable type. The graph is adorned with markers showing all of the non-portable instances in the graph. 

This logging in on by default, but can be disabled with the <non-portable-dump> option in the the <runtime-logging> section of the config file



</div>



{% endraw %}
