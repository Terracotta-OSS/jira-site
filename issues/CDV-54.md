---

title: "Cluster Events and Node Data"
layout: issue
tags: 
permalink: /browse/CDV-54

issue_key: CDV-54
issue_numeric_sort_key: 54
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "DSO:L1,DSO:L2"
labels: 
assignee: "alex"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-08T00:27:44.000-0500"
updated: "2012-07-27T19:59:23.000-0400"
resolved: "2007-02-02T12:09:42.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Motivation:
Provide access to cluster events and node data to enable integrations to custom cluster aware features and various projects with multi-node support. For example, some new Tomcat integrations may require this capability.

Requirements:
   \1 Make cluster events accessible to developer and/or operator:
       \1 Notify when a node joins or leaves the cluster.
       \1 Notify L1 to L2 connect timeout
           \1 Default handler is to exit
           \1 Time is configurable
       \1 Notify when L1 to L2 connection established
       \1 Notify when L1 to L2 connection closed
       \1 Notify when L1 to L2 connection error
   \1 User application must have access to a data structure or can request a data structure that represents the cluster members

Optional:
   \1 All default handlers can be overridden (depends somewhat on if the implementation has the notion of 'handlers').
   \1 Make node list and status available via JMX -- both viable via management consoles and by developers from their applications.
   \1 Allow attaching meta-data (an arbitrary object) for each node.  For example, support custom routing based on info like node name or node capacity.

Use Case:
An e-commerce company wants to integrate a custom load balancer with Terracotta such that Terracotta will notify the load balancer when an application server leaves the cluster.  The load balancer may then take the desired action such as re-booting the application server or paging the appropriate operations personnel.


</div>

## Comments



{% endraw %}
