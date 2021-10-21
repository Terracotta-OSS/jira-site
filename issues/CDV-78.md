---

title: "Disable location sensitive information in id generated for Spring app context by default"
layout: issue
tags: 
permalink: /browse/CDV-78

issue_key: CDV-78
issue_numeric_sort_key: 78
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "3 Minor"
components: "SpringRuntime"
labels: 
assignee: "ekulesho"
reporter: "ekulesho"
votes:  0
watchers: 0

created: "2007-01-18T13:21:36.000-0500"
updated: "2012-07-27T20:00:51.000-0400"
resolved: "2007-02-05T23:31:11.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Currently we always include execution stack trace into synthetic id generated for Spring app context. This is done to allow scenario like this:

ctx1 = new ClasspathXmlApplicationContext(new String[] {"ctx.xml"});
ctx2 = new ClasspathXmlApplicationContext(new String[] {"ctx.xml"});

So, ctx1 and ctx2 won't be linked together as a clustered context and will stay unrelated, though instance of ctx1 or ctx2 can be still clustered with context created at the same location on different node.

However there are scenarios when this logic may not be what user would expect and we need to support that.

The idea is to disable stack trace inclusion by default and add an option to tc-config.xml to allow to enable it. Meaning of that option is something like "include location info into the generated context id".

</div>

## Comments


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-19</span>

<div markdown="1" class="comment">

So the default will be for autoid to be off. Lets brain storm on some names.

auto id
stack based id
dynamic id

any other ideas?

</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-01-19</span>

<div markdown="1" class="comment">

generate id with location
generate id with stacktrace

</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-02-05</span>

<div markdown="1" class="comment">

Added <enable-location-info> element to the <application-context>. By default stack trace is not added, but can be enabled back by adding:

...
<application-context>
  <enable-location-info>true<enable-location-info>
   ...


</div>



{% endraw %}
