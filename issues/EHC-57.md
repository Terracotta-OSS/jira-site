---

title: "Cache not alive"
layout: issue
tags: 
permalink: /browse/EHC-57

issue_key: EHC-57
issue_numeric_sort_key: 57
issuetype: "Bug"
project: "Ehcache Core"
project_key: "EHC"
status: "Closed"
resolution: "Fixed"
priority: ""
components: ""
labels: 
assignee: "drb"
reporter: "sourceforgetracker"
votes:  0
watchers: 0

created: "2009-09-21T15:07:39.000-0400"
updated: "2009-09-22T23:44:27.000-0400"
resolved: "2009-09-22T23:44:27.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

When i use ehcache with Hibernate 2.1.1 i have 
following issue

1) create Session factory 
2) close session factory 
3) crete new sessin factory (sthe same or other DB) 
4) try to use - Exceptinl occurs (listed bellow) 

It seems to ehcache hase 1 singleton instance and not 
reinit for new factory. (old instance has ststus 
NOT\1ALIVE) 

Exception: 
java.lang.IllegalStateException 
The com.enterra.surv.po.User.surveys Cache is not 
alive. 
Stack Trace: 
net.sf.ehcache.Cache.checkStatus(Cache.java:437) 
net.sf.ehcache.Cache.get(Cache.java:217) 
net.sf.ehcache.hibernate.Plugin.get(Plugin.java:119) 
net.sf.hibernate.cache.ReadWriteCache.get
(ReadWriteCache.java:71) 
net.sf.hibernate.impl.SessionImpl.getCachedCollection
(SessionImpl.java:3763) 
net.sf.hibernate.impl.SessionImpl.getCollection
(SessionImpl.java:3739) 
net.sf.hibernate.type.PersistentCollectionType.resolveIde
ntifier(PersistentCollectionType.java:181) 
net.sf.hibernate.impl.SessionImpl.initializeEntity
(SessionImpl.java:2132) 
net.sf.hibernate.loader.Loader.doQuery
(Loader.java:239) 
net.sf.hibernate.loader.Loader.doQueryAndInitializeNonLa
zyCollections(Loader.java:132) 
net.sf.hibernate.loader.Loader.doList(Loader.java:949) 
net.sf.hibernate.loader.Loader.list(Loader.java:940) 
net.sf.hibernate.hql.QueryTranslator.list
(QueryTranslator.java:833) 
net.sf.hibernate.impl.SessionImpl.find
(SessionImpl.java:1475) 
net.sf.hibernate.impl.SessionImpl.find
(SessionImpl.java:1454) 
... 


Sourceforge Ticket ID: 896250 - Opened By: barnaul - 13 Feb 2004 03:42 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
