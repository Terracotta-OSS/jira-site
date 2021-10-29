---

title: "Configure using Java Annotations"
layout: issue
tags: 
permalink: /browse/CDV-13

issue_key: CDV-13
issue_numeric_sort_key: 13
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "3 Minor"
components: "X-Common Code"
labels: 
assignee: "prodmgmt"
reporter: "drb"
votes:  0
watchers: 0

created: "2006-11-30T19:18:13.000-0500"
updated: "2012-07-27T20:00:51.000-0400"
resolved: "2007-09-17T18:42:11.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

Support a DSO programming model based on JSR 175 annotations as an alternative configuration mechanism for DSO metadata. Instead of writing tc-config.xml, DSO developers would declare metadata inline in their code using the standard Java mechanism for Java code metadata.

For example:

import com.terracotta.annotations.Root;

public class ECommerceClient \{

@Root public static Inventory;

\}

And:

import com.terracotta.annotations.Shareable;
import com.terracotta.annotations.WriteLock;

@Shareable(honorTransient=true) public class Inventory \{

@WriteLock public void doImportantUpdateStuff() \{ ... \}

\}

Note that annotations would be supported in addition to the existing XML configuration. XML will always be required for operational settings (e.g. listen ports, logs, and so forth). Plus, we need to continue to support the drop-in use case which can only be accomplished via an external configuration file.
Reasons:

    * Developers like annotations
    * It's low-hanging fruit
    * Pretty much every framework that manages POJOs works this way
    * JBoss Cache AOP does this (PojoCache)
    * Demos involving code will be 10x more clear and powerful
    * This is where the money is

Reference  - Support JSR 175 annotations for DSO meta data: LKC-451 (Terraotta internal development Jira issue)

</div>

## Comments


{:.comment-heading}
### **Taylor Gautier** <span class="date">2007-09-17</span>

<div markdown="1" class="comment">

Most functionality for annotations is implemented.

</div>



{% endraw %}
