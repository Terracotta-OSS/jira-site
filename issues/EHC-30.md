---

title: "Invalid checksums for 1.6.x artifacts in Maven repos"
layout: issue
tags: 
permalink: /browse/EHC-30

issue_key: EHC-30
issue_numeric_sort_key: 30
issuetype: "Bug"
project: "Ehcache Core"
project_key: "EHC"
status: "Closed"
resolution: "Fixed"
priority: ""
components: ""
labels: 
assignee: ""
reporter: "sourceforgetracker"
votes:  0
watchers: 0

created: "2009-09-21T15:07:37.000-0400"
updated: "2012-07-27T19:59:13.000-0400"
resolved: "2009-09-25T17:24:53.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Looks like the ehcache-1.6.0 and 1.6.1 artifacts on oss.sonatype.org and by extension, repo1.maven.org have invalid MD5 and SHA1 checksums:

Downloading: http://repo1.maven.org/maven2/net/sf/ehcache/ehcache/1.6.1/ehcache-1.6.1-osgi.jar
4222K downloaded
[WARNING] \*\*\* CHECKSUM FAILED - Checksum failed on download: local = 'b743344bf636af2a0b5d79a1031ff469f208b510'; remote = '8dbd0fe09757ae4a695e8cc1f4884b643fc9fcab' - RETRYING
Downloading: http://repo1.maven.org/maven2/net/sf/ehcache/ehcache/1.6.1/ehcache-1.6.1-osgi.jar
4222K downloaded
[WARNING] \*\*\* CHECKSUM FAILED - Checksum failed on download: local = 'b743344bf636af2a0b5d79a1031ff469f208b510'; remote = '8dbd0fe09757ae4a695e8cc1f4884b643fc9fcab' - IGNORING

Manually checked the checksums on oss.sonatype.org for 1.6.1 and had similar results for the main artifact as well as the javadoc, osgi, and sources artifacts.
Sourceforge Ticket ID: 2835847 - Opened By: ksmith2000 - 11 Aug 2009 21:21 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Kyle

Looking into this with Jason Van Zyl of Sonatype. I just deployed a snapshot and also got a checksum error. 

Greg
Comment by: gregluck - 23 Aug 2009 05:46 UTC

</div>


{:.comment-heading}
### **gluck** <span class="date">2009-09-25</span>

<div markdown="1" class="comment">

Fixed. Was caused by a bug in Maven 2.0 

</div>



{% endraw %}
