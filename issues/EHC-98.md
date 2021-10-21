---

title: "Possible problem in MulticastServerThread clas"
layout: issue
tags: 
permalink: /browse/EHC-98

issue_key: EHC-98
issue_numeric_sort_key: 98
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

created: "2009-09-21T15:07:43.000-0400"
updated: "2009-09-22T23:44:30.000-0400"
resolved: "2009-09-22T23:44:30.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

It seems like there is a small "bug" in the
MulticastServerThread class (file
MulticastKeepaliveHeartbeatSender.java). In line 192
(revision 52), the code looks like:

if (!socket.isClosed()) {

but it would be better if it looked like:

if ((socket != null) && (!socket.isClosed())) {

because I get a NullPointerException if the application
terminates before Java is able to start the thread. If
I run the application in the debugger (with a
breakpoint), the thread has enough time to create the
MulticastSocket object.

Exception in thread "main" java.lang.NullPointerException
        at
net.sf.ehcache.distribution.MulticastKeepaliveHeartbeatSender$MulticastServerThread.closeSocket(MulticastKeepaliveHeartbeatSender.java:192)
        at
net.sf.ehcache.distribution.MulticastKeepaliveHeartbeatSender$MulticastServerThread.interrupt(MulticastKeepaliveHeartbeatSender.java:186)
        at
net.sf.ehcache.distribution.MulticastKeepaliveHeartbeatSender.dispose(MulticastKeepaliveHeartbeatSender.java:78)
        at
net.sf.ehcache.distribution.MulticastRMICacheManagerPeerProvider.dispose(MulticastRMICacheManagerPeerProvider.java:172)
        at
net.sf.ehcache.CacheManager.shutdown(CacheManager.java:526)
        at go.<init>(go.java:57)
        at go.main(go.java:21)

This is of course not a big problem because it is very
rare that an application uses the Cache and terminates
THAT quickly.... ;)

Thanks,
Roman
Sourceforge Ticket ID: 1480442 - Opened By: roman\1k - 2 May 2006 14:45 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=693320

Roman,

Yes, a startup and immediate shudown could theoretically cause the NPE. 
Added the fix as suggested. Is in trunk. Will be in 1.2.1. 

If you find any biggies in this area let me know!

Regards
Greg
Comment by: gregluck - 3 May 2006 08:20 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
