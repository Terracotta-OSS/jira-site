---

title: "Incorrect host IP calculation if using vmware and rmi"
layout: issue
tags: 
permalink: /browse/EHC-34

issue_key: EHC-34
issue_numeric_sort_key: 34
issuetype: "Bug"
project: "Ehcache Core"
project_key: "EHC"
status: "Open"
resolution: ""
priority: "3 Minor"
components: "ehcache-core"
labels: 
assignee: ""
reporter: "sourceforgetracker"
votes:  0
watchers: 1

created: "2009-09-21T15:07:38.000-0400"
updated: "2010-01-06T18:57:12.000-0500"
resolved: ""

---



{% raw %}



## Description

<div markdown="1" class="description">

Incorrect host IP calculation if using VMWare Workstation 6.5.2 and distributed cache using manual rmiURL configuration and fully qualified ip

When a fully qualified  ip/hostname is assigned to parameter hostname of cacheManagerPeerListenerFactory and manual rmiURL is used for cacheManagerPeerProviderFactory
then ehcache should use/prioritize  that ip/hostname address instead of getting the first one of the availables newtwork adaptes.

The versions i tested was 1.6 final and 1.6 rc2, when i disabled the virtual vmware networks adapters of the first machine every think worked correctly.

My Enviroment:
::::::::::::::::::::::::::::::

First machine (10.100.1.106)
--------------------------------------
Windows 7 rc1 64 bits
IntelliJ IDEA 8.1.3 running inside tomcat 6.0.20
Java 1.6.14 32 bits for both
This machine has many networks adapters including:  10.100.1.106  (Wireless LAN adapter Wireless Network Connection) and 192.168.136.1  (Ethernet adapter VMware Network Adapter VMnet1)

Second Machine (10.100.1.13)
-----------------------------------------
Windows Server 2003 r2
Tomcat 5.5.27 server
Java 1.5.16 32 bits
Only one Network adapter 10.100.1.13  (Ethernet adapter Local Area Connection)

ehcache.xml: (First machine (10.100.1.106))

    <cacheManagerPeerProviderFactory class=
                          "net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory"
                          properties="peerDiscovery=manual,
                          rmiUrls=//10.100.1.13:40001/userSessionIdCache|//10.100.1.106:40001/userSessionIdCache|//10.100.1.13:40001/configurationCache|//10.100.1.106:40001/configurationCache"/>

    <cacheManagerPeerListenerFactory
            class="net.sf.ehcache.distribution.RMICacheManagerPeerListenerFactory"
            properties="hostName=10.100.1.106, port=40001,socketTimeoutMillis=2000"
            />

ehcache.xml: (Second machine)

    <cacheManagerPeerProviderFactory class=
                          "net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory"
                          properties="peerDiscovery=manual,
                          rmiUrls=//10.100.1.13:40001/userSessionIdCache|//10.100.1.106:40001/userSessionIdCache|//10.100.1.13:40001/configurationCache|//10.100.1.106:40001/configurationCache"/>

    <cacheManagerPeerListenerFactory
            class="net.sf.ehcache.distribution.RMICacheManagerPeerListenerFactory"
            properties="hostName=10.100.1.13, port=40001,socketTimeoutMillis=2000"
            />

The problem is that when second machine wants to send a update notification for the caches to machine one
it sends the message to machine  192.168.136.1 instead of to machine 10.100.1.106.

How does machine 2 knows of ip 192.168.136.1?  Guessing it looks like EHCACHE sends its address to its peer 
but instead of using the one provided in parameter hostname it recalculates the address and send it back.

So machine 2 cannot communicate to machine one, when i disabled the virtual vmware networks all works correctly even using hostname=localhost in both machines for cacheManagerPeerListenerFactory

This is my log4j configuration:
---------------------------------------

log4j.logger.net.sf.ehcache=INFO
log4j.logger.net.sf.ehcache.config=DEBUG
log4j.logger.net.sf.ehcache.distribution=DEBUG

It will e easier if cacheManagerPeerListenerFactory could use automatically the ips and ports defined in cacheManagerPeerProviderFactory 

Additionally  i added as attachment the partial logs files for both machines and  ehcache.xml file for the first machine
Sourceforge Ticket ID: 2809212 - Opened By: aldo\1gg - 19 Jun 2009 19:47 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Ald

Thanks for the detailed bug report. There are two things I need:

1) Your config is incorrect. On the first machine each IP in the urls should be 106. It is the hostname of the URLs you are advertising:

miUrls=//10.100.1.106:40001/userSessionIdCache|//10.100.1.106:40001/configurationCache"/>

rmiUrls=//10.100.1.13:40001/userSessionIdCache|//10.100.1.13:40001/configurationCache"/> 

i.e. each configuration is specific to that host.

The behaviour of ehcache with your original configuration is undefined.


I checked the sample ehcache.xml and see that it is not clearly expressed. I have fixed that and will publish an update to the site with the new sample. The sample ehcache.xml now reads, inter alia:

Manual discovery requires a unique configuration per host. It is contains a list of rmiURLs for the peers, other than itself. So, if we have server1, server2 and server3 the configuration will be:

    In server1's configuration:
    <cacheManagerPeerProviderFactory class=
                          "net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory"
                          properties="peerDiscovery=manual,
                          rmiUrls=//server2:40000/sampleCache1|//server3:40000/sampleCache1
                          | //server2:40000/sampleCache2|//server3:40000/sampleCache2"
                          propertySeparator="," />
                          
    In server2's configuration:
    <cacheManagerPeerProviderFactory class=
                          "net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory"
                          properties="peerDiscovery=manual,
                          rmiUrls=//server1:40000/sampleCache1|//server3:40000/sampleCache1
                          | //server1:40000/sampleCache2|//server3:40000/sampleCache2"
                          propertySeparator="," />
                          
    In server3's configuration:
    <cacheManagerPeerProviderFactory class=
                          "net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory"
                          properties="peerDiscovery=manual,
                          rmiUrls=//server1:40000/sampleCache1|//server2:40000/sampleCache1
                          | //server1:40000/sampleCache2|//server2:40000/sampleCache2"
                          propertySeparator="," />

The RMI documentation page has however always been correct.

2) I have added some FINEST logging to RMICacheManagerPeerProvider to spit out the URL we attempt to lookup. I am concerned the problem could lay in Naming.lookup() in Java. Please grab the latest snapshot (http://oss.sonatype.org/content/repositories/sourceforge-snapshots/net/sf/ehcache/ehcache/1.6.1-SNAPSHOT/) and retest. 

I cannot set logging in Naming.lookup(). If you have access to a debugger, please set a breakpoint at line 126 in RMICacheManagerPeerProvider and let me know what endpoint is reported. I think this is where the switch may be happening.

Greg

Comment by: gregluck - 12 Jul 2009 00:57 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2010-01-06</span>

<div markdown="1" class="comment">

Assigning these issues to Greg, so that he can decide what to do with them.

</div>



{% endraw %}
