---

title: "TC Session is probably broken when Tomcats are load-balanced using Jvm Rout and session cookie re-write"
layout: issue
tags: 
permalink: /browse/CDV-30

issue_key: CDV-30
issue_numeric_sort_key: 30
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Open"
resolution: ""
priority: "2 Major"
components: "Sessions"
labels: 
assignee: "prodmgmt"
reporter: "alex"
votes:  0
watchers: 0

created: "2006-12-12T13:51:53.000-0500"
updated: "2010-03-19T19:00:17.000-0400"
resolved: ""

---



{% raw %}



## Description

<div markdown="1" class="description">

When we currently manage (read/write/parse) Session Cookie we don't take into account possible optional fields containing JVM Rout info used to mod\1jk load balancing implementation

</div>

## Comments



{% endraw %}
