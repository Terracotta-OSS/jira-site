---

title: "Cannot load configuration from jar (or any non-file URL)"
layout: issue
tags: 
permalink: /browse/EHC-99

issue_key: EHC-99
issue_numeric_sort_key: 99
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

created: "2009-09-21T15:07:43.000-0400"
updated: "2009-09-22T23:44:30.000-0400"
resolved: "2009-09-22T23:44:30.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

Loading any configuration file from a jar fails in the
current beta release and CVS head.

Error details:
net.sf.ehcache.CacheException: Error configuring from
jar:file:/C:/documents and
settings/jt/LOCALS~1/Temp/config\_29212.jar!/ehcache.xml.
Error was file:\C:\documents and
settings\jt\LOCALS~1\Temp\config\_29212.jar!\ehcache.xml
(The filename, directory name, or volume label syntax
is incorrect)
 at
net.sf.ehcache.config.ConfigurationFactory.parseConfiguration(ConfigurationFactory.java:120)
 at
net.sf.ehcache.config.ConfigurationFactoryTest.testDefaultConfiguration(ConfigurationFactoryTest.java:561)
 at
net.sf.ehcache.config.ConfigurationFactoryTest.testLoadConfigurationFromJarURL(ConfigurationFactoryTest.java:617)
<snip/>

This is regression caused by the fix applied for bug
1237017: "Fails to load ehcache.xml if the filepath
contains spaces" - it now tries to convert all urls to
a file path and load from that path, which will only
work for files - not for jars, http or any other protocol.

The fix is to simply use the URL.openStream() method.

Note that this fix will invalidate bug 1338846:
"URLdecode for url.getFile".

The attached patch fixes the issue and includes a test
case to prevent regression. 
Sourceforge Ticket ID: 1410374 - Opened By: teljj001 - 20 Jan 2006 02:05 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
