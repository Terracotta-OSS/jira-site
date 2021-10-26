---

title: "DSO Eclipse Plug-in Outline View"
layout: issue
tags: 
permalink: /browse/CDV-48

issue_key: CDV-48
issue_numeric_sort_key: 48
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "gkeim"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-07T15:43:48.000-0500"
updated: "2012-07-27T19:59:33.000-0400"
resolved: "2007-03-22T15:15:21.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Motivation:
Enhance the developer experience to show an application's clustering information in a single place.

Requirements:
Add an outline view for Terracotta applications which displays a high-level tree hierarchy of the configuration items.

Below the top-level Application Configuration node, show the expandable sub-nodes --
    * Roots
    * Shared Locks
    * Transient Fields
    * Boot JAR Classes
    * Instrumented Classes

Within each sub-node, list the appropriate DSO configuration items and allow users to add or edit the items comparable to the way it is done in the other panels.

Use the same graphical adornments in the DSO Outline View as in the other panels.

Use Cases:
Developer -- Developers often need an overview of the DSO aspect - a quick view of all the roots, locks, etc.

Field Engineer -- Field Engineers sometime need to quickly demonstrate the purpose of the DSO Eclipse plug-in, and it would be better to not have to drill down into the source code.


</div>

## Comments


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-02-06</span>

<div markdown="1" class="comment">

It does make sense to contribute special model to the standard Project Explorer view (since Eclipse 3.2). See how Spring IDE does that http://springide.org/project/wiki/SpringIde2Milestone1#ContentContributiontoEclipsesProjectExplorer

</div>



{% endraw %}
