---

title: "MemStore keyArray fuzzy stats"
layout: issue
tags: 
permalink: /browse/EHC-40

issue_key: EHC-40
issue_numeric_sort_key: 40
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

created: "2009-09-21T15:07:38.000-0400"
updated: "2009-09-22T23:44:26.000-0400"
resolved: "2009-09-22T23:44:26.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

If MemoryStore has > 5k maxElements, it uses a keyArray (which is an adaptation over the old pre 1.6 LFU code).
However in line 481 through 498: function saveKey(el) drops a key without doing anything to the corresponding element.

What I believe is happening is that over time the keyArray contains fewer and fewer legitimate items (containly mostly keys to elements already evicted). So the key-sampler grabs 30 keys of which < 30 become elements.  So you're sample-size is shrinking until effectively your just selecting random records from which to delete.  Finally you get a random sample which is completely empty.  This triggers the now VERY slow linear-walk through the entire ConcurrentHashMap (slow if you have 500k+ records).

The number of slow-walks (v.s. fast array selections) increases over time until two full passes have been made through the keyArray.  All the while we're biasing the statistics to fewer than 30 samples.

I suggest the following two algorithms (can provide code if desired)
Alg 1)
init-phase)
 \* until maxElements have been put, atomicly increment a keyArray index as currently done.
 \* every time a random-sample is computed for eviction detection, IF the map doesn't find the key, null out from the array and add to the free-list and continue search for a replacement sample (indices[i]+retryK) (changes lines 575 .. 580).
filled-phase)
 \* pull from the free-list.  If empty for whatever reason, continue walking the atomicKeyIncrementer as done currently.

Alg 2)
Ideally the element stores the index of the keyArray.. Then any eviction can clear out the key. Then your statistics table will always be legit. The freelist will then never be null in the filled-phase above.

Sourceforge Ticket ID: 2823281 - Opened By: mmaraist - 17 Jul 2009 20:01 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
