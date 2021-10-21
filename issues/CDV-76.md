---

title: "potential case problem with context paths for demo apps manually deployed"
layout: issue
tags: 
permalink: /browse/CDV-76

issue_key: CDV-76
issue_numeric_sort_key: 76
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "Sessions"
labels: 
assignee: "alex"
reporter: "teck"
votes:  0
watchers: 0

created: "2007-01-17T17:16:11.000-0500"
updated: "2012-07-27T19:59:16.000-0400"
resolved: "2007-02-20T13:56:22.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Not sure if there is a problem here, but we might want to look at the code for the Cart demo to see if it too dependent on the case of the context path under which it is deployed. My guess is that you can get the context path /cart (lowercase 'c') if the war is named cart.war (lowercase 'c'). The code in the app probably can use something on the request or what not to create it's links.

The original report:

BTW, anyone else notice a problem with the cart demo application 
relating to case-sensitivity for Tomcat and Geronimo outside of the 
configurator environment?  Seems that the button click results in an 
upper-case Cart which isn't found... Replacing it with a lowercase c 
works fine..

Specifically...
http://localhost:8080/Cart?item=X-files+movie&submit=add -vs-
http://localhost:8080/cart?item=X-files+movie&submit=add

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

Updating Due date to last day of Moraga dev iteration

</div>


{:.comment-heading}
### **Alex Voskoboynik** <span class="date">2007-02-20</span>

<div markdown="1" class="comment">

currently, our dist comes with Cart.war, which is when deployed under Tomcat (outside of configurator, and in the absence of any manual config changes) deploys under /Cart context.  This means that only URL's with 'C' will work.  
E.g. http://localhost:8080/cart?item=NIN+CD&submit=add is broken (as it should be); while http://localhost:8080/Cart?item=NIN+CD&submit=add works fine 

</div>



{% endraw %}
