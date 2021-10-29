---

title: "TC Session breaks when request is forwarded across contexts under Tomcat"
layout: issue
tags: 
permalink: /browse/CDV-29

issue_key: CDV-29
issue_numeric_sort_key: 29
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
votes:  2
watchers: 3

created: "2006-12-12T13:49:11.000-0500"
updated: "2010-03-19T18:58:59.000-0400"
resolved: ""




---

{% raw %}

## Description

<div markdown="1" class="description">

Because our Valve is installed at the Engine level, request will pass through it only once.  Therefore, we will not process the forwarded request correctly.
Details:
- the two contexts will share the same cookie (which would have to use "/" path)
- the contexts should cooperate in cooking the response correctly (e.g. what if diff contexts use diff session cookie id?)
- we have to be able to create a session for a requested session id in case of cross-context forwarded requests
- we have to be able to obtain/commit locks correctly, as request traverses all possible combinations of DSO-ized and non-DSO-ized contexts


Possible solution:
In addition to installing a Valve, we might also have to install Tomcat Session Manager


</div>

## Comments



{% endraw %}
