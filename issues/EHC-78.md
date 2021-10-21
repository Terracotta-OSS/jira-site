---

title: "PropertyUtil.parseProperties using a JDK 1.5+ only feature"
layout: issue
tags: 
permalink: /browse/EHC-78

issue_key: EHC-78
issue_numeric_sort_key: 78
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
updated: "2009-09-22T23:44:28.000-0400"
resolved: "2009-09-22T23:44:28.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Version: 1.3.0-beta3

The following line in PropertyUtil.parseProperties attempts to use String.replace(CharSequence, CharSequence), which exists in JDK 1.5+, but not in 1.4 or earlier versions.

propertyLines = propertyLines.replace(propertySeparatorLocal, "\n");

I'm guessing that 1.4 compatibility is still desired/required, so this probably should be changed back to using String.replace(char, char) if possible.
Sourceforge Ticket ID: 1732424 - Opened By: ben\1piper - 6 Jun 2007 22:34 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=693320
Originator: NO

Ben

Yes 1.4 remains important. Fixed.

Greg
Comment by: gregluck - 11 Jun 2007 00:11 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
