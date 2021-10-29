---

title: "ehcache DiskStore has issues when used in Tomcat"
layout: issue
tags: 
permalink: /browse/EHC-93

issue_key: EHC-93
issue_numeric_sort_key: 93
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

created: "2009-09-21T15:07:43.000-0400"
updated: "2009-09-22T23:44:29.000-0400"
resolved: "2009-09-22T23:44:29.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

When used in a server context, like Tomcat, for
instance, retrieving objects from the DiskStore can
throw errors. This is due to way in which objects get
resolved by the class loader - in Tomcat, for instance,
each webapp has its own classloader. If you put local
objects loaded by the webapp's class loader into the
disk cache, ehcache throws an error on retrieval.

A simple fix is to redefine the resolveClass method of
ObjectInputStream to get the contextClassLoader of the
currentThread. A patch file is included for
DiskStore.java that makes this change, and fixes this
issue. (Hopefully the patch works - if not, the code
changes are very small)

- ben houston
- ben@collegenet.com
Sourceforge Ticket ID: 1324221 - Opened By: nobody - 11 Oct 2005 21:03 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

Ben

The patch looks good. Thanks.

It is in CVS HEAD.

Regards
Greg
Comment by: gregluck - 16 Oct 2005 00:04 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
