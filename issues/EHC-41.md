---

title: "Ehcache returns old value in case of quick value updates"
layout: issue
tags: 
permalink: /browse/EHC-41

issue_key: EHC-41
issue_numeric_sort_key: 41
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

When I do a lot of quick value updates, the get issued sometimes returns old value, instead of the most recent one.

I've attached a JUnit example, please run it a few times, as it works from time to time.
Sourceforge Ticket ID: 2824181 - Opened By: data-loss - 20 Jul 2009 10:08 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
