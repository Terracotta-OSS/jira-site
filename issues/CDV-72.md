---

title: "Determine impact of configuration bundles on the kit structure"
layout: issue
tags: 
permalink: /browse/CDV-72

issue_key: CDV-72
issue_numeric_sort_key: 72
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "Kit & Installation"
labels: 
assignee: "nharward"
reporter: "nharward"
votes:  0
watchers: 0

created: "2007-01-15T23:45:28.000-0500"
updated: "2012-07-27T19:59:38.000-0400"
resolved: "2007-01-24T21:21:01.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

The configuration bundles project will introduce the concept of plugins to the Terracotta runtime, initially just for instrumentation recipes but to be extended later.  This impacts the kit structure, as "standard" plugins need a place to live in the final kit and in our testing environment.  Suggestions for how to best package plugins should be documented here.

</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-24</span>

<div markdown="1" class="comment">

After some meetings/discussions around this, we appear to have settled on having a top level "plugins" directory in the distribution, which will contain JAR files for "standard" plugins that Terracotta ships with.  This will be the primary location to search for plugins, with the configuration file allowing any number of additional repositories to be used as supplements.

</div>



{% endraw %}
