---

title: "Bounded MemoryStore leak - 1.6b5 w/large caches & heavy keys"
layout: issue
tags: 
permalink: /browse/EHC-49

issue_key: EHC-49
issue_numeric_sort_key: 49
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
updated: "2009-09-22T23:44:26.000-0400"
resolved: "2009-09-22T23:44:26.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Hi Greg,

The new sampling algorithm used for eviction from the MemoryStore when maxElementsInMemory > TOO\_LARGE\_TO\_EFFICIENTLY\_ITERATE (5000) causes a very marked memory usage increase when the following conditions are met:

1) maxElementsInMemory > 5000 (this selects the keyArray sampling technique, otherwise it does not apply)
2) maxElementsInMemory is oversized with respect to actual cache key cardinality (eg: maxElementsInMemory = 10000 with perhaps only about 1000 unique keys)
3) The cache key objects are "heavy" (eg: neither primitive wrappers nor Strings, perhaps with a moderate object graph attached)
4) The cache key objects are not pooled (meaning that they correctly implements equals() and hashCode() but they do not evaluate true with identity == comparison - this means that key objects are built on demand when necessary)

Our applications happen to meet all of the above criteria for several caches. Let me describe the use case. We cache remote method invocations (Spring remoting + customized spring-modules-cache) and cache keys contain references to actual method arguments, which in turn in many cases are Hibernate detached instances. Now, let's analyze each of the above criteria with this use case:

1) We've configured many of our caches to hold maxElementsInMemory = 10000
2) Actual cache cardinality is between 1000 and 5000. Since setting a higher number is not supposed to increase memory usage more than that of actual cache key cardinality (at least up to EhCache 1.5.0), this accomodates future growth in the domain model without having to constantly tweak cache configuration parameters.
3) The cache keys are custom Serializable objects which reference the method signature plus all actual arguments.
4) The cache keys are built on demand from an AOP alliance MethodInvocation whenever needed. They correctly implement equals() and hashCode(), however they are not pooled. In turn, method arguments are often Hibernate detached instances. The fact that they are detached is important, since this means that there may be many equal (but non-identical) object references in memory for a single logical entity instance.

You may argue that many of the above issues affect cache key quality/overhead and it's true - in fact we have several optimizations in place to avoid excessive memory usage and all was going well until we tried out EhCache 1.6.0 betas. The optimization we use most of the time is replacing Hibernate entities with their identifiers when constructing the cache key from the MethodInvocation and its arguments. However, one of our cached methods has an argument that is not a Hibernate entity, but CONTAINS an internal reference to a Hibernate entity. Thus, our optimization did not kick in.

Before you discard this as a programming error, let's analyze the difference in behaviour between 1.5.0 and 1.6.0 betas. In previous versions, with a cache key cardinality of 1000 the memory consumption of this particular case was bounded at (cache key cardinality = 1000) \* (size in memory of the suboptimal cache key). However, in 1.6.0, this value is bounded at:

((maxElementsInMemory = 10000) + (cache key cardinality = 1000)) \* (size in memory of the suboptimal cache key)

This is 11x the memory consumption in our example. The reason is behind keyArray which is used for sampling keys eligible for eviction. Since maxElementsInMemory > 5000, a keyArray[maxElementsInMemory] is created at MemoryStore construction-time. keySamplePointer is incremented every single time an Element is put in the cache, and every position is filled with a cache key reference until it wraps around and starts over again. This means that even though cache key cardinality may be 1, eventually there will be maxElementsInMemory references to the same logical cache key (though they may be different object instances). If the key is lightweight, pooled or interned then it's not a problem at all. However when it's not (as in our example) and when it's big (again, as in our example) it's a recipe for disaster.

Our production systems crashed with OutOfMemoryErrors and large-scale paging/disk swapping after a few hours because of this when we deployed using EhCache 1.6.0 beta4 and beta5 as well. Going back to EhCache 1.5.0 solved the problem.

The sampling algorithm when maxElementsInMemory < 5000 does not suffer this problem since keyArray is not used, and the only references to cache keys are the cache keys of the underlying ConcurrentHashMap. Thus, there will never be multiple cache key instances for the same logical cache key.

We can solve our specific problem by applying necessary optimizations at the relevant MethodInvocations (heck, we now realize we needed to do this before anyway). But perhaps other use cases are not able to change their cache keys for whatever reason. I see two possible solutions to this:

1) Make the sampling algorithm configurable, so that the default behaviour of choosing the keyArray technique by using the maxElementsInMemory > 5000 heuristic can be overridden. This can result in performance penalties as suggested by the MemoryStore JavaDoc.
2) Use canonical references (for instance, using a WeakHashMap) for the keys held in the keyArray as well as the underlying ConcurrentHashMap in order to guarantee at most a single instance of each logical cache key. This probably has other side benefits as well.

I'd appreciate any comments. It would be nice to have this resolved before the 1.6.0 final release.

Sourceforge Ticket ID: 2791732 - Opened By: mads1980 - 14 May 2009 14:17 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
