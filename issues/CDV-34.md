---

title: "Allow the ability to use a 1.6 JDK"
layout: issue
tags: 
permalink: /browse/CDV-34

issue_key: CDV-34
issue_numeric_sort_key: 34
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "nharward"
reporter: "nharward"
votes:  0
watchers: 0

created: "2006-12-13T14:09:39.000-0500"
updated: "2012-07-27T19:59:41.000-0400"
resolved: "2006-12-13T20:29:35.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

Currently when using Java 6 the following happens when running the server:

java.lang.NullPointerException
    at com.tc.management.AbstractTerracottaMBean.getNotificationInfo(AbstractTerracottaMBean.java:70)
    at com.sun.jmx.mbeanserver.MBeanIntrospector.findNotifications(MBeanIntrospector.java:415)
    at com.sun.jmx.mbeanserver.MBeanIntrospector.getMBeanInfo(MBeanIntrospector.java:362)
    at com.sun.jmx.mbeanserver.MBeanSupport.<init>(MBeanSupport.java:148)
    at com.sun.jmx.mbeanserver.StandardMBeanSupport.<init>(StandardMBeanSupport.java:81)
    at javax.management.StandardMBean.construct(StandardMBean.java:169)
    at javax.management.StandardMBean.<init>(StandardMBean.java:220)
    at com.tc.management.AbstractTerracottaMBean.<init>(AbstractTerracottaMBean.java:46)
    at com.tc.management.AbstractTerracottaMBean.<init>(AbstractTerracottaMBean.java:41)
    at com.tc.management.beans.object.ObjectManagementMonitor.<init>(ObjectManagementMonitor.java:20)
    at com.tc.management.L2Management.<init>(L2Management.java:45)
    at com.tc.objectserver.impl.DistributedObjectServer.startJMXServer(DistributedObjectServer.java:676)
    at com.tc.objectserver.impl.DistributedObjectServer.start(DistributedObjectServer.java:228)
    at com.tc.server.TCServerImpl.startDSOServer(TCServerImpl.java:357)
    at com.tc.server.TCServerImpl.startServer(TCServerImpl.java:299)
    at com.tc.server.TCServerImpl.start(TCServerImpl.java:134)
    at com.tc.server.TCServerMain.main(TCServerMain.java:26)

</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2006-12-13</span>

<div markdown="1" class="comment">

Allows the Terracotta Server to be experimentally run with a 1.6 JDK; building the kit still demands a 1.5 JDK, however.

</div>



{% endraw %}
