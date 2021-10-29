---

title: "Server can throw an Assertion and crash if a client disconnects before sending any transactions to the server."
layout: issue
tags: 
permalink: /browse/CDV-89

issue_key: CDV-89
issue_numeric_sort_key: 89
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "DSO:L2"
labels: 
assignee: "ssubbiah"
reporter: "ssubbiah"
votes:  0
watchers: 0

created: "2007-01-23T02:36:53.000-0500"
updated: "2012-07-27T19:59:28.000-0400"
resolved: "2007-01-23T04:02:25.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

The server throws this Assertin error and exists if a client disconnects before sending any transactions to the server.

java.lang.NullPointerException: object is null
        at com.tc.util.Assert.assertNotNull(Assert.java:80)
        at com.tc.util.Assert.assertNotNull(Assert.java:84)
        at com.tc.objectserver.tx.TransactionBatchManagerImpl.shutdownClient(TransactionBatchManagerImpl.java:47)
        at com.tc.objectserver.handler.ChannelLifeCycleHandler.channelRemoved(ChannelLifeCycleHandler.java:64)
        at com.tc.objectserver.handler.ChannelLifeCycleHandler.handleEvent(ChannelLifeCycleHandler.java:46)
        at com.tc.async.impl.StageImpl$WorkerThread.run(StageImpl.java:141)

Even though this is an unllikely case, it can still happen in the enterprise kit where there is license restrictions or when client run for a very short time and dont modify any data. 

This was fixed by Gary in the trunk but it needs to be back ported to 2.2.1


</div>

## Comments


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

pushed to 2.2.1 for ga

</div>



{% endraw %}
