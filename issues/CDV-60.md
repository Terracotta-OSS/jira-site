---

title: "FastAopProxy failed to follow the same semantics of Spring Proxy for the \"before\" JPs with 0 "
layout: issue
tags: 
permalink: /browse/CDV-60

issue_key: CDV-60
issue_numeric_sort_key: 60
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Open"
resolution: ""
priority: "2 Major"
components: "X-AspectWerkz"
labels: 
assignee: "prodmgmt"
reporter: "lyi"
votes:  0
watchers: 0

created: "2006-07-15T00:16:14.000-0400"
updated: "2010-03-19T18:59:33.000-0400"
resolved: ""

---



{% raw %}



## Description

<div markdown="1" class="description">

For before advice

public class SimpleBeforeAdvice implements MethodBeforeAdvice {
  public void before(Method method, Object[] args, Object target) throws Throwable {
     ...
  }
}

on JP of  "MessageWriter.writeMessage()"

It passes  "null" args to the advice, while Spring proxy passes "empty" args.

</div>

## Comments



{% endraw %}
