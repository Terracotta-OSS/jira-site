---

title: "include bootjar in classpath of a project created with Eclipse plugin"
layout: issue
tags: 
permalink: /browse/CDV-99

issue_key: CDV-99
issue_numeric_sort_key: 99
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "Eclipse Plugin"
labels: 
assignee: "hhuynh"
reporter: "hhuynh"
votes:  0
watchers: 0

created: "2007-01-25T20:56:48.000-0500"
updated: "2007-02-21T19:02:00.000-0500"
resolved: "2007-02-07T13:20:10.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

Related to CDV-54

Classes in com.tc.cluster package are needed to use Cluster Membership Events feature. They can be found in bootjar. Eclipse plugin should include the bootjar in the classpath if users want to implement ClusterEventListener interface.



</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-06</span>

<div markdown="1" class="comment">

Is this for a test?

</div>


{:.comment-heading}
### **Hung Huynh** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

This issue was created under assumption we would publish Java interface for the membership cluster events but Steve has told me we use JMX instead so there's no need to include the bootjar in the classpath.

</div>



{% endraw %}
