---

title: "Initial support for GlassBox integration."
layout: issue
tags: 
permalink: /browse/CDV-27

issue_key: CDV-27
issue_numeric_sort_key: 27
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "eellis"
reporter: "ssubbiah"
votes:  0
watchers: 0

created: "2006-12-08T21:43:07.000-0500"
updated: "2012-07-27T19:59:53.000-0400"
resolved: "2006-12-18T12:57:00.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

These are the things we agreed to do for initial support for glassbox integration.

1) Object Flush/Fault monitoring.

@see RemoteObjectManagerImpl sendRequest()

requestMonitor.notifyObjectRequest(ctxt) 

This already monitors requests sent to the server. We can probably hook into that. Also we could add a new method on the requestMonitor (and rename it ?) to monitor the response along with the number of objects that got faulted (fault depth) etc.

2) Time take for lock acquire

For the first cut we decided to give high level hooks into the system. So the measurement will happen in the ManagerImpl.begin() which will be good indication of the lock acquire time.

3) Transaction commit time 

This will also be done at the ManagerImpl.commit() level. Longer commit time indicates more data in the transaction and/or server not catching up with the changes from L1s.


</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2006-12-11</span>

<div markdown="1" class="comment">

Glassfish is coming in on Wed. and Steve wants to provide them with some hooks to start at least an initial integration project.  The description outlines some higher level ideas, come talk to me first about generally how we want this to look and then Saravanan for specific details.  I created a branch at /dso/branches/glassfish-CDV-27 for this work and initialized merge support with svnmerge.py.

</div>


{:.comment-heading}
### **Tim Eck** <span class="date">2006-12-12</span>

<div markdown="1" class="comment">

BTW, this is for GlassBOX, not the fish ;-)


</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2006-12-18</span>

<div markdown="1" class="comment">

Glassbox is happy with the format of the hooks that we've provided for them.

</div>



{% endraw %}
