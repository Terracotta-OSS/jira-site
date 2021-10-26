---

title: "Combine Solaris and Linux Kits"
layout: issue
tags: 
permalink: /browse/CDV-40

issue_key: CDV-40
issue_numeric_sort_key: 40
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "Build & Test"
labels: 
assignee: "juris"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-05T17:59:21.000-0500"
updated: "2012-07-27T19:59:30.000-0400"
resolved: "2007-02-06T19:36:19.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Requirements:
   \* Build Unix/Linux kit without a JRE
   \* Include both Solaris and Unix Boot JARs in dso-boot folder (4 total files)
       * dso-boot-hotspot_solaris_142_13.jar
       * dso-boot-hotspot_solaris_150_10.jar
       * dso-boot-hotspot_linux_142_13.jar
       * dso-boot-hotspot_linux_150_10.jar

   \* TC-Welcome auto-gens boot jar if needed
   \* Update Installation section user guides with instructions on setting and/or verifying JAVA\_HOME and TC\_JAVA\_HOME.
   \* Update web site to refer to "Unix/Linux" kits.


</div>

## Comments


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-10</span>

<div markdown="1" class="comment">

test

</div>



{% endraw %}
