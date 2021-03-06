---

title: "Update Checker"
layout: issue
tags: 
permalink: /browse/CDV-45

issue_key: CDV-45
issue_numeric_sort_key: 45
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "gkeim"
reporter: "tgautier"
votes:  0
watchers: 0

created: "2007-01-05T19:21:27.000-0500"
updated: "2012-07-27T19:59:53.000-0400"
resolved: "2007-03-01T15:23:54.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

Motivation:
This feature should periodically check to see if there is an update available.  It needs to check to see if there is a new update available and notify the user - notification via logs is sufficient.  The intended goal is to identify when new updates are available to end users, with the side benefit of helping us to better understand the installed base.

Requirements:
    * System should notify end user that update check is commencing; notify in logs.
         * Example: (no update available)  
               * <...> Performing an update check
               * <...> No update is available (version is current)
         * Example (update available):
               * <...> Performing an update check
               * <...> An update to version xxxx is available.  Please visit http://www.terracotta.org
    * System should collect all relevant data
         * OS Name and Version
         * Hardware
         * JVM and Version
         * Terracotta Version
    * System should send relevant data to a script on .org
    * Script should return a response that indicates if an update is available
    * If an update is available system should notify end user in logs

The setting in the tc-config.xml file:
    * Should be off by default (if not specified in tc-config.xml file)
    * All published or distributed files should have the feature turned on (e.g. samples)
    * Any file generated by terracotta should have the feature turned on (e.g. server file generation feature)


</div>

## Comments


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

Are we going through with this? Have the requirements changed?

</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-02-15</span>

<div markdown="1" class="comment">

Gary and I have a working prototype ready for review.

</div>


{:.comment-heading}
### **Gary Keim** <span class="date">2007-03-01</span>

<div markdown="1" class="comment">

See http://wiki.terracotta.org/confluence/display/wiki/Update+check for a Nat's feature description.


</div>



{% endraw %}
