---

title: "Support Lucene Search Index"
layout: issue
tags: 
permalink: /browse/CDV-52

issue_key: CDV-52
issue_numeric_sort_key: 52
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "juris"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-07T16:51:05.000-0500"
updated: "2012-07-27T19:59:32.000-0400"
resolved: "2007-03-20T14:03:47.000-0400"

attachments:
- filename: "lucene.tgz"
  author: "eellis"
  size: 21.00 k
  mimeType: application/x-compressed-tar




---

{% raw %}

## Description

<div markdown="1" class="description">

Motivation:
Target Lucene (http://lucene.apache.org/) support to increase Terracotta's transparency and add to the portfolio of Terracotta clustered frameworks.

Requirements:
Successfully cluster a Lucene search index such that an index is generated on one node and utilized on a second node.  Test with an existing Lucene powered Java application (http://en.wikipedia.org/wiki/Lucene) or develop a sample application if needed.


</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-31</span>

<div markdown="1" class="comment">

This should be done in /trunk for now -- as the "config bundles" infrastructure plays out we can migrate over.

</div>


{:.comment-heading}
### **orion** <span class="date">2007-01-31</span>

<div markdown="1" class="comment">

I've put up some sample code that does clustering lucene.  We also have a request from a community member (officialleader AT yahoo.com) to participate in the Lucene clustering effort.

The sample code that I made is up on the Forge Labs project:  http://wiki.terracotta.org/confluence/display/labs/Lucene+Clustering

</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-02-19</span>

<div markdown="1" class="comment">

The first phase of support is complete. We have a Lucene project with a simple test which uses the Lucene demo classes to parse an HTML version of the Bible and then search for lamb and Buddha. The test clusters two nodes, searches for Buddha (which is not found), then adds the keyword and searches again. The same is done with lamb (which is found in 14 books) where the phrase "Mary had a little lamb" is added and again searched on both nodes.

A second test will be written which will query, under load, all methods of the Directory interface to produce lock contention.

</div>


{:.comment-heading}
### **Gary Keim** <span class="date">2007-02-28</span>

<div markdown="1" class="comment">

There's apparently more to this than meets the eye.


</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-03-01</span>

<div markdown="1" class="comment">

Various research files such as bookmarks, a thread dump (of lock contention using hashtable locking), lock attributes.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-03-01</span>

<div markdown="1" class="comment">

Eric got this working, but apparently the performance is not what it should be.  It will require a deeper investigation to tune this to be satisfactory -- not sure if this can happen for Moraga or if it will have to wait.

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-03-02</span>

<div markdown="1" class="comment">

Maybe tomorrow or monday I'll go over it with him. Is it slow on add or query? Used to be pretty fast on query but a bit slow on adds.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-03-08</span>

<div markdown="1" class="comment">

I've spoken with Taylor and we agree that this has been finished to requirements outlined in PRD. Any other issues with it, outside of the requirements should be logged as bugs. Is there anything else to complete on this feature?

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-03-08</span>

<div markdown="1" class="comment">

This is not quite ready to go, it needs to be converted to a configuration module and apparently requires some tuning before it will be usable in large indices.

</div>


{:.comment-heading}
### **Juris Galang** <span class="date">2007-03-19</span>

<div markdown="1" class="comment">

We now have a Clustered Lucene module.
We only have 1 test - it basically checks that the RAMDirectory is clustered. We'll need to add some performance and load tests.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-03-20</span>

<div markdown="1" class="comment">

Resolving as this is done for 2.3, except for adding more tests.

</div>



{% endraw %}
