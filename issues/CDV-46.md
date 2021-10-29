---

title: "Exception Reporter"
layout: issue
tags: 
permalink: /browse/CDV-46

issue_key: CDV-46
issue_numeric_sort_key: 46
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "eellis"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-05T19:25:26.000-0500"
updated: "2012-07-27T19:59:27.000-0400"
resolved: "2007-02-15T16:38:36.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

The intent of this is primarily to assist support.  The intent is to wrap up the details of collecting (and knowing) all of the relevant information into a single script.  The output of this script is all the debugging information required by support personnel to assist in resolving an issue.  

Requirements:
    * In the event of an exception with the Terracotta software, there should be a tool (usable for both L1 and L2 environments) to capture Terracotta instance data:
       * user environment variables (this information is stored in the tc-logs)
       * terracotta logs (client or server)
       * terracotta logs
       * stack traces etc. (these are printed to the logs)
    * Full mode
       * includes the heap also (or other large objects as deemed required) - specifically, this refers to the L2 database files
    * compressed into a single file archive (a zip)




</div>

## Comments


{:.comment-heading}
### **Taylor Gautier** <span class="date">2007-01-17</span>

<div markdown="1" class="comment">

Additional requirement

\* We must explicitly define the set of environment variables we \*will\* send.  We cannot implement sending environment settings by filtering out those which we do not want to send (such as username).  This will ensure that we only send information that is relevant, and not accidentally include sensitive information.


</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

The primary application is complete. Certain tc-config log features won't be tested CDV-93. In light of this I am unable to assure the behavior of the program.

</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

Do you still want me to package this as a standalone jar. If so there will be some config related code taken from the standard TC codebase. We may also want to integrate this standalone build into our buildsystem.

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

taylor is on vaca. It can probably wait till he gets back.

</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-02-15</span>

<div markdown="1" class="comment">

Program is complete w/ unit test. After review with PM minor updates have been made. This should be correct according to the specs and feedback I've been given.

</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-02-19</span>

<div markdown="1" class="comment">

I've written a unit test which iterates through every combination of inout/output variables that I could come up with. Valid and invalid. This feature should require very little manual testing.

</div>



{% endraw %}
