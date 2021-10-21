---

title: "Closing Cache does not remove shutdown hook"
layout: issue
tags: 
permalink: /browse/EHC-66

issue_key: EHC-66
issue_numeric_sort_key: 66
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

created: "2009-09-21T15:07:40.000-0400"
updated: "2009-09-22T23:44:27.000-0400"
resolved: "2009-09-22T23:44:27.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

When a cache is removed from the cache manager it is
disposed and if persistent the memory is spooled the
file. But the shutdown hook is not removed from the
runtime and thus the thread and hence the reference to
the cache instance remains in memory.

In my usecase I use many cache instances which I
intermitently close and open - I use EHCache because of
the great and transparent overflow to disk and
persistency functionality.

Attached is a patch which (1) removes the shutdown hook
from the runtime and (2) actually starts the thread
such that the thread is removed from the system -
actually from the thread group. Both actions are called
from the Cache.dispose() method \1after\1 setting the
state to shutdown.

Note on starting the thread: When the shutdown hook
thread is created it is added to a thread group, which
keeps a string reference to the thread which in turn
has a strong reference to the cache instance.
consequently the cache instance will not be gc-ed even
though it has been disposed and the shutdown hook will
actually do nothing. When the thread is started it will
be removed from its thread group when the thread
terminates. As the thread is started after the cache is
set "shutdown" it will actually do nothing at all.

Alternatively, I could imagine that the Cache.dispose()
method would actually not dispose of the cache itself
but start the shutdown hook thread which in turn would
do the disposal job..
Sourceforge Ticket ID: 1474360 - Opened By: fmeschbe - 21 Apr 2006 19:10 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
