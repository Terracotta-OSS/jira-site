---

title: "In Terracotta DSO kit in sample application launcher after starting the second instance of chatteer application it is showing the warning java.io.IOException"
layout: issue
tags: 
permalink: /browse/CDV-25

issue_key: CDV-25
issue_numeric_sort_key: 25
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "Sample Apps"
labels: 
assignee: "juris"
reporter: "mbansal"
votes:  0
watchers: 0

created: "2006-12-07T16:00:38.000-0500"
updated: "2012-07-27T19:59:54.000-0400"
resolved: "2007-02-13T14:58:50.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

1.Downloaded the revision #272 of Terracotta DSO from trunk.
2. Started the Sample Application launcher from console with tc-sample.bat file.
3. When started the second instance of chatter application it is showing the warning.

WARNING: Failed to restart: java.io.IOException: The connector is not at the connection state.
4. application is working fine.
5. In the Admin console too expanding the tree of chatter application, nodes are fine with information.

Expected no warning.


</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-08</span>

<div markdown="1" class="comment">

Please review to verify that this is still an issue

</div>


{:.comment-heading}
### **Juris Galang** <span class="date">2007-02-13</span>

<div markdown="1" class="comment">

not an issue anymore.

</div>



{% endraw %}
