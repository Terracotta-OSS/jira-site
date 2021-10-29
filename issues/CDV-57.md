---

title: "Support Wicket Web Application Framework"
layout: issue
tags: 
permalink: /browse/CDV-57

issue_key: CDV-57
issue_numeric_sort_key: 57
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "hhuynh"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-08T01:20:22.000-0500"
updated: "2007-06-19T14:45:31.000-0400"
resolved: "2007-05-15T19:12:08.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

Motivation:
Target Wicket (http://wicket.sourceforge.net/) support to increase Terracotta's transparency and add to the portfolio of Terracotta clustered frameworks.

Clustering Wicket Requirements:
   \* Support versions 1.2
   \* Session clustering support
   \* Support for all examples - e.g. each example runs as expected, fail a node and see that the example continues to run in secondary app server

Optional:
   \* Spring component


</div>

## Comments


{:.comment-heading}
### **Eelco Hillenius** <span class="date">2007-01-25</span>

<div markdown="1" class="comment">

If possible, it would be great to target Wicket 1.3. This is currently under development in the Apache repo (https://svn.apache.org/repos/asf/incubator/wicket), but we plan to release within the next two weeks. Good about going for that version is that it is the version that most people (that we know of) are using, including ourselves, which in turn makes it easier to play with Terracotta.

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-25</span>

<div markdown="1" class="comment">

makes sense to me. Unless it is considerably harder for us we'll do 1.3. Of course any help from you guys will be greatly appreciated :-)

</div>


{:.comment-heading}
### **Eelco Hillenius** <span class="date">2007-01-26</span>

<div markdown="1" class="comment">

Cool. And sure we'd be happy to help out if you run into Wicket issues.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-02-01</span>

<div markdown="1" class="comment">

Orion says he tried Wicket 1.2.4 with Terracotta 2.2.1 and had problems -- he was unable to find Wicket 1.3 at the time.

</div>


{:.comment-heading}
### **orion** <span class="date">2007-02-01</span>

<div markdown="1" class="comment">

Here's a link to the wiki page I created about Terracotta config for Wicket:

http://wiki.terracotta.org/confluence/display/wiki/Wicket



</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-03-07</span>

<div markdown="1" class="comment">

Eugene is working on CDV-144 which will enable this feature to be completed.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-03-15</span>

<div markdown="1" class="comment">

This is not going to make Moraga, targeting for (hopefully) Noriega and assigning back to PM.

</div>


{:.comment-heading}
### **orion** <span class="date">2007-04-08</span>

<div markdown="1" class="comment">

Eelco posted some news about Terracotta Wicket integration.  There's been some activity of late:

http://chillenious.wordpress.com/2007/04/07/terracotta-supports-subtype-expressions/

</div>


{:.comment-heading}
### **orion** <span class="date">2007-04-08</span>

<div markdown="1" class="comment">

We should make a config module out of this, of course.

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-05-11</span>

<div markdown="1" class="comment">

where are we with this? seems to be dragging on a bit.

</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-05-15</span>

<div markdown="1" class="comment">

Resolving. Also added some note to the docs about compatibility and boot jar.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-05-30</span>

<div markdown="1" class="comment">

Verify that this has been completed 

</div>


{:.comment-heading}
### **Hung Huynh** <span class="date">2007-06-06</span>

<div markdown="1" class="comment">

aside for failure on JBoss. Tests passed in other containers.

</div>



{% endraw %}
