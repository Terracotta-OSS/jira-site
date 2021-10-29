---

title: "straight to-disk serialization error with acegi security"
layout: issue
tags: 
permalink: /browse/EHC-61

issue_key: EHC-61
issue_numeric_sort_key: 61
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

I'm using ehcache as a cacheimplementation of springframeworks springsecurity aclCache. when the maximum number of elements in memory is reached and elements are serialized to disk this exception is thrown: 
 
2008-05-23 15:34:23,944 ERROR [net.sf.ehcache.store.DiskStore] - <aclCacheCache: Failed to write element to disk '9'. Initial cause was org.springframework.security.acls.jdbc.BasicLookupStrategy> 
java.io.NotSerializableException: org.springframework.security.acls.jdbc.BasicLookupStrategy 
 
but the class 'org.springframework.security.acls.jdbc.BasicLookupStrategy' is not part of the objectgraph that is put in cache. i've opened the aclCache.data file in a textfile editor: this file obviously contians objects that are not part of the cached object graph. 
 
ehcache seems to serialize any objects to disk not only those elements that are put in cache. How can that happen? 
I think this is a bug of ehcache not of acegi securityframework.
Sourceforge Ticket ID: 1971801 - Opened By: kongokingbongo - 25 May 2008 10:26 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
