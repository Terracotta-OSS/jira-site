---

title: "Patch: Wrong log message by DiskStore#backedUp"
layout: issue
tags: 
permalink: /browse/EHC-96

issue_key: EHC-96
issue_numeric_sort_key: 96
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

DiskStore#backedUp always logs the following on
debug level:
"A back up on cache puts occurred. Consider increasing diskSpoolBufferSizeMB for cache ..."

Then it calculates and returns whether the pool size 
limit is exceeded. This message should only be logged if 
the limit is execeeded.

Sourceforge Ticket ID: 1730999 - Opened By: fleiter - 4 Jun 2007 20:29 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320
Originator: NO

Yes

You are right. Fixed.

Greg
Comment by: gregluck - 11 Jun 2007 00:17 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
