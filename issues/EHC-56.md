---

title: "<cache> element is not a valid element"
layout: issue
tags: 
permalink: /browse/EHC-56

issue_key: EHC-56
issue_numeric_sort_key: 56
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

created: "2009-09-21T15:07:39.000-0400"
updated: "2009-09-22T23:44:27.000-0400"
resolved: "2009-09-22T23:44:27.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

After configuring my hbm.xml files to add <cache>
element, I got an error something like this.

16:10:37,438 ERROR [XMLHelper$ErrorLogger] XMLHelper:48
- Error parsing XML: XML InputStream(17) The content of
element type "hibernate-mapping" must match
"(meta\1,import\1,(class|subclass|joined-subclass)\1,query\1,sql-query\1)".


I am using hibernate 2.1.2 and ehcache 0.7
Sourceforge Ticket ID: 934061 - Opened By: jaredflo - 13 Apr 2004 07:57 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
