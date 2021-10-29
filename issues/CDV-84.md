---

title: "ClassInfo cache and CL hierarchy issue"
layout: issue
tags: 
permalink: /browse/CDV-84

issue_key: CDV-84
issue_numeric_sort_key: 84
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Won't Fix"
priority: "2 Major"
components: "X-AspectWerkz"
labels: 
assignee: "drb"
reporter: "jboner"
votes:  0
watchers: 0

created: "2006-05-30T18:29:09.000-0400"
updated: "2012-07-27T19:59:19.000-0400"
resolved: "2009-05-11T19:16:03.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

ClassInfo will not see changes that weaving may trigger in parent CL changes since it will read bytecode instead of going thru the weaver

use case: A in CLA, B in CLB, B extends A, CLB below CLA
aop.xml in CLA puts a mixin onto A

when B gets loaded, classinfo for B [won't / might not] see the mixin A.

=> need to check since due to B extends A, A might be loaded before B by the VM
=> same check when B has a method whose param / returns is A. Not sure if the VM fully resolve types. Actually it don't since we can have method that never gets invoke without any ClassNotFoundE.

=> to fix it:
1/ whether delegate type resolution to upper CL and if ClassNotFound then fallback with ClassInfo. Will be time consuming since will trigger A LOT of Class loading for nothing (which can break with ClassNotFound when 3rd part. jars are not all there as we know it happened on WLS)

2/ trigger weaving IN ClassInfo level itself
A bit of duplicate work..
Might be a path for optim: trigger nested weaving of A => cache it somewhere, when A gets loaded, hook check the cache.
===> issue with SystemDef changes in the between + multiple agent if we do so so must have some checks for dirty def (we need versioning of the defs) + hash on bytecode before weaving.

</div>

## Comments



{% endraw %}
