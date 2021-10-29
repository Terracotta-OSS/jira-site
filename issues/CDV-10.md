---

title: "Updating Web Config for Using the Filter Specification"
layout: issue
tags: 
permalink: /browse/CDV-10

issue_key: CDV-10
issue_numeric_sort_key: 10
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "In Progress"
resolution: ""
priority: "3 Minor"
components: ""
labels: 
assignee: "prodmgmt"
reporter: "drb"
votes:  0
watchers: 0

created: "2006-11-30T19:09:41.000-0500"
updated: "2010-03-19T18:59:47.000-0400"
resolved: ""




---

{% raw %}

## Description

<div markdown="1" class="description">

Use the filter specification allows us to be much less tightly coupled to the servlet server internals (specifically class loaders). However, we'll now need to update the web.xml with information to hook Terracotta into the filter. Having customer edit their own web.xml file is technically the easiest route, but it is an additional step (and a tad inconvenient if the web.xml file is already in a WAR).

</div>

## Comments



{% endraw %}
