---

title: "NPE in admin console"
layout: issue
tags: 
permalink: /browse/CDV-58

issue_key: CDV-58
issue_numeric_sort_key: 58
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "Console"
labels: 
assignee: "gkeim"
reporter: "teck"
votes:  0
watchers: 0

created: "2007-01-10T17:38:48.000-0500"
updated: "2012-07-27T19:59:47.000-0400"
resolved: "2007-02-16T18:44:59.000-0500"

---



{% raw %}


## Attachments

* <em>teck</em> (4.000 k, text/plain) [threads.txt](/attachments/CDV/CDV-58/threads.txt)




## Description

<div markdown="1" class="description">

Got this NPE trying to refresh the "Classes" item in the admin console. Afterwards console seemed hung (threads attached)

Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
	at com.tc.admin.dso.ClassTreeRoot.setClassInfo(ClassTreeRoot.java:31)
	at com.tc.admin.dso.ClassTreeModel.setClassInfo(ClassTreeModel.java:16)
	at com.tc.admin.dso.ClassesPanel.refresh(ClassesPanel.java:71)
	at com.tc.admin.dso.ClassesNode.refresh(ClassesNode.java:52)
	at com.tc.admin.dso.ClassesNode$RefreshAction.actionPerformed(ClassesNode.java:65)
	at com.tc.admin.dso.ClassesNode.nodeClicked(ClassesNode.java:70)
	at com.tc.admin.AdminClientPanel$4.mouseClicked(AdminClientPanel.java:137)
	at java.awt.AWTEventMulticaster.mouseClicked(AWTEventMulticaster.java:212)
	at java.awt.Component.processMouseEvent(Component.java:5504)
	at javax.swing.JComponent.processMouseEvent(JComponent.java:3135)
	at java.awt.Component.processEvent(Component.java:5266)
	at java.awt.Container.processEvent(Container.java:1966)
	at java.awt.Component.dispatchEventImpl(Component.java:3968)
	at java.awt.Container.dispatchEventImpl(Container.java:2024)
	at java.awt.Component.dispatchEvent(Component.java:3803)
	at java.awt.LightweightDispatcher.retargetMouseEvent(Container.java:4212)
	at java.awt.LightweightDispatcher.processMouseEvent(Container.java:3901)
	at java.awt.LightweightDispatcher.dispatchEvent(Container.java:3822)
	at java.awt.Container.dispatchEventImpl(Container.java:2010)
	at java.awt.Window.dispatchEventImpl(Window.java:1778)
	at java.awt.Component.dispatchEvent(Component.java:3803)
	at java.awt.EventQueue.dispatchEvent(EventQueue.java:463)
	at java.awt.EventDispatchThread.pumpOneEventForHierarchy(EventDispatchThread.java:242)
	at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:163)
	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:157)
	at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:149)
	at java.awt.EventDispatchThread.run(EventDispatchThread.java:110)

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-16</span>

<div markdown="1" class="comment">

Gary says this is fixed

</div>



{% endraw %}
