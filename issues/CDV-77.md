---

title: "Add %i to config expansion"
layout: issue
tags: 
permalink: /browse/CDV-77

issue_key: CDV-77
issue_numeric_sort_key: 77
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "nharward"
reporter: "tgautier"
votes:  0
watchers: 1

created: "2007-01-17T21:50:14.000-0500"
updated: "2012-07-27T19:59:15.000-0400"
resolved: "2007-03-09T17:14:58.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

The value %i should expand to the local ip address of the server.

This expansion should be supported in the server element attributes.  

Having this feature will enable the config file to be "location independent" - allowing it to be moved around without having to change the config file.

</div>

## Comments


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-01-22</span>

<div markdown="1" class="comment">

From what I see in com.tc.config.schema.dynamic.ParameterSubstituter.substitute(String source) the following macros are supported (including requested %i):

%d - getUniqueTempDirectory()
%h - getHostname();
%i - getIpAddress();  
%H - System.getProperty("user.home");
%n - System.getProperty("user.name");
%o - System.getProperty("os.name");
%a - System.getProperty("os.arch");
%v - System.getProperty("os.version");
%t - System.getProperty("java.io.tmpdir");

As far as I can tell this should work for logs and data elments.

Taylor, I just tested 2.2 release and it is working there.

</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-01-22</span>

<div markdown="1" class="comment">

After discussing this with Steve, he suggested that these macros should be documented somewhere.

</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-01-22</span>

<div markdown="1" class="comment">

Sounds good.

Any of you think we may be overdoing it a little with the wildcards? Maybe two or three would be enough.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

Updating Due date to last day of Moraga dev iteration

</div>


{:.comment-heading}
### **Taylor Gautier** <span class="date">2007-02-27</span>

<div markdown="1" class="comment">

Let's apply this fix to demos.  Then we need to test that they can be run from localhost and a different host without changing the config.  Which is better - %i or %h?

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-02-28</span>

<div markdown="1" class="comment">

With the current demos, is this problem already gone?

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-03-05</span>

<div markdown="1" class="comment">

QA, this should work according to Gary can you verify?

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-03-05</span>

<div markdown="1" class="comment">

Marking fixed as Gary says this should work.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-03-08</span>

<div markdown="1" class="comment">

This is not quite fixed, but is a quick one-liner.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-03-08</span>

<div markdown="1" class="comment">

Can we have this fixed be EOD Friday 3/9?

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-03-09</span>

<div markdown="1" class="comment">

The samples tc-config.xml files have also been updated to use a host of "%i" and a symbolic name of "sample"

</div>



{% endraw %}
