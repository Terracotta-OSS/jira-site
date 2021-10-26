---

title: "Support for multiple CacheManager(s)"
layout: issue
tags: 
permalink: /browse/EHC-90

issue_key: EHC-90
issue_numeric_sort_key: 90
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

created: "2009-09-21T15:07:43.000-0400"
updated: "2009-09-22T23:44:29.000-0400"
resolved: "2009-09-22T23:44:29.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

I'm working on an application where I want to use
ehcache for disk based persistence.  I'm pleased this
is so easy to do.

In my particular case I want to be able to maintain the
disk cache's in different locations on the disk (user
space).  To do this, it seems I need multiple instances
of CacheManager.

I was able to hack my version of ehcache1.1 to support
this by adding the following method to CacheManager:

    public static CacheManager
createNewInstance(InputStream inputStream) throws
CacheException \{
        return new CacheManager(inputStream);
     }


Here is an example of how I am using this:

  StringBuffer sb = new StringBuffer();
  final String cache1dir = "/tmp/foo";
  sb.append("<ehcache>")
      .append("<diskStore path='"+cache1dir+"'/>")
   .append("<defaultCache />")
   .append("</ehcache>");
  ByteArrayInputStream bin = new
ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
  CacheManager manager1 =
CacheManager.createNewInstance(bin);
  final boolean bCacheToDisk = true;
  final boolean bEternal = true;
  final Cache cache1 = new Cache(".DO\_NOT\_REMOVE",
1000, bCacheToDisk, bEternal, 0, 0,bCacheToDisk,120);
  manager1.addCache(cache1);
  Element el= cache1.get("name");
  if(el != null)\{
   System.out.println("name(1) is "+(String)el.getValue());
  \}else\{
   System.out.println("no name(1)");
  \}
  Element element = new Element("name", "FirstCache!");
  cache1.put(element);
  sb = new StringBuffer();
  final String cache2dir = "/tmp/bar";
  sb.append("<ehcache>")
      .append("<diskStore path='"+cache2dir+"'/>")
   .append("<defaultCache />")
   .append("</ehcache>");
  bin = new
ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
  CacheManager manager2 =
CacheManager.createNewInstance(bin);
  Cache cache2 = new Cache(".DO\_NOT\_REMOVE", 1000,
bCacheToDisk, bEternal, 0, 0,bCacheToDisk,120);
  manager2.addCache(cache2);
  el= cache2.get("name");
  if(el != null)\{
   System.out.println("name(2) is "+(String)el.getValue());
  \}else\{
   System.out.println("no name(2)");
  \}
  Element element2 = new Element("name", "SecondCache!");
  cache2.put(element2);
  element = new Element("name", "FirstCache - updated!");
  cache1.put(element);

Best regards,
Robert.
Sourceforge Ticket ID: 1159768 - Opened By: fuller - 9 Mar 2005 12:29 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
