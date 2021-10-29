---

title: "Determine impact of class loading behaviour re: configuration bundles"
layout: issue
tags: 
permalink: /browse/CDV-73

issue_key: CDV-73
issue_numeric_sort_key: 73
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "X-Common Code"
labels: 
assignee: "nharward"
reporter: "nharward"
votes:  0
watchers: 0

created: "2007-01-15T23:48:40.000-0500"
updated: "2012-07-27T19:59:19.000-0400"
resolved: "2007-01-24T21:19:30.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

Configuration bundles will require an additional class loading layer to be injected into the DSO client runtime as they are dynamic; this issue is to track the potential problems and issues regarding this.  Currently there is no implementation, but Equinox and Knopflerfish are leading contenders in this area as OSGi implementations, we might or might not roll our own as well.

</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-24</span>

<div markdown="1" class="comment">

This does not really appear to be an issue (we have chosen Knopflerfish as our implementation) -- the OSGi framework stipulates very strict and very isolated class loading rules for a bundle, and we have configured Knopflerfish to delegate unknown classes to the same loader that any other DSO client code would use.  As we are not explicitly exporting any com.tc packages into the OSGi framework, this delegation is necessary but again, does not appear to cause any problems or behave in unexpected ways.

</div>



{% endraw %}
