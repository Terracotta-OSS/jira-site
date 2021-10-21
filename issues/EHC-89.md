---

title: "ehcache-1.2.3.jar has dependency on Clover"
layout: issue
tags: 
permalink: /browse/EHC-89

issue_key: EHC-89
issue_numeric_sort_key: 89
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

created: "2009-09-21T15:07:42.000-0400"
updated: "2009-09-22T23:44:29.000-0400"
resolved: "2009-09-22T23:44:29.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

The ehcache-1.2.3.jar classes contain a runtime
dependency on Clover.  It appears that this has
happened a number of times:
http://sourceforge.net/tracker/index.php?func=detail&aid=1553144&group\1id=93232&atid=603559
http://sourceforge.net/tracker/index.php?func=detail&aid=1422906&group\1id=93232&atid=603559

[CLOVER] FATAL ERROR: Clover could not be initialised.
Are you sure you have Clover in the runtime classpath?
(class
java.lang.NoClassDefFoundError:com\1cenqua\1clover/CloverVersionInfo)
java.lang.reflect.InvocationTargetException
        at
sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at
sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
        at
sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
        at java.lang.reflect.Method.invoke(Method.java:585)
...
Caused by: java.lang.NoClassDefFoundError:
com\1cenqua\1clover/g
        at
net.sf.ehcache.CacheManager.create(CacheManager.java:337)
        at
org.hibernate.cache.EhCacheProvider.start(EhCacheProvider.java:130)
        at
org.hibernate.impl.SessionFactoryImpl.<init>(SessionFactoryImpl.java:173)
        at
org.hibernate.cfg.Configuration.buildSessionFactory(Configuration.java:1176)
...
Sourceforge Ticket ID: 1554434 - Opened By: ferraro - 7 Sep 2006 23:53 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=599784

Duplicate...  Sorry.
Comment by: ferraro - 7 Sep 2006 23:54 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
