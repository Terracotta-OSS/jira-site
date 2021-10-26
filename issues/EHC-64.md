---

title: "build script unfriendly to IDE"
layout: issue
tags: 
permalink: /browse/EHC-64

issue_key: EHC-64
issue_numeric_sort_key: 64
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
updated: "2009-09-22T23:44:27.000-0400"
resolved: "2009-09-22T23:44:27.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

If you are in an IDE, especially where the "running
directory" is not the same as the "project directory",
the build fails with:

Testcase: testCreateCacheManagerFromFile took 0 sec
        Caused an ERROR
Cannot configure CacheManager: [IDE
dir]\src\config\ehcache.xml (The system cannot find the
path specified)
net.sf.ehcache.CacheException: Cannot configure
CacheManager: [IDE dir]\src\config\ehcache.xml (The
system cannot find the path specified)
        at
net.sf.ehcache.CacheManager.configure(CacheManager.java:170)
        at
net.sf.ehcache.CacheManager.<init>(CacheManager.java:118)
        at
net.sf.ehcache.CacheManager.create(CacheManager.java:225)
        at
net.sf.ehcache.CacheManagerTest.testCreateCacheManagerFromFile(CacheManagerTest.java:91)
        [...]

Solution: set the junit task's dir attribute:

dir="$\{basedir\}"
Sourceforge Ticket ID: 1003659 - Opened By: zacjacobson - 4 Aug 2004 23:52 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

Added to CVS. This will be released with ehcache 1.0, in
August or September. Thanks for your report.
Comment by: gregluck - 8 Aug 2004 11:31 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
