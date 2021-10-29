---

title: "Allow keyword configuration/integration of Terracotta with other packages (a.k.a. \"configuration bundles\")"
layout: issue
tags: 
permalink: /browse/CDV-35

issue_key: CDV-35
issue_numeric_sort_key: 35
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Duplicate"
priority: "2 Major"
components: "X-Common Code"
labels: 
assignee: "nharward"
reporter: "nharward"
votes:  0
watchers: 0

created: "2006-12-19T21:54:54.000-0500"
updated: "2012-07-27T19:59:27.000-0400"
resolved: "2007-01-15T23:41:31.000-0500"




---

{% raw %}

## Description

<div markdown="1" class="description">

Currently much of the configuration work done to support Spring, Tomcat, etc. is done in a hard-coded fashion and integration with new packages is non-trivial and cannot be done in a pluggable fashion.  The use case is to have the Terracotta user be able to add something simple like this to the configuration file:

....
   <bundles>
      ...
      <bundle name="spring" version="2.0"/>
      <bundle name="tomcat" version="5.5"/>
      ...
   </bundles>

All necessary configuration, class loading/instrumentation, etc. would be handled by the bundle.  Eventually we would like to have a central repository of commonly used or contributed bundles a la maven that could be automatically downloaded and installed locally if not already present.

</div>

## Comments


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2006-12-19</span>

<div markdown="1" class="comment">

Currently investigating some existing plugin architectures that we could use, the most obvious to me is to make use of the OSGi standard which is proven to be successful in the case of the Eclipse platform (though with some deviations from the official standard).  There will be native support in the JRE someday for this, but it looks like it won't happen until Java 7 or later, per JSR-277.

Standardized or aspiring module/plugin specifications:
   \* JSR-277, "Java Module System": http://www.jcp.org/en/jsr/detail?id=277
   \* OSGi: http://www.osgi.org/

A few open source OSGi implementations:
   \* Eclipse Equinox (EPL): http://www.eclipse.org/equinox/
   \* Knopflerfish (BSD-ish dual licensing): http://www.knopflerfish.org/
   \* Apache Felix (incubator): http://cwiki.apache.org/FELIX/index.html

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2006-12-19</span>

<div markdown="1" class="comment">

Adding another JSR to the mix, one that closely ties with OSGi and supports a more dynamic environment until JSR-277 comes into play in Java 7.

   \* JSR-291, "Dynamic Component Support for Java SE": http://jcp.org/en/jsr/detail?id=291

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2006-12-20</span>

<div markdown="1" class="comment">

Per Eugene from the tc-dev mailing list as to what the configuration bundles need to be able to do:

  Either way bundle should be able to register several things:

-- required declarative includes, locks and roots
-- custom bytecode transformers
-- AspectWerkz-based aspects

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2006-12-20</span>

<div markdown="1" class="comment">

Eugene, Juris and I are plowing along on this doing some investigation as to what technology to use or build.

</div>


{:.comment-heading}
### **Nathaniel Harward** <span class="date">2007-01-15</span>

<div markdown="1" class="comment">

This is superseded by CDV-49

</div>



{% endraw %}
