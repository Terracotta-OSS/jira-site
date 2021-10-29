---

title: "Fails to load ehcache.xml if the filepath contains spaces"
layout: issue
tags: 
permalink: /browse/EHC-95

issue_key: EHC-95
issue_numeric_sort_key: 95
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
updated: "2009-09-22T23:44:29.000-0400"
resolved: "2009-09-22T23:44:29.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

I am using ehcache-09 called from inside of hibernate
for an application deployed on JBoss on Windows
platform. JBoss is installed in a directory off,
"c:\program files". net.sf.hibernate.EhCacheProvider
invokes CacheManager.create() to create a cache
manager. A FileNotFoundException is thrown from
parser.parse(url.toExternalForm(), handler);
in Configurator.configure.
To make the code work on windows with spaces in the
file path, the following code is needed.
           //get URL object to ehcache.xml from class
loader
    File f = new File(url.getFile());
    FileInputStream fis = new FileInputStream(f);
           parser.parse(fis, handler);
I noticed there is a workaround in ehcache-1.1 where a
cache manager can be created with an InputStream,
however the other 3 configure methods will fail on
windows with spaces in the file path. Suggest modifying
the 3 other configure methods in Configurator to first
create a FileInputStream and then call the configure
method that accepts InputStream.

Sourceforge Ticket ID: 1237017 - Opened By: sandip\_ghosh - 12 Jul 2005 23:07 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
