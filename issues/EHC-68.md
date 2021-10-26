---

title: "getSerializedSize always returns 0"
layout: issue
tags: 
permalink: /browse/EHC-68

issue_key: EHC-68
issue_numeric_sort_key: 68
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

created: "2009-09-21T15:07:40.000-0400"
updated: "2009-09-22T23:44:28.000-0400"
resolved: "2009-09-22T23:44:28.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Element.getSerializedSize() always returns zero. 
Currently, if the Element can be serialized to a
stream, instead of returning the number of bytes sent
to the stream, the value of the variable size is
returned.  This patch will fix the problem.

Index: src/java/net/sf/ehcache/Element.java
===================================================================
RCS file:
/cvsroot/ehcache/ehcache/src/java/net/sf/ehcache/Element.java,v
retrieving revision 1.15
diff -u -r1.15 Element.java
--- src/java/net/sf/ehcache/Element.java        3 Jul
2004 07:46:34 -0000      1.15
+++ src/java/net/sf/ehcache/Element.java        8 Sep
2004 12:03:15 -0000
@@ -296,7 +296,7 @@
         try {
             oos = new ObjectOutputStream(bout);
             oos.writeObject(this);
-            return size;
+            return bout.size();
         } catch (IOException e) {
              LOG.error("Error measuring element size
for element with key " + key);
         }

Sourceforge Ticket ID: 1024334 - Opened By: nobody - 8 Sep 2004 12:03 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

This bug was reported in the forums and fixed in CVS back on the 17 
July. I have been production testing some large scale changes to the Disk 
Store pending a 1.0 release, which is why I have not done a release. 

My change has the same effect as your patch.

Sorry for the inconvenience.

Greg Luck
Comment by: gregluck - 20 Sep 2004 02:14 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
