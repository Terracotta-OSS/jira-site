---

title: "refreshElement( ) throws \"is not Serializable\" exception"
layout: issue
tags: 
permalink: /browse/EHC-47

issue_key: EHC-47
issue_numeric_sort_key: 47
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

UpdatingSelfPopulatingCache fails to call updateEntryValue( ) because it expects replacementElement.getValue() to return a class that implements Serializable interface.  Specially the following line in SelfPopulatingCache.refreshElement( ) is skipped

((UpdatingCacheEntryFactory) factory).updateEntryValue(key, replacementElement.getValue());

Here is snip of the stack trace:
net.sf.ehcache.CacheException: The value com.digitalimpact.catalog.assembler.cellcache.CachedCell@1a62c31 for key com.digitalimpact.catalog.assembler.cellcache.CacheKey@5c957 is not Serializable. Consider using Element#getObjectKey()
 at net.sf.ehcache.Element.getValue(Element.java:244)
 at net.sf.ehcache.constructs.blocking.SelfPopulatingCache.refreshElement(SelfPopulatingCache.java:183)
 at net.sf.ehcache.constructs.blocking.UpdatingSelfPopulatingCache.update(UpdatingSelfPopulatingCache.java:108)
 at net.sf.ehcache.constructs.blocking.UpdatingSelfPopulatingCache.get(UpdatingSelfPopulatingCache.java:79)

Work around at the mement is to make 'value' class implement Serializable interface, but not sure if that is the original intention (requirement) for updating value of elements.
Sourceforge Ticket ID: 2790350 - Opened By: k719 - 11 May 2009 23:02 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
