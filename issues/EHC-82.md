---

title: "Unmentioned dependency"
layout: issue
tags: 
permalink: /browse/EHC-82

issue_key: EHC-82
issue_numeric_sort_key: 82
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

While the site/docs mention a dependency on
commons-logging, they don't mention the dependency on
commons-collections.

 This was running within Tomcat (V4, I think, if it
makes any difference...)

2004-11-29 14:23:50.793 [10] Fatal     ModuleContainer
     Could not initialize the server correctly        
java.lang.NoClassDefFoundError:
org/apache/commons/collections/LRUMap
         java.lang.ClassLoader.defineClass0(Native Method)
        
java.lang.ClassLoader.defineClass(ClassLoader.java:537)
        
java.security.SecureClassLoader.defineClass(SecureClassLoader.java:123)
        
java.net.URLClassLoader.defineClass(URLClassLoader.java:251)
        
java.net.URLClassLoader.access$100(URLClassLoader.java:55)
        
java.net.URLClassLoader$1.run(URLClassLoader.java:194)
        
java.security.AccessController.doPrivileged(Native Method)
        
java.net.URLClassLoader.findClass(URLClassLoader.java:187)
        
java.lang.ClassLoader.loadClass(ClassLoader.java:289)
        
sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:274)
        
java.lang.ClassLoader.loadClass(ClassLoader.java:235)
        
java.lang.ClassLoader.loadClassInternal(ClassLoader.java:302)
         net.sf.ehcache.Cache.initialise(Cache.java:271)
        
net.sf.ehcache.CacheManager.addCacheNoCheck(CacheManager.java:333)
        
net.sf.ehcache.CacheManager.configure(CacheManager.java:177)
        
net.sf.ehcache.CacheManager.<init>(CacheManager.java:138)
        
net.sf.ehcache.CacheManager.create(CacheManager.java:193)


Sourceforge Ticket ID: 1075252 - Opened By: gwynevans - 29 Nov 2004 13:47 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
