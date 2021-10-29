---

title: "AW set(..) pointcut fails with final fields"
layout: issue
tags: 
permalink: /browse/CDV-83

issue_key: CDV-83
issue_numeric_sort_key: 83
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

created: "2006-05-30T18:31:03.000-0400"
updated: "2012-07-27T19:59:24.000-0400"
resolved: "2009-05-11T19:16:24.000-0400"




---

{% raw %}

## Description

<div markdown="1" class="description">

@Set pc on final field breaks it
but we should still support some constructs
(f.e before / after)

might need change in the weaving model since Reflection cannot be use

see:

package test;

import java.lang.reflect.Field;

/\*\*
\*

    * @author <a href="mailto:alex@gnilux.com">Alexandre Vasseur</a>
      */
      public class AlexRef {

public final double d = -1;

public static void main(String args[])
\{
try \{
Class cls = Class.forName("test.AlexRef");
Field fld = cls.getField("d");
fld.setAccessible(true);
AlexRef f2obj = new AlexRef();
System.out.println("d = " + f2obj.d);
fld.setDouble(f2obj, 12.34);
System.out.println("d = " + f2obj.d);
\}
catch (Throwable e) \{
System.err.println(e);
\}
\}
\}


</div>

## Comments



{% endraw %}
