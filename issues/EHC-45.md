---

title: "Wrong Maven snippet in the changelog.txt"
layout: issue
tags: 
permalink: /browse/EHC-45

issue_key: EHC-45
issue_numeric_sort_key: 45
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

Changelog.txt for 1.6.0 release contains invalid Maven snippet:
<dependency>
        <groupId>net.sf.ehcache</groupId>
        <artifactId>ehcache</artifactId>
        <version>1.6.0-beta5</version>
     </dependency>

Version in it shall be 1.6.0, not 16.0-beta5.
Sourceforge Ticket ID: 2807544 - Opened By: al0 - 17 Jun 2009 06:54 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
