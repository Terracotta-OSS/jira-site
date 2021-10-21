---

title: "Add Rolling Upgrade Capability"
layout: issue
tags: 
permalink: /browse/CDV-8

issue_key: CDV-8
issue_numeric_sort_key: 8
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "In Progress"
resolution: ""
priority: "3 Minor"
components: "Kit & Installation,X-System Administration"
labels: 
assignee: "prodmgmt"
reporter: "drb"
votes:  0
watchers: 0

created: "2006-11-30T18:50:00.000-0500"
updated: "2010-03-19T18:59:09.000-0400"
resolved: ""

---



{% raw %}



## Description

<div markdown="1" class="description">

To facilitate minimizing scheduled downtime, support upgrading the application on the applications nodes (L1s) without stopping the cluster.

New Features:

    \1 Similar to the "onLoad" BeanShell script, add an "onSet" method that can be called when fields are mutated.
    \1 Create an "Open" mode for loading config files that allows the application node (L1) to be restated with a new and different config file. If reasonable, just rename "Development" mode to "Open" mode.
    \1 Add capability to attach a version number to objects via a new "metadata" attribute in the "<include>" tag.

Customer Steps:

    \1 Run Terracotta in "Open" mode.
    \1 Identify new fields and fields whose type has changed in the new version of the application.
    \1 For new fields, edit the existing config file to initialize the field value using custom BeanShell script off the "onLoad" method.
    \1 For fields whose type has changed, edit the config file to handle upgrading and downgrading the field value using custom BeanShell script off the "onSet" method. Optionally, leverage the metadata information to track version number.
    \1 Modify the existing config file to use custom BeanShell scripts in the "onSet" of fields where the data type has changed or other computation is required to convert old values to new values ...
    \1 fields to new...


</div>

## Comments



{% endraw %}
