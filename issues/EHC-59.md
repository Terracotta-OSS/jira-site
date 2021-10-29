---

title: "SelfPopulatingCache allows multiple lock holders"
layout: issue
tags: 
permalink: /browse/EHC-59

issue_key: EHC-59
issue_numeric_sort_key: 59
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

created: "2009-09-21T15:07:39.000-0400"
updated: "2009-09-22T23:44:27.000-0400"
resolved: "2009-09-22T23:44:27.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

A JUnit test has been attached that reproduces the problem.

Expected: If multiple threads try to retrieve the same
key from a SelfPopulatingCache at the same time, and
that key is not yet in the cache, one thread obtains
the lock for that key and uses the CacheEntryFactory to
generate the cache entry and all other threads wait on
the lock.  Any and all threads which timeout while
waiting for this lock should fail to acquire the lock
for that key and throw an exception.

Observed: While some threads timeout and throw an
exception as expected, some threads timeout and then
acquire the lock that the first thread already has and
go on to try to generate that cache entry in parallel
with the first thread.  Reproducing this situation, as
done in the attached test, requires that the multiple
attempts to acquire the lock for the key are slightly
spaced apart, say by 10 ms or more.

After examining the source code and stepping through
the scenario in a debugger it appears that what happens
is this:

1.  Thread-1 acquires the lock for "key1", finds that
"key1" is not in the cache and attempts to generate it
using the CacheEntryFactory.  Say this takes 15 sec or
more.
2.  Thread-2 through Thread-10 attempt to acquire the
lock with a short timeout, say 2 sec.  Each thread
makes its attempt just a little bit after the other,
say 10 ms.  Each of these threads goes into a "wait".
3.  Thread-2 (for example) times out, sees that the
lock (Mutex) is still in use and returns false from
Mutex.attempt(long).  A LockTimeoutException is thrown
from BlockingCache.get(Object).  This exception is
caught in SelfPopulatingCache.get(Serializable) and a
"put" is done using an Element with a null value.  This
"put" causes the lock for "key1" to be released, even
though Thread-2 failed to acquire the lock and Thread-1
still has the lock.
4.  Another thread, say Thread-3 times out, finds the
lock is not in use anymore (because Thread-2 released
it), acquires it and returns true from
Mutex.attempt(long).  No LockTimeoutException is thrown
and Thread-3 tries to find "key1" in the backing cache,
doesn't find it and tries to generate it using the
CacheEntryFactory.  All the while, Thread-1 still
hasn't released the lock and is still creating the
value for "key1" using the CacheEntryFactory as well.
5.  This may happen several times as other threads
timeout, throw exceptions, then release locks they
haven't held allowing other threads to acquire it.

As long as the threads are slightly staggered as they
attempt to acquire the lock, I see about 50% of the
threads expire with a timeout and 50% simultaneously
acquire the lock and try to load the entry from the
CacheEntryFactory.


I tried to create a SourceForge account, but was
getting system errors.  Here's my e-mail:

derek.guist@theplatform.com
Sourceforge Ticket ID: 1550043 - Opened By: nobody - 31 Aug 2006 19:39 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
