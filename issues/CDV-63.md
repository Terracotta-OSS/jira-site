---

title: "Inconsistent local vs. distributed state possible to TXNs that race to commit new objects"
layout: issue
tags: 
permalink: /browse/CDV-63

issue_key: CDV-63
issue_numeric_sort_key: 63
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Resolved"
resolution: "Won't Fix"
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "teck"
reporter: "teck"
votes:  0
watchers: 1

created: "2006-07-03T14:52:29.000-0400"
updated: "2013-09-06T16:18:55.000-0400"
resolved: "2013-09-06T16:18:55.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

A while back we I added some code to deal with the fact separate transactions (ie. threads) in the same client might both reference the same "new" (as in new to DSO) object . In that case there will a race to commit the "new" object. The fix for that bug has a new bug in it. The new code determines who is the winner of the race, the winner is allowed to commit the object wholesale, the loser of the race only commits deltas they created after first referencing the new object. Committing that set of deltas is wrong since they might have occured before the time the first thread commits the state. In this case, the distributed state of the object will be inconsistent with the local state. 

There is more than one way to fix this problem, and I think it's even already being worked on. This item is just to make sure we track the issue.

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2006-07-05</span>

<div markdown="1" class="comment">

Assigning to Kirkham for now. If we have a fix in the near term (i.e. before the NTT fix release)\1 we should fix this in the Judah branch and main

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2006-07-13</span>

<div markdown="1" class="comment">

Did your last batch of changes fix this?

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2006-07-13</span>

<div markdown="1" class="comment">

This was fixed by making VM wide txns instead thread local txns. But since that fix makes us lose txn atomicity, that fix was not pushed. So this bug is still lurking around. 

</div>



{% endraw %}
