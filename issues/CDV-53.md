---

title: "Support Quartz Job Scheduler"
layout: issue
tags: 
permalink: /browse/CDV-53

issue_key: CDV-53
issue_numeric_sort_key: 53
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Open"
resolution: ""
priority: "2 Major"
components: ""
labels: 
assignee: "prodmgmt"
reporter: "jhartley"
votes:  3
watchers: 3

created: "2007-01-07T17:01:43.000-0500"
updated: "2010-03-19T19:00:34.000-0400"
resolved: ""

---



{% raw %}



## Description

<div markdown="1" class="description">

Motivation:
Target Quartz Job Scheduler (http://www.opensymphony.com/quartz/) support to increase Terracotta's transparency and add to the portfolio of Terracotta clustered frameworks.

Clustering Quartz Requirements:
   \* Fail-over - if one node is running a job, and fails, the job will run on another node
   \* Load balancing of jobs - to execute on the first (or "most") available node
   \* "Pinning" of jobs - to execute on a specific node
   \* "Ubiquitous" jobs - to execute on every node
   \* "Appropriating" of jobs - to execute on the node that has the given capabilities the job requires

Use Case:
We could implement a simple use case like a Tournament system which should cover basic scheduling capabilities and test fail-over and scalability (through load balancing).  Pinning would involve running some maintenance jobs like vacuuming a database.  Ubiquitous jobs could be something like refreshing a local (not shared) cache.  Appropriate jobs are kind of like pinning and could involve things like creating backups of a datastore.

Notes:
Clustering Quartz may be as simple as clustering RAMJobStore (http://www.opensymphony.com/quartz/api/org/quartz/simpl/RAMJobStore.html).


</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-02-28</span>

<div markdown="1" class="comment">

This simply won't happen for Moraga, no time left.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-03-01</span>

<div markdown="1" class="comment">

Assigning back to PM. We will probably want to fit this in Noriega

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-06-05</span>

<div markdown="1" class="comment">

Just adding to the mix, there is a forum post at http://forums.terracotta.org/forums/posts/list/178.page#969, Kunal has posted a sample tc-config.xml file that might just work or need a little tweaking.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-12-03</span>

<div markdown="1" class="comment">

Assigning to DRB as Nitin has said in response to an email from Orion
Yes, we should change this. I tested it for stability in many scenarios.

Nitin

-----Original Message-----
From: Orion Letizi [mailto:orion@terracotta.org]
Sent: Tuesday, November 20, 2007 1:20 AM
To: Nitin Jain
Cc: sales; fieldeng; pm
Subject: Re: [Fieldeng] Clustering Quartz

Should we change this:

https://jira.terracotta.org/jira//browse/CDV-53

and this:

http://www.terracotta.org/confluence/display/integrations/Quartz

?  Or, is this still experimental?

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-12-03</span>

<div markdown="1" class="comment">

-----Original Message-----
From: pm-bounces@terracottatech.com [mailto:pm-bounces@terracottatech.com] On Behalf Of Sreenivasan Iyer
Sent: Tuesday, November 20, 2007 10:21 AM
To: Steven Harris
Cc: Orion Letizi; pm; Nitin Jain; sales; fieldeng
Subject: Re: [Pm] [Fieldeng] Clustering Quartz

Yes. 
Centric-CRM will use this (+ 1 other customer we sent this too). We can support it for now - but  at some point we need the conversation around when (Pacheco, Quintara?) it becomes officially certified.
Thanks


----- Original Message -----
From: "Steven Harris" <steve@terracottatech.com>
To: "Orion Letizi" <orion@terracotta.org>
Cc: "pm" <pm@terracottatech.com>, "Nitin Jain" <njain@terracottatech.com>, "sales" <sales@terracotta.org>, "fieldeng" <fieldeng@terracottatech.com>
Sent: Tuesday, November 20, 2007 10:11:16 AM (GMT-0800) America/Los\_Angeles
Subject: Re: [Pm] [Fieldeng] Clustering Quartz

I think it should be in the forge and develop and mature.
That's what the forge is for :-)



</div>



{% endraw %}
