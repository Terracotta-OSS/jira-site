---

title: "admin console unhappy with large amount of logging happening in L2"
layout: issue
tags: 
permalink: /browse/CDV-64

issue_key: CDV-64
issue_numeric_sort_key: 64
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "Console"
labels: 
assignee: "hhuynh"
reporter: "teck"
votes:  0
watchers: 0

created: "2006-07-03T14:38:40.000-0400"
updated: "2007-06-04T13:58:07.000-0400"
resolved: "2007-04-24T21:29:20.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

As part of debugging a problem at NTT, I added some considerable logging on the L2 side. Since the admin tool receives all logging events, these log statements also make their way there too (in addition to the log file). 

I don't have a thread dump, but the admin console seem to suck 100% cpu with this logging present. The one thread dump I did get, it looked like the AWT event thread was trying to render a text field. My guess is that it wasn't the amount of log data arriving, but rather something about the length of any one individual line.

I'd try two things here:
- Try adding some logging using ridiculous line lengths -- 50k chars? 
- Lots of logging (ie. lots of data being shipped to the console)

I don't think it's worth tuning anything here, but if it's possible to get the console stuck in an infinite loop, that seems like something to investigate/fix



</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2006-07-05</span>

<div markdown="1" class="comment">

Lets get this fixed in Kirkham. As we hopefully add more customers we will be seeing more issues like this

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-04-04</span>

<div markdown="1" class="comment">

Can you create a test for this to verify the issue?

</div>


{:.comment-heading}
### **Hung Huynh** <span class="date">2007-04-24</span>

<div markdown="1" class="comment">

I modified ClientTunnelingEventHandler to have the log print out lots of log and as long as 50k character line and the console works fine.



	private void connectToL1JmxServer(final MessageChannel channel) \{
		logger.info("L1[" + channel.getChannelID()
				+ "] notified us that their JMX server is now available");
		EventContext msg = new L1ConnectionMessage(l2MBeanServer, channel,
				channelIdToJmxConnector, channelIdToMsgConnection, true);
		synchronized (sinkLock) \{
			if (connectStageSink == null) \{
				throw new AssertionError("ConnectStageSink was not set.");
			\}
			connectStageSink.add(msg);
		\}
		
		for (int i = 1024; i < 50\*1024; i += 1024) \{
			logger.info(makeString(i));
		\}

	\}

	private String makeString(int length) \{
		StringBuffer b = new StringBuffer(""+length);
		for (int i = 0; i < length; i++) \{
			b.append("a");
		\}
		return b.toString();
	\}

</div>



{% endraw %}
