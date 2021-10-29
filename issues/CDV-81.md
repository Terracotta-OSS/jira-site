---

title: "Option to specify DSO root names for matching Spring App context"
layout: issue
tags: 
permalink: /browse/CDV-81

issue_key: CDV-81
issue_numeric_sort_key: 81
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Open"
resolution: ""
priority: "3 Minor"
components: "SpringRuntime"
labels: 
assignee: "prodmgmt"
reporter: "ekulesho"
votes:  2
watchers: 2

created: "2006-10-03T19:57:02.000-0400"
updated: "2010-03-19T18:59:57.000-0400"
resolved: ""

attachments:
- filename: "errors.txt"
  author: "ekulesho"
  size: 19.00 k
  mimeType: text/plain




---

{% raw %}

## Description

<div markdown="1" class="description">

We should allow to assign aliases for the application contexts. So, such alias will be used to identify roots created for the Spring's application contexts in place where we using "synthetic id" now. 

That would also allow us to share bean instances from different apps (even between standalone app and one running in tomcat) as long as they using the same aliases.

To support that we'll need to add an "alias" attribute to <application-context> element in Spring config:

<application>
  <spring>
    <jee-application name="*">
      <application-contexts>
        <application-context alias="clusteredServices">
          <paths>
            <path>*/context.xml</path>
          </paths>
          <beans>
            <bean name="master" />
            <bean name="queue" />
          </beans>
        </application-context>
      </application-contexts>
    </jee-application>
  </spring>
</application>

That is very simple but important addition and will only affect how we create name for the roots.

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2006-10-04</span>

<div markdown="1" class="comment">

Do you want this included in Lawton

</div>


{:.comment-heading}
### **Dileo Moreira** <span class="date">2006-10-25</span>

<div markdown="1" class="comment">

After consulting with Eugene, he now suggests we use the same notation as for DSO roots.

New notation:
  <application-context>
     <root-name>clusteredServices</root-name>

This looks good to me.


</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-02-05</span>

<div markdown="1" class="comment">

I've implemented new configuration element and was able to map beans from different contexts to the same root. But unfortunately it wasn't too useful, because of this error. It seems like we need to figure out how to map or rename classloaders between the nodes to make such configuration work.

Caused by: com.tc.exception.TCRuntimeException: java.lang.ClassNotFoundException: No registered loader for description: Tomcat.context:/jmx, trying to load com.tcspring.ComplexBeanId
        at com.tc.object.ClientObjectManagerImpl.lookup(ClientObjectManagerImpl.java:502)
        at com.tc.object.ClientObjectManagerImpl.lookupObject(ClientObjectManagerImpl.java:399)
        at com.tc.object.ClientObjectManagerImpl.lookupObject(ClientObjectManagerImpl.java:392)
        at com.tc.object.applicator.HashMapApplicator.getObjectForKey(HashMapApplicator.java:137)
        at com.tc.object.applicator.HashMapApplicator.apply(HashMapApplicator.java:102)
        at com.tc.object.applicator.HashMapApplicator.hydrate(HashMapApplicator.java:92)
        at com.tc.object.TCClassImpl.hydrate(TCClassImpl.java:152)
        at com.tc.object.TCObjectImpl.hydrate(TCObjectImpl.java:103)
        ... 21 more
Caused by: java.lang.ClassNotFoundException: No registered loader for description: Tomcat.context:/jmx, trying to load com.tcspring.ComplexBeanId
        at com.tc.object.loaders.StandardClassProvider.getClassFor(StandardClassProvider.java:38)
        at com.tc.object.ClientObjectManagerImpl.lookup(ClientObjectManagerImpl.java:494)
        ... 28 more


</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-12-04</span>

<div markdown="1" class="comment">

Pacheco PRD Priority 96

</div>



{% endraw %}
