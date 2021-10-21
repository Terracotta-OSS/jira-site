---

title: "Terracotta Administrator Console Password"
layout: issue
tags: 
permalink: /browse/CDV-47

issue_key: CDV-47
issue_numeric_sort_key: 47
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: ""
labels: 
assignee: "tgautier"
reporter: "jhartley"
votes:  0
watchers: 0

created: "2007-01-05T19:54:28.000-0500"
updated: "2012-07-27T19:59:37.000-0400"
resolved: "2007-02-23T16:50:31.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Provide a first layer of security for access to Terracotta Administrator Console.

Requirements:
    \1 We need the ability to configure password access to Terracotta Administrator Console.
    \1 This can be as simple as it needs to be.
    \1 The password can be stored in a config or properties file.
    \1 The password must be stored in a hashed (e.g. not clear text) format.
    \1 Java keychains can be cumbersome -- let's avoid them if we can find a better approach.
    \1 Terracotta Administrator Console should have a menu driven interface for configured/setting the password.
    \1 By default the password should be off.
    \1 If there is no password, Terracotta Administrator console should not ask for a password.
    \1 If there is no password, the user should have a menu option to set the password - the password field should not show plain text, and it should ask for the password twice. If the two passwords typed (they can be in the same dialog) match, the password is enabled.
    \1 If the user is logged in, and there is a password enabled, there should be an option for resetting the password. The password dialog should ask for the old password, and for the new one, with confirmation as previously described.
    \1 If the user is logged in, there should be an option to disable password checking.


</div>

## Comments


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-02-12</span>

<div markdown="1" class="comment">

The current implementation of password authentication is as follows:

We rely on the standard Java security mechanisms which require that the password be declared with user permissions in the jre/lib/management directory in plain text with user read only permissions. 

JMX is authenticated but the actual messages are not. JMX support for SSL was not available until Java 1.5 which would require that we migrate the L2 away from 1.4 support.

The authentication criteria will be defined as a tcconfig element under <server>, not as a user interface element. This allows us to manage just the L2 and the JMX messages that shut the server down.

There will be no facility to change or reset the L2 password (you'll have to edit the files under jre/lib/management).


Awaiting approval:

</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-02-12</span>

<div markdown="1" class="comment">

>>We rely on the standard Java security mechanisms which require that the password be declared with user permissions in the jre/lib/management >>directory in plain text with user read only permissions.

We actually support the placement of these files anywhere but the default will remain $JAVA\1HOME/jre/lib/management because it's Java's default location for securing the environment.

</div>


{:.comment-heading}
### **Eric Ellis** <span class="date">2007-02-22</span>

<div markdown="1" class="comment">

I believe this issue is complete. Waiting on approval.

</div>


{:.comment-heading}
### **Sean Nguyen** <span class="date">2007-03-02</span>

<div markdown="1" class="comment">

Currently, we require a very specific setup for both jmxremote.access and jmxremote.password files in order for the Amin authentication feature to behave as expected.  However, what Eric has not included in his documentation is what if the setup was done any different, what will be the user experience from the UI perspective.  I might be wrong but as soon as we dwell into further detail, it might open a lot more work from both dev and of course qa.  

Taylor, please confirm to what extend we need to look for in this feature.

</div>



{% endraw %}
