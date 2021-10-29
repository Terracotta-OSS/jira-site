---

title: "MemStore.incrementIndex race condition"
layout: issue
tags: 
permalink: /browse/EHC-42

issue_key: EHC-42
issue_numeric_sort_key: 42
issuetype: "Bug"
project: "Ehcache Core"
project_key: "EHC"
status: "Closed"
resolution: "Fixed"
priority: ""
components: ""
labels: 
assignee: "drb"
reporter: "sourceforgetracker"
votes:  0
watchers: 0

created: "2009-09-21T15:07:38.000-0400"
updated: "2009-09-22T23:44:26.000-0400"
resolved: "2009-09-22T23:44:26.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

MemStore lines 503 through 511 define an  incrementIndex with a race-condition.  It's very rare, but bad-bad-bad.

I'm attaching a unit-tests which can reproduce the error randomly.
and a patch to revision: 999

Sourceforge Ticket ID: 2823211 - Opened By: mmaraist - 17 Jul 2009 17:10 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
