---

title: "stack traces for NPE, IlllegalMonitorStateException, UnlockedSharedObjectException, etc should not include terracotta stack frames"
layout: issue
tags: 
permalink: /browse/CDV-69

issue_key: CDV-69
issue_numeric_sort_key: 69
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Open"
resolution: ""
priority: "2 Major"
components: "DSO:L1"
labels: 
assignee: "prodmgmt"
reporter: "teck"
votes:  0
watchers: 0

created: "2006-06-12T19:15:43.000-0400"
updated: "2010-03-19T19:00:02.000-0400"
resolved: ""

---



{% raw %}



## Description

<div markdown="1" class="description">

Currently there are a number of exceptions that a user can naturally create, but might steer blame towards terrracotta. For example, if you attempt to modify a null reference from DSO instrumented code, you'll get an exception like this:

java.lang.NullPointerException
     at com.tc.object.bytecode.hook.impl.ArrayManager.objectArrayChanged(ArrayManager.java:64)
     at com.tc.object.bytecode.ManagerUtil.objectArrayChanged(ManagerUtil.java:606)
     at com.tctest.CloneTestApp.run(CloneTestApp.java:150)

Looking at that you might assume the problem is in that peksy terracotta class, but really the problem is in that application code. It seems like all \*common\* exceptions should look like they originate from the app level code. This means regular java things like NullPointerException, ArrayIndexOutOfBoundsException, IllegalMonitorStateException.. in addition to Terracotta specific exceptions like ReadOnlyException, NonPortableException, UnlockedSharedObjectException.

It'snot even that crazy to implement (probably)...there is a fixed set of entry points into DSO from instrumented code (Manager, ArrayManager and TCObject). For certain classes of exceptions, we can trim off the stacks up to an including the Terracotta entry point frame

</div>

## Comments



{% endraw %}
