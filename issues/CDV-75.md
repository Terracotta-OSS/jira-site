---

title: "Incorrect behavior on non-portable exception"
layout: issue
tags: 
permalink: /browse/CDV-75

issue_key: CDV-75
issue_numeric_sort_key: 75
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "1 Critical"
components: ""
labels: 
assignee: "teck"
reporter: "tgautier"
votes:  0
watchers: 0

created: "2007-01-17T14:46:49.000-0500"
updated: "2012-07-27T19:59:14.000-0400"
resolved: "2007-02-27T20:10:53.000-0500"

---



{% raw %}


## Attachments
  
* <em>tgautier</em> (1.000 k, application/x-gzip) [unit-npe.tgz](/attachments/CDV/CDV-75/unit-npe.tgz)
  



## Description

<div markdown="1" class="description">

1.   Attempt to share a class that we do not support.  For example a Thread.
2.  You will get a non-portable exception, which tells you to add the class into the boot jar.
3.  Add the class into the boot jar, and remake the boot jar
4.  Re-run your app.

You get the same error.

At step 3, we need to not silently ignore the fact that we don't support the particular class.

An example of a class that works properly is java.lang.Thread (the exception properly reports that Thread is a system resource which cannot be shared).  An example of a class which does not work properly is java.util.concurrent.Semaphore.

</div>

## Comments


{:.comment-heading}
### **Taylor Gautier** <span class="date">2007-01-17</span>

<div markdown="1" class="comment">

The attached unit test creates a root with a holder which holds a java.util.concurrent.Semaphore.

To use the project, compile it with maven2:
> mvn package

To run with terracotta, install Terracotta 2.2+, and start a demo server:
> $TC\1HOME/dso/samples/start-demo-server.sh

Start the app (cd into the target directory):
target> java `$TC\1HOME/dso/bin/dso-env.sh` -Dtc.config=resources/conf/tc-config.xml -classpath classes unit.test.Main

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

Updating Due date to last day of Moraga dev iteration

</div>


{:.comment-heading}
### **Tim Eck** <span class="date">2007-02-27</span>

<div markdown="1" class="comment">

for all the various java.util.concurrent classes (and those in subpackages like locks and atomic), I added permanent exclude rules so that we won't suggest adding any of these types to the boot jar until we official support them

</div>



{% endraw %}
