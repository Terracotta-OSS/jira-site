---

title: "Classloader bug"
layout: issue
tags: 
permalink: /browse/EHC-92

issue_key: EHC-92
issue_numeric_sort_key: 92
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

The use of
Thread.currentThread().getContextClassLoader() to
obtain the classloader for creating new instances
doesn't work properly in all cases - the
contextClassLoader isn't always updated.

The usual approach in this situation is to try either
the classloader used by the class, OR the
contextClassLoader, and then, if the class is not
found, fall back to the other.

Case in point: hloader can use ehcache depending on the
config file being used. Using hloader inside Ant
currently requires ehcache on the system class path,
while with ehcache 1.1 it was sufficient to put in on
the task's classpath.
Sourceforge Ticket ID: 1506399 - Opened By: robertdw - 14 Jun 2006 23:38 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\1id=693320

Hi

I have added a fallback class loader which is tried if the context class loader 
fails.

/\1\1
     \1 Gets a fallback <code>ClassLoader</code> that all classes in ehcache, 
and extensions, should use for classloading.
     \1 This is used if the context class loader does not work.
     \1 @return the <code>ClassLoaderUtil.class.getClassLoader();</code>
     \1/
    public static ClassLoader getFallbackClassLoader() {
        return ClassLoaderUtil.class.getClassLoader();
    }

The code that uses it is:

try {
            clazz = Class.forName(className, true, getStandardClassLoader());
        } catch (ClassNotFoundException e) {
            //try fallback
            try {
                clazz = Class.forName(className, true, getFallbackClassLoader());
            } catch (ClassNotFoundException ex) {
                throw new CacheException("Unable to load class " + className + ". 
Initial cause was " + e.getMessage(), e);
            }
        }

This is in trunk and will be in ehcache-1.2.1.

Greg
Comment by: gregluck - 15 Jun 2006 10:54 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
