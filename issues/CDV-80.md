---

title: "Errors when using tc-admin console to connect to L2 on RHEL4"
layout: issue
tags: 
permalink: /browse/CDV-80

issue_key: CDV-80
issue_numeric_sort_key: 80
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "3 Minor"
components: "Console"
labels: 
assignee: "qa"
reporter: "alex"
votes:  0
watchers: 0

created: "2006-08-24T15:21:13.000-0400"
updated: "2013-02-12T14:02:23.000-0500"
resolved: "2007-06-08T13:39:21.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

the following exceptions were thrown when clicking Connect button on the console:

WARNING: Could not lock User prefs. Unix error code 5.
java.util.prefs.BackingStoreException: Couldn't get file lock.
        at java.util.prefs.FileSystemPreferences.sync(Unknown Source)
        at java.util.prefs.FileSystemPreferences.flush(Unknown Source)
        at com.tc.admin.common.PrefsHelper.flush(PrefsHelper.java:43)
        at com.tc.admin.common.XObjectTable.storeSortPrefs(XObjectTable.java:383)
        at com.tc.admin.common.XObjectTable.setSortColumn(XObjectTable.java:159)
        at com.tc.admin.dso.ClassesTable.<init>(ClassesTable.java:13)
        at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        at sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)
        at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)
        at java.lang.reflect.Constructor.newInstance(Unknown Source)
        at java.lang.Class.newInstance0(Unknown Source)
        at java.lang.Class.newInstance(Unknown Source)
        at visix.galaxy.ComponentResource.newComponent(ComponentResource.java:225)
        at visix.galaxy.ComponentResource.newComponent(ComponentResource.java:207)
        at visix.galaxy.ComponentResource.newComponent(ComponentResource.java:243)
        at visix.galaxy.ComponentResource.safeNewComponent(ComponentResource.java:230)
        at visix.galaxy.ScrollPane.load(ScrollPane.java:44)
        at visix.galaxy.ScrollPane.load(ScrollPane.java:35)
        at visix.galaxy.ComponentResource.newComponent(ComponentResource.java:214)
        at visix.galaxy.ComponentResource.newComponent(ComponentResource.java:243)
        at visix.galaxy.ComponentResource.safeNewComponent(ComponentResource.java:230)
        at visix.galaxy.TabbedPane.load(TabbedPane.java:45)
        at visix.galaxy.TabbedPane.load(TabbedPane.java:24)
        at visix.galaxy.ComponentResource.newComponent(ComponentResource.java:214)
        at visix.galaxy.ComponentResource.newComponent(ComponentResource.java:243)
        at visix.galaxy.ComponentResource.safeNewComponent(ComponentResource.java:230)
        at visix.galaxy.ContainerSupport.load(ContainerSupport.java:21)
        at visix.galaxy.Container.load(Container.java:62)
        at com.tc.admin.dso.ClassesPanel.<init>(ClassesPanel.java:31)
        at com.tc.admin.dso.ClassesNode.<init>(ClassesNode.java:24)
        at com.tc.admin.dso.DSONode.<init>(DSONode.java:25)
        at com.tc.admin.ServerNode.handleActivation(ServerNode.java:556)
        at com.tc.admin.ServerNode.setConnected(ServerNode.java:188)
        at com.tc.admin.ServerNode.access$300(ServerNode.java:45)
        at com.tc.admin.ServerNode$1.run(ServerNode.java:111)
        at java.awt.event.InvocationEvent.dispatch(Unknown Source)
        at java.awt.EventQueue.dispatchEvent(Unknown Source)
        at java.awt.EventDispatchThread.pumpOneEventForHierarchy(Unknown Source)
        at java.awt.EventDispatchThread.pumpEventsForHierarchy(Unknown Source)
        at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
        at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
        at java.awt.EventDispatchThread.run(Unknown Source)
Aug 24, 2006 12:18:06 PM java.util.prefs.FileSystemPreferences checkLockFile0ErrorCode


</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2006-09-28</span>

<div markdown="1" class="comment">

Consider not using Java preferences and don't store preferences in dot file

</div>


{:.comment-heading}
### **Taylor Gautier** <span class="date">2007-01-19</span>

<div markdown="1" class="comment">

We should catch this exception and give a better error message to indicate the problem.  

</div>


{:.comment-heading}
### **Hung Huynh** <span class="date">2007-01-26</span>

<div markdown="1" class="comment">

Related issue: https://jira.terracotta.org/jira/browse/IT-211

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-05-31</span>

<div markdown="1" class="comment">

Add a better error message

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-06-08</span>

<div markdown="1" class="comment">

Can we reproduce this issue?

</div>


{:.comment-heading}
### **Hung Huynh** <span class="date">2007-06-08</span>

<div markdown="1" class="comment">

This is known hardware problem. We can't run on shared network drive. Set data and logs folder in tc-config.xml to point to local folders instead (ie, /tmp )

</div>



{% endraw %}
