---

title: "ehcache1.2.3 give clover not found error"
layout: issue
tags: 
permalink: /browse/EHC-76

issue_key: EHC-76
issue_numeric_sort_key: 76
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

created: "2009-09-21T15:07:41.000-0400"
updated: "2009-09-22T23:44:28.000-0400"
resolved: "2009-09-22T23:44:28.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

code sample
===========
InputStream fis = null;
fis = new FileInputStream(new File
("/mysys", "properties/ehcache.xml"));
            cacheManager = new CacheManager(fis);


it throw exception
===================
java.lang.NoClassDefFoundError: com\1cenqua\1clover/g
 at net.sf.ehcache.CacheManager.<init>
(CacheManager.java:170)




From:
cheesang@yahoo.com
Sourceforge Ticket ID: 1553144 - Opened By: nobody - 6 Sep 2006 03:57 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=693320

Hi

I got an email on this and replaced the tarball with one that is identical except it 
does not include clover classes.

Greg
Comment by: gregluck - 6 Sep 2006 08:47 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
