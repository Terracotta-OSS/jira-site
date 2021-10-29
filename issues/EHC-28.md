---

title: "instance of in JMSCachePeer not working"
layout: issue
tags: 
permalink: /browse/EHC-28

issue_key: EHC-28
issue_numeric_sort_key: 28
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
votes:  0
watchers: 0

created: "2009-09-21T15:07:37.000-0400"
updated: "2010-01-12T10:37:26.000-0500"
resolved: ""

attachments:
- filename: "JmsCachePeerTest.patch"
  author: "carrja99"
  size: 10.00 k
  mimeType: text/plain




---

{% raw %}

## Description

<div markdown="1" class="description">

Hi

The "instance of" used in the source code of ehcache jms replication provided by ehcahe is not working. 

File Name is JMSCachePeer

private void handleObjectMessage(Message message) throws JMSException, RemoteException \{
        ObjectMessage objectMessage = (ObjectMessage) message;
        Object object = objectMessage.getObject();

        //If a non-cache publisher sends an Element
        if (object instanceof Element) {
            /** some code **/
        } else if (object instanceof JMSEventMessage) {
            /** some code **/
        } else {
            /** some code **/
        }

Even though the object is of type JMSEventMessage, the control does not go in the second if. rather it goes in the last else.

The object.getClass().getName() and JMSEventMessage.class.getName() both give the same result which is net.sf.ehcache.distribution.jms.JMSEventMessage.

Even then i am getting a classcastexception when i do this:

JMSEventMessage jmsEventMessage = (JMSEventMessage) object;

If anyone has any input then please share.

Thanks in advance..

Regards,
Nimit Grover.

Sourceforge Ticket ID: 2839041 - Opened By: nimitgrover - 17 Aug 2009 15:09 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2010-01-06</span>

<div markdown="1" class="comment">

Assigning these issues to Greg, so that he can decide what to do with them.

</div>


{:.comment-heading}
### **James Carr** <span class="date">2010-01-12</span>

<div markdown="1" class="comment">

In order to understand the codebase better I wrote some pure unit tests around JmsCachePeer... and it seems to prove this bug does not exist. 

</div>



{% endraw %}
