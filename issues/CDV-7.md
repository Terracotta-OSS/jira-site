---

title: "Add Static Instrumentation Capability"
layout: issue
tags: 
permalink: /browse/CDV-7

issue_key: CDV-7
issue_numeric_sort_key: 7
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Reopened"
resolution: ""
priority: "3 Minor"
components: "Build & Test"
labels: 
assignee: "prodmgmt"
reporter: "drb"
votes:  0
watchers: 1

created: "2006-11-30T18:47:36.000-0500"
updated: "2010-03-19T18:58:57.000-0400"
resolved: ""




---

{% raw %}

## Description

<div markdown="1" class="description">

Because it's adding an additional build step, it will be critical that it be as simple as possible to use. An idea would be to expose a simple post-processing compiler that scans all of the classfiles in a given directory or jarfile to find the ones that match DSO metadata (either from a config or from annotations embedded in the classfiles themselves). The tool would then simply munge the classfiles in place to add the necessary DSO instrumentation.

The tool could be exposed as a custom ant task that users would simply run after <javac>.

For example:

<target ...>
  <javac srcdir='.' destdir='/classes'/>
  <dso-compile classdir='/classes' />
</target>

Reasons

    * App startup time reduced since instrumentation doesn't have to happen at runtime
    * Some amount of static error and consistency checking becomes possible, improved dev experience
    * Simplified deployment: conceivably could allow us to eliminate bootjar/-Xbootclasspath requirement


</div>

## Comments


{:.comment-heading}
### **Radim Tlusty** <span class="date">2007-03-30</span>

<div markdown="1" class="comment">

This could be a very helpful feature. I meet in my project quit significant slowdown of application startup, because of big amount of classes. Where can I find the implementation of this issue?

</div>


{:.comment-heading}
### **Tim Eck** <span class="date">2007-03-30</span>

<div markdown="1" class="comment">

unless this item was converted into another jira item, this has not been implemented and thus shouldn't be resolved/fixed status. If this item is superceded by another, please link the items when closing

</div>


{:.comment-heading}
### **orion** <span class="date">2007-04-01</span>

<div markdown="1" class="comment">

Radim, have you already tried to narrow the set of classes that are being instrumented:

This section of the documentation describes how to do it:

http://www.terracotta.org/confluence/display/docs1/Configuration+Guide+and+Reference#ConfigurationGuideandReference-%2Ftc%3Atcconfig%2Fapplication%2Fdso%2Finstrumentedclasses



</div>


{:.comment-heading}
### **Radim Tlusty** <span class="date">2007-04-06</span>

<div markdown="1" class="comment">

We have performed test with our application with several configurations. The size of our projects is about 10.000 classes.

The results of startup of application were following:
-- plain Java without Terracotta - 28s
-- current minimum of instrumented classes: 47s (can be still optimized)
-- all classes instrumented: 88s

The current amount of instrumented classes is approximately 100.

I will try to use profiler to see, where is the biggest bottleneck.

</div>



{% endraw %}
