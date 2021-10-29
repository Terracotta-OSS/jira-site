---

title: "Usability issue w/ tag in tc-config.xml"
layout: issue
tags: 
permalink: /browse/CDV-79

issue_key: CDV-79
issue_numeric_sort_key: 79
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "3 Minor"
components: "DSO:L1,DSO:L2"
labels: 
assignee: "qa"
reporter: "ari"
votes:  0
watchers: 0

created: "2006-11-14T01:10:01.000-0500"
updated: "2013-02-12T14:02:21.000-0500"
resolved: "2007-03-08T18:23:53.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

When I tried to run clients on different hosts (for my Virgina and NYC JUG) I ran into lots of frustration getting the client L1 to connect across a network to the L2. After lots of frustration, I uncovered that the following won't work (in the case of no DNS): 

<servers> 
<server name="www.xxx.yyy.zzz" /> 

Where www.xxx.yyy.zzz is a real IP address such as 192.168.1.102 

The problem? Simple. name only applies to a DNS-mappable name and IP addresses must be set in the host="" block of the same server config setting. This is not very normal behavior. Most software detects that a hostname is actually an IP address and has no issues being used in that manner.

</div>

## Comments


{:.comment-heading}
### **Steve Harris** <span class="date">2006-11-14</span>

<div markdown="1" class="comment">

If this is something you can fix easily than squeeze it in. If not we'll move it out to moraga since we are down to the wire.

</div>


{:.comment-heading}
### **Dileo Moreira** <span class="date">2007-01-03</span>

<div markdown="1" class="comment">

Server name should support both computer host name and numerical IP address.

</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-01-18</span>

<div markdown="1" class="comment">

I've debugged this a little. The current logic sets host value to the local IP address. See com.tc.config.schema.setup.StandardXMLFileConfigurationCreator.java (line 331)

After that logic for using name as a host in com.tc.config.schema.L2ConfigForL1Object.java (line 57) never work, because host value is never nur or empty at this point.

So, the current behaviour is actually like this: if host is not specified, localhost is assumed in this case. Which is probably make some sense, but need to be fixed in the documentation.

Please let me know if we should change that to use name value for the host as per current documentation.

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-19</span>

<div markdown="1" class="comment">

I'm a bit confused. So is ari wrong about what the problem was? Can someone put either an ip address or a hostname in that field?

</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-01-19</span>

<div markdown="1" class="comment">

There is no difference in treating name or the host attribute. However, if host is not specified it is always defaulted to the local ip address and not to the value of name attribute.

Ari was following to what documentation is saying, but apparently documentation is not correct.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-19</span>

<div markdown="1" class="comment">

I'm still waiting for a reply on tc-dev as to what this thing really does, but the best I've heard so far informally is that this tag is used to identify the server in the admin console.  Additionally, if you have multiple servers using the same configuration file you can identify exactly which one to start/stop/etc.

It seems to me that if this attribute is causing problems (as it appears to be from a real user in a JUG) we should just get rid of it as it's really not necessary, at least any more.  The conceivable use case of having multiple L2 servers on one or more machines using the exact same configuration file seems like a < %1 case, whereas > 99% of users will never do this.

I think most people would, however, like to see the ability to "name" an L2 for use in the admin console, reporting, monitoring, etc., but I believe we already have a -n switch in start-tc-server.sh that could just as easy provide a user-defined name instead of <host>:<port>, covering this use case.

Just my two cents.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-19</span>

<div markdown="1" class="comment">

I guess I misinterpreted the description in my last comment: I thought this was an issue from a user trying our software who was a member of two JUGs.  I think I still have a fair point, though :)

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-03-07</span>

<div markdown="1" class="comment">

Per the dev list (and Steve) the "name" attribute will be renamed to "alias" to make it clear that this is not where the hostname/IP address goes but is only a symbolic name.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-03-08</span>

<div markdown="1" class="comment">

The description is the result of a bug in which the code does not behave how the documentation (the schema itself) says it does.  It claims that the value for the "host" attribute defaults to the value of the "name" attribute which simply wasn't true (until revision 1686).  By putting the IP address in the "name" attribute it would not propagate to the "host" element as the docs suggest and would instead default to the local machine IP address which was, of course, wrong and cause the situation Ari saw.

IP addressing and DNS naming have always worked, and with this fix the "name" will now propertly be the default value for "host" if "host" is not given, so the situation in the description should now work.

</div>



{% endraw %}
