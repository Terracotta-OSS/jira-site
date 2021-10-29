---

title: "RMP: Allow disconnected L1s to seemlessly reconnect within a timeout period in comms layer to protect against intermittent network outage"
layout: issue
tags: 
permalink: /browse/CDV-97

issue_key: CDV-97
issue_numeric_sort_key: 97
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "X-Common Code"
labels: 
assignee: "hhuynh"
reporter: "ssubbiah"
votes:  0
watchers: 0

created: "2007-01-24T23:55:40.000-0500"
updated: "2007-06-07T17:48:33.000-0400"
resolved: "2007-06-01T14:08:29.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

Currently once a socket is disconnected, the client is chucked out and never allowed to reconnect.

We need to be tolerant of intermittent network failures and allow disconnected L1s to reconnect within a timeout period before giving up locks.

We can handle this condition in the comms layer seamlessly since other components need not really know about this. The following things are needed to be implemented for realizing this feature.
1) On normal disconnect (like client terminating) L1s send a goodbye message. L2 doesnt wait for reconnect
2) On abnormal disconnect, L2 comms layer waits for "timeout" period for allowing reconnect before throwing the CONNECTION\_CLOSED event to the application code.
3) If client reconnects before timeout, a comms level handshake happens to reestablish connection.
4) Any message written out to channel in the meantime is queued for delivery.
5) On reconnect, messages are delivered as usual including the queued messages.

This is very simple to implement, the solution is very local to comms code and will solve the "disconnected mode" and "intermittent network failure"  problem.

</div>

## Comments


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-25</span>

<div markdown="1" class="comment">

I'm not so sure this is quite as simple as it sounds but it is a neat twist on a solution to this issue.

1)  we don't currently have a concept of client shutdown so sending off a message when it happens might be a problem.
Maybe if we add client kill to the admin console it could do the fast kill thing.

2) This is a neat idea. nice twist and simpler to tune than what we were doing when we experimented with lock timeouts way
back when.

3) We have to have some sort of once and only once protocol in here. Not the end of the world since it doesn't have to be transactional
on either the client or the server and we can base resends strickly on connect. Just have to have the sequence number thing right
so that we don't double receive on the server or the client.

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-01-25</span>

<div markdown="1" class="comment">

1) is easy to solve by just sending a goodbye message from shutdown hook in the L1.
Yes 3) is a little involved than that but still not complicated.  We need to add sequence numbers to packets and have comms level acks piggyback on the messages and queue messages till acks. (kind of what tcp does already)

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-25</span>

<div markdown="1" class="comment">

1) Spoken like a man who has never tried to use shutdown hooks :-)

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

Updating Due date to last day of Moraga dev iteration

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-04-09</span>

<div markdown="1" class="comment">

Alex will be doing this feature.

</div>



{% endraw %}
