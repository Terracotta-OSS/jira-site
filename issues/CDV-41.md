---

title: "Consolidate 3 Products into Single Kit"
layout: issue
tags: 
permalink: /browse/CDV-41

issue_key: CDV-41
issue_numeric_sort_key: 41
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "juris"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-05T18:08:05.000-0500"
updated: "2012-07-27T19:59:43.000-0400"
resolved: "2007-02-06T19:36:54.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

To reduce effort required to build, test, and post kits/installers, combine existing 3 products (Terracotta Sessions, Terracotta Spring, and Terracotta DSO) into a single product called Open Terracotta.

New Kit Structure
-----------------------
/terracotta-2.3
   disclaimer.txt
   license.txt
   readme.txt
   releasenotes.txt
   thirdpartylicenses.txt
   /bin
      dso-env.sh
      admin.sh
      samples.sh
      welcome.sh
   /lib
      /dso-boot
   /config-examples
      tc-config-reference.xml
   /modules
      /<module-name>\1<version>
         /config-examples
            tc-config-<module-name>.xml
         /docs
            Terracotta<module-name>Guide.html
         /lib
         /samples
            /sample1
         /tools
            run-something.sh
      /org.terracotta.core-dso\12.3.0  (module or not?)
      /org.terracotta.pojos\12.3.0
         /config-examples
            tc-config-dso.xml
         /docs
            TerracottaDSOGuide.html
         /samples
            /jtable
            /sharededitor
      /org.terracotta.sessions\12.3.0
         /config-examples
            tc-config-sessions.xml
            tc-config-sessions-tomcat.xml
            tc-config-sessions-weblogic.xml
         /docs
            TerracottaSessionsQuickStartGuide.html
         /tools
            configurator.sh
         /samples
            /cart
            /townsend
         /external
            /tomcat5.5
         /sandbox
            /bin
            /tomcat5.0
            /tomcat5.5
            /wls8.1
      /org.terracotta.spring\12.3.0
         /config-examples
            tc-config-spring.xml
         /docs
            TerracottaSpringGuide.html
         /samples
            /jmx
   /schema

For the latest kit structure, consult the PRD.

The default Windows install folder is:
   \Program Files\Terracotta\terracotta-2.3


</div>

## Comments



{% endraw %}
