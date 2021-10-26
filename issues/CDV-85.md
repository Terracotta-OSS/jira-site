---

title: "Mixins + Advisable: preparing constructors causes error"
layout: issue
tags: 
permalink: /browse/CDV-85

issue_key: CDV-85
issue_numeric_sort_key: 85
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

created: "2006-05-30T18:27:35.000-0400"
updated: "2012-07-27T19:59:21.000-0400"
resolved: "2009-05-11T19:15:44.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Advisable: preparing constructors causes error - ordering issue since: JP accesses field in target that is init in ctor that has not yet been called since called from JP

causes NPE, but now only occurs in the proxy impl when used together with intercept impl.

might be other issues with regular mixin use as well, don't know
now it seems to happen only when you tweak the definition

See comment is DocumentParser line: 1138
/\*
DefinitionParserHelper.createAndAddAdvisableDef(
// TODO add ctor to expression - BUT: problem with mixin and ctor, ordering issue, Jp.invoke() calls field instance that has not been init yet in ctor (since body not invoked)
"(execution(!static \* .(..)) && " + withinPointcut + ')',
definition
);
\*/

fix this code when issue solved (along with the other TODOs in the same method)

</div>

## Comments



{% endraw %}
