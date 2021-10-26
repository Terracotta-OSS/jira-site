---

title: "tc-config <logs> element supports too many wildcards -- there is no unit test for these wildcards nor is it even possible to tests all scenarios"
layout: issue
tags: 
permalink: /browse/CDV-93

issue_key: CDV-93
issue_numeric_sort_key: 93
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Won't Fix"
priority: "2 Major"
components: "X-Common Code"
labels: 
assignee: "hhuynh"
reporter: "eellis"
votes:  0
watchers: 0

created: "2007-01-23T19:20:15.000-0500"
updated: "2007-06-04T13:58:07.000-0400"
resolved: "2007-05-03T19:48:10.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

ParameterSubstituter should only support two or three wildcards and should have a unit test to verify that they work correctly. Currently d, h, i, H, n, o, a, v, t, and all environment variables are supported. This is almost impossible to validate and complete overkill.


switch (nextChar) \{
          case 'd':
            value = getUniqueTempDirectory();
            break;

          case 'h':
            value = getHostname();
            break;

          case 'i':
            value = getIpAddress();
            break;

          case 'H':
            value = System.getProperty("user.home");
            break;

          case 'n':
            value = System.getProperty("user.name");
            break;

          case 'o':
            value = System.getProperty("os.name");
            break;

          case 'a':
            value = System.getProperty("os.arch");
            break;

          case 'v':
            value = System.getProperty("os.version");
            break;

          case 't':
            value = System.getProperty("java.io.tmpdir");
            break;

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

Updating Due date to last day of Moraga dev iteration

</div>


{:.comment-heading}
### **Taylor Gautier** <span class="date">2007-02-23</span>

<div markdown="1" class="comment">

Not a pressing issue for the current release - will re-evaluate for Noriega.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-04-10</span>

<div markdown="1" class="comment">

Assigning to QA to run tests

</div>



{% endraw %}
