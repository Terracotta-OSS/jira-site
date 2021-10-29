---

title: "jms replication and Oracle AQ"
layout: issue
tags: 
permalink: /browse/EHC-32

issue_key: EHC-32
issue_numeric_sort_key: 32
issuetype: "Bug"
project: "Ehcache Core"
project_key: "EHC"
status: "Open"
resolution: ""
priority: "3 Minor"
components: "ehcache-jmsreplication"
labels: 
assignee: ""
reporter: "sourceforgetracker"
votes:  1
watchers: 1

created: "2009-09-21T15:07:38.000-0400"
updated: "2010-01-06T18:57:12.000-0500"
resolved: ""




---

{% raw %}

## Description

<div markdown="1" class="description">

ehcache-jms-replication version 0.4 is not working with Oracle AQ.
When jms-replication tries to send(publish) a message to the Topic AQ gives the following message: JMS-107: Operation not allowed on TopicPublisher.
It is not allowed to call the send method on TopicPublisher, you have to call the publish method instead.
 
In JMSCacheManagerPeerProvider.java the created TopicPublisher is passed to the constructor of JMSCachePeer.java
In JMSCachePeer.java the TopicPublisher is assigned to a class member of type MessageProducer.
The type MessageProducer has to be changed to TopicPublisher and instead of the send method, the publish method has to be called in the send method of the JMSCachePeer.java class.

Sourceforge Ticket ID: 2817660 - Opened By: hsdpe - 6 Jul 2009 21:43 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

I applied your change which worked perfectly with Open MQ.

For ActiveMQ it did not. Only one test is breaking so I am looking into it. The problem happens with both 5.1 and 5.2




Comment by: gregluck - 13 Jul 2009 02:50 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2010-01-06</span>

<div markdown="1" class="comment">

Assigning these issues to Greg, so that he can decide what to do with them.

</div>



{% endraw %}
