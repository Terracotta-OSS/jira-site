---

title: "L1 Disconnected Mode Allowed"
layout: issue
tags: 
permalink: /browse/CDV-11

issue_key: CDV-11
issue_numeric_sort_key: 11
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Won't Fix"
priority: "3 Minor"
components: ""
labels: 
assignee: "steve"
reporter: "drb"
votes:  0
watchers: 1

created: "2006-11-30T19:11:40.000-0500"
updated: "2012-07-27T20:00:51.000-0400"
resolved: "2007-02-17T00:33:25.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

Mode allowed for applications nodes (L1s) to continue to function as standalone web containers if they lose connection to the Terracotta Server (L2).

Details:

    * Just for Sessions
    * Should it timeout before disconnected mode kicks in
    * Application nodes can swap objects in and out, and only load a configured portion of each session, and configure the L1s accordingly.
    * Exception thrown is application attempts to accesses to shared object not in the application node (L1) DSO cache.
    * Queue up DSO transactions (L1 mutates an object that's already in its local DSO cache).
    * Upon reconnect, what happens? Meaning of <client-reconnect-window>.
    * Allow dirty reads?

Virtual Router Redundancy Protocol (VRRP): http://en.wikipedia.org/wiki/Virtual\_router\_redundancy\_protocol

</div>

## Comments


{:.comment-heading}
### **Ron Bodkin** <span class="date">2007-01-17</span>

<div markdown="1" class="comment">

I find that if I the L2 server stops then an L1 Web application (like the Spring JMX sample app) will hang, even if you restart the L2. This results in losing threads in the app server and might also result in deadlock. Apparently a network outage would cause this too. It would be very desirable to support recovery from lost connections and even server crashes. 

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-17</span>

<div markdown="1" class="comment">

Are you running in persistent mode? 

</div>


{:.comment-heading}
### **Ron Bodkin** <span class="date">2007-01-17</span>

<div markdown="1" class="comment">

This isn't in persistent mode. I will test that case...

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-17</span>

<div markdown="1" class="comment">

In persistent mode you can bring down the server, which should pause things that require locks, when the server comes back up things should continue

</div>


{:.comment-heading}
### **Ron Bodkin** <span class="date">2007-01-17</span>

<div markdown="1" class="comment">

It works in persistent mode thanks for clarifying that. This is a way I can test monitoring slow Terracotta performance with Glassbox :-)

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-01-24</span>

<div markdown="1" class="comment">

If CDV-97 is implemented this might not be needed.

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-02-17</span>

<div markdown="1" class="comment">

Decided to do CDV-97

</div>



{% endraw %}
