---

title: "Add support to have modular, pre-packaged configurations (aka \"config modules\")"
layout: issue
tags: 
permalink: /browse/CDV-49

issue_key: CDV-49
issue_numeric_sort_key: 49
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "1 Critical"
components: ""
labels: 
assignee: "nharward"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-07T16:07:47.000-0500"
updated: "2012-07-27T19:59:28.000-0400"
resolved: "2007-03-02T02:59:14.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

Motivation:
Move towards modularization to help adoption of 3rd party frameworks.

Requirements:
"Configuration Modules" refers to bundles each with a separate configuration file to be layered on top of each other.  This will make it easier for framework developers and other 3rd parties to build integrations and will make it easier for operators to deploy those integrations with Terracotta into their environments.

Application Developer/Operator --
    * List modules going to use
    * Have spot to put and/or retrieve modules
    * Be notified when Boot JAR needs rebuilding

Framework Provider --
    * Specify Terracotta configuration information (tc-config.xml) for a JAR
    * Specify dependencies
    * Tool for creating module
    * Needs version information

Allow kits to be shipped with default modules.  For example, if there is a Swing module, it should be included in the kit so that running the JTable sample application can be done out-of-the-box without rebuilding the Boot JAR.


</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-15</span>

<div markdown="1" class="comment">

There is a wiki page for developers located here: http://wiki.terracotta.org/confluence/display/wiki/Configuration+bundles

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-18</span>

<div markdown="1" class="comment">

Updating the issue name to be a little more descriptive.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-22</span>

<div markdown="1" class="comment">

Moving due date to end of last dev iteration. As this issue has many dependencies.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-02-24</span>

<div markdown="1" class="comment">

Need to rename "plugins" to "modules" per Taylor.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-03-02</span>

<div markdown="1" class="comment">

Finally!  Infrastructure support should be over at this point, save some minor detail or bug.  Work at this point should be largely on the individual modules themselves.

</div>



{% endraw %}
