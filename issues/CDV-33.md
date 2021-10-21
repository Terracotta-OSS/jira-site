---

title: "remove dependency from dom4j-1.6.1.jar in the aspectwerkz module"
layout: issue
tags: 
permalink: /browse/CDV-33

issue_key: CDV-33
issue_numeric_sort_key: 33
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "X-AspectWerkz"
labels: 
assignee: "ekulesho"
reporter: "juris"
votes:  0
watchers: 0

created: "2006-12-12T19:14:59.000-0500"
updated: "2012-07-27T19:59:17.000-0400"
resolved: "2007-01-05T23:07:14.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

This is somewhat related to LKC-3237 - because of the way we build the tc.jar for the kits, we could only specify whether we should include the content of the module's lib/ directory or not. 

</div>

## Comments


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-01-05</span>

<div markdown="1" class="comment">

Committed to 2.2.1 and to the trunk.

Ironically, we still need dom4j jar to run HibernateTest from spring-system-tests module. So, I moved dependency up there.

</div>



{% endraw %}
