---

title: "Add ability to read snippets of tc-config.xml files in configuration plugins"
layout: issue
tags: 
permalink: /browse/CDV-98

issue_key: CDV-98
issue_numeric_sort_key: 98
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "1 Critical"
components: "X-Common Code"
labels: 
assignee: "ekulesho"
reporter: "nharward"
votes:  0
watchers: 0

created: "2007-01-25T18:24:19.000-0500"
updated: "2012-07-27T19:59:24.000-0400"
resolved: "2007-02-27T13:01:53.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Part of the configuration plugins project is allowing plugins to either a) use a programmatic interface to do their configuration (useful for custom class adapters) or b) to pre-package a file describing the configuration.  This is for b).

The user should be able to specify a subset of tc-config.xml (starting at the <application> element) and specify a path to this resource via the "Terracotta-Instrumentation" manifest header attribute.

</div>

## Comments


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-02-05</span>

<div markdown="1" class="comment">

This is implemented. 

Nat, do we need any more work on this?

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-02-06</span>

<div markdown="1" class="comment">

If we know it works, then no.  Jason and Juris are busy working on the build system to support tests within plugin modules, when they are ready just write a test for this and close it out.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-23</span>

<div markdown="1" class="comment">

Moving due date to end of iteration 4 (last dev iteration). Will this be done by then?

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-02-27</span>

<div markdown="1" class="comment">

This was fixed about a week ago -- snippets of XML files are currently used in the clustered-commons-collections-3.1 module.

</div>



{% endraw %}
