---

title: "StackOverflowError running JSF1.1 app with DSO"
layout: issue
tags: 
permalink: /browse/CDV-39

issue_key: CDV-39
issue_numeric_sort_key: 39
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "teck"
reporter: "teck"
votes:  0
watchers: 0

created: "2007-01-04T20:16:30.000-0500"
updated: "2012-07-27T19:59:52.000-0400"
resolved: "2007-01-04T20:28:32.000-0500"

---



{% raw %}


## Attachments

* <em>teck</em> (2.000 M, application/x-zip-compressed) [jsf-cardemo.war](/attachments/CDV/CDV-39/jsf-cardemo.war)




## Description

<div markdown="1" class="description">

From forum posting (http://forums.terracotta.org/forums/posts/list/108.page)

To reproduce this, import the attached WAR (from the JSF1.1\_02 sun release) to a tomcat instance with DSO enabled

This is the cycle causing the stack overflow:
	at java.util.ArrayList.<init>(ArrayList.java:136)
	at javax.faces.component.UIComponentBase$FacetsMap.keySetIterator(UIComponentBase.java:1804)
	at javax.faces.component.UIComponentBase$FacetsMapEntrySetIterator.<init>(UIComponentBase.java:1974)
	at javax.faces.component.UIComponentBase$FacetsMapEntrySet.iterator(UIComponentBase.java:1856)
	at java.util.HashMap$KeySetWrapper.iterator(HashMap/java:482)
	at java.util.AbstractCollection.toArray(AbstractCollection.java:173)
	at java.util.ArrayList.<init>(ArrayList.java:136)

</div>

## Comments


{:.comment-heading}
### **Tim Eck** <span class="date">2007-01-04</span>

<div markdown="1" class="comment">

This issue has to do with the class javax.faces.component.UIComponentBase$FacetsMap. That map implementation (a subclass of java.util.HashMap) bases it's entrySet() iterator off of the map's keySet() iterator. The keySet() iteartor implementation in the DSO version of java.util.HashMap is based off the entrySet() iterator.  This circular dependency causes the stack overflow

</div>


{:.comment-heading}
### **Tim Eck** <span class="date">2007-01-04</span>

<div markdown="1" class="comment">

fix committed as revision 604

</div>



{% endraw %}
