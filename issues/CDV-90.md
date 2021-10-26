---

title: "LinkedBlockingQueue throws \"java.lang.IllegalStateException: Queue full\" when queue actually not full"
layout: issue
tags: 
permalink: /browse/CDV-90

issue_key: CDV-90
issue_numeric_sort_key: 90
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "asi"
reporter: "ssubbiah"
votes:  0
watchers: 0

created: "2007-01-23T04:09:55.000-0500"
updated: "2012-07-27T19:59:35.000-0400"
resolved: "2007-01-30T12:51:32.000-0500"

---



{% raw %}


## Attachments

* <em>ssubbiah</em> (4.000 k, application/x-gzip) [LinkedBlockingQueueTest.tar.gz](/attachments/CDV/CDV-90/LinkedBlockingQueueTest.tar.gz)




## Description

<div markdown="1" class="description">

I wrote some simple LinkedBlockingQueue test and it fails with the following exception when the Queue is actually not full.

Exception in thread "main" java.lang.IllegalStateException: Queue full
        at java.util.AbstractQueue.add(AbstractQueue.java:64)
        at com.tctest.concurrent.LinkedBlockingQueueTest.populateQueue(LinkedBlockingQueueTest.java:70)
        at com.tctest.concurrent.LinkedBlockingQueueTest.runTest(LinkedBlockingQueueTest.java:36)
        at com.tctest.concurrent.LinkedBlockingQueueTest.main(LinkedBlockingQueueTest.java:31)

The code is attached. One can run it using the accompanying script. Run as
./run.sh 2

It fails with the above exception once in a few runs. Note that the LinkedBlockingQueue was created with no bounds.

Exception in thread "main" java.lang.IllegalStateException: Queue full
        at java.util.AbstractQueue.add(AbstractQueue.java:64)
        at com.tctest.concurrent.LinkedBlockingQueueTest.populateQueue(LinkedBlockingQueueTest.java:70)
        at com.tctest.concurrent.LinkedBlockingQueueTest.runTest(LinkedBlockingQueueTest.java:36)
        at com.tctest.concurrent.LinkedBlockingQueueTest.main(LinkedBlockingQueueTest.java:31)



</div>

## Comments


{:.comment-heading}
### **Antonio Si** <span class="date">2007-01-30</span>

<div markdown="1" class="comment">

Fix the instrumentation. Add a LinkedBlockingQueuePerfTest.

</div>



{% endraw %}
