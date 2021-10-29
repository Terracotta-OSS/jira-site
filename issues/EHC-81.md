---

title: "URL decode for url.getFile"
layout: issue
tags: 
permalink: /browse/EHC-81

issue_key: EHC-81
issue_numeric_sort_key: 81
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

created: "2009-09-21T15:07:42.000-0400"
updated: "2009-09-22T23:44:29.000-0400"
resolved: "2009-09-22T23:44:29.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

In Configurator.java, method urlToFileInputStream, 

the url needs to be decoded when getting the3 file

Intead of File file = new File(url.getFile());

needs to be 

File file = new File(URLDecoder.decode(url.getFile
(),"UTF-8"));


Sourceforge Ticket ID: 1338846 - Opened By: shishirksingh - 26 Oct 2005 20:40 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
