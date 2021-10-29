---

title: "Support Quartz Job Scheduler"
layout: issue
tags: 
permalink: /browse/CDV-16

issue_key: CDV-16
issue_numeric_sort_key: 16
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Duplicate"
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "drb"
reporter: "drb"
votes:  0
watchers: 1

created: "2006-11-30T19:29:04.000-0500"
updated: "2012-07-27T19:59:30.000-0400"
resolved: "2007-04-22T22:52:51.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">


Support clustering of Quartz:

    * Request came from a couple of sources including Rob Harrop (cofounder of Interface21). I think the primary requirements are:

    * 1. Availaiblity:
                + Cluster the job schedules (so machine hosting job schedule is not SPOF)
                + Machine excuting job is not SPOF - i.e. master restarts work when node recovers.
    * 2. JobPersistence:
                + Replace RAMJobStore with DSO based implementation: This allows the scheduling engine to work without a database. "RAMJobStore is the simplest JobStore to use, it is also the most performant (in terms of CPU time). RAMJobStore gets its name in the obvious way: it keeps all of its data in RAM. This is why it's lightning-fast, and also why it's so simple to configure. The drawback is that when your application ends (or crashes) all of the scheduling information is lost - this means RAMJobStore cannot honor the setting of "non-volatility" on jobs and triggers. For some applications this is acceptable - or even the desired behavior, but for other applications, this may be disasterous."
    * 3. Transactions:
                + Quartz can participate in JTA transactions. We can't support now, but another data-point around that.
    * 4. Monitoring:
                + Cluster-wide monitoring and Stats.


</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-04-22</span>

<div markdown="1" class="comment">

Superseded by CDV-53.  Probably this issue should have been updated instead of creating a new one, but oh well :)

</div>



{% endraw %}
