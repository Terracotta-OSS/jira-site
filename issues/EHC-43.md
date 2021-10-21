---

title: "The new Ehcache 1.6 distribution doesnt have jgroup class"
layout: issue
tags: 
permalink: /browse/EHC-43

issue_key: EHC-43
issue_numeric_sort_key: 43
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

created: "2009-09-21T15:07:38.000-0400"
updated: "2009-09-22T23:44:26.000-0400"
resolved: "2009-09-22T23:44:26.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

I compared to 1.5 and 1.6 ehcache.jar.  The one in 1.6 is missing this folder:  net/sf/ehcache/distribution/jgroups/\1.classes

The 1.6 distribution jar is attached and released at Thu Jun 18 2009 08:28
Please fix it.
Sourceforge Ticket ID: 2824465 - Opened By: shaoxianyang - 20 Jul 2009 20:19 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

JGroups is now a separate download and maven module. See Sourceforge downloads or click on the maven releases link.
Comment by: gregluck - 21 Jul 2009 11:57 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
