---

title: "ehcache-constructs-0-5 only compiles with jdk1.5"
layout: issue
tags: 
permalink: /browse/EHC-84

issue_key: EHC-84
issue_numeric_sort_key: 84
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

The RELEASE\_NOTES.txt says in Java Requirements:

"When compiling from source, the build process requires
JDK 1.4 or 5."

\*\*\*

When i compile the source with jdk1.4 i get compile
errors with one
testclass:

    [javac]
/home/krische/cvs/ehcache-constructs/test/java/net/sf/ehcache/constructs/web/filter/SpeedTest.java:128:
cannot resolve symbol
    [javac] symbol  : method contains (java.lang.String)
    [javac] location: class java.lang.String
    [javac]            
response.getText().contains("timestamp");

String.contains(String) exists only with jdk1.5+.

\*\*\*

A solution would be to use the old fashioned way: 

String.indexOf(String) >= 0



Sourceforge Ticket ID: 1185149 - Opened By: knigge - 18 Apr 2005 12:56 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

Thanks for that. I added source="1.4" to the usages of javac
in ehcache and ehcache-constructs to try and control issues
like this.

Changed the code as suggested. Ta.
Comment by: gregluck - 26 Apr 2005 05:41 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
