---

title: "multiple Ehcache with Jgroups ina single application failure"
layout: issue
tags: 
permalink: /browse/EHC-33

issue_key: EHC-33
issue_numeric_sort_key: 33
issuetype: "Bug"
project: "Ehcache Core"
project_key: "EHC"
status: "Closed"
resolution: "Fixed"
priority: "3 Minor"
components: "ehcache-jgroupsreplication"
labels: 
assignee: "drb"
reporter: "sourceforgetracker"
votes:  0
watchers: 2

created: "2009-09-21T15:07:38.000-0400"
updated: "2012-07-27T20:00:51.000-0400"
resolved: "2010-10-13T20:21:12.000-0400"

attachments:
- filename: "channelName.patch"
  author: "edalquis"
  size: 30.00 k
  mimeType: text/plain




---

{% raw %}

## Description

<div markdown="1" class="description">

Running multiple EHCacheâ€™s in a single application (web-application) ,  then adding each one to a different JGROUPS cluster fails.

The reason being that in package:  net.sf.ehcache.distribution.jgroups.JGroupManager

The Channel Name ("EH\_CACHE") is hard coded. 
notificationBus = new NotificationBus("EH\_CACHE", connect);

As a suggestion could you make it so that you can specify the Channel name as part of the properties,
to allow JGroups to uniquely communicate updates to the different caches.

<cacheManagerPeerProviderFactory class="net.sf.ehcache.distribution.jgroups.JGroupsCacheManagerPeerProviderFactory"
properties="channel=EH\_CACHE\_1:connect=UDP(...."
propertySeparator="::"


Sourceforge Ticket ID: 2810214 - Opened By: andreasbester - 22 Jun 2009 12:06 UTC

</div>

## Comments


{:.comment-heading}
### **Fiona OShea** <span class="date">2010-01-06</span>

<div markdown="1" class="comment">

Assigning these issues to Greg, so that he can decide what to do with them.

</div>


{:.comment-heading}
### **gluck** <span class="date">2010-08-30</span>

<div markdown="1" class="comment">

That seems like a good idea.

</div>


{:.comment-heading}
### **gluck** <span class="date">2010-08-30</span>

<div markdown="1" class="comment">

In trunk. Will be in ehcache-jgroupsreplication-1.4

Changes as suggested. A channel config has been added, defaulting to EHCACHE.

</div>


{:.comment-heading}
### **Juan G** <span class="date">2010-10-10</span>

<div markdown="1" class="comment">

Hi all!

I think the shared channel for EHCache is a great improvement, but I can't get it to work.

Environment I am using:
glassfish 2.1.1
EHCache 2.2 (tried with EHCache 2.2.1 and EHCache 2.3.0 too).
JGroups 2.10 (tried with JGroups 2.8 too).
ehcache-jgroups.replication-1.4 from SNAPSHOTS (2010/10/07).

Sample config:

``` 
 <cacheManagerPeerProviderFactory class="net.sf.ehcache.distribution.jgroups.JGroupsCacheManagerPeerProviderFactory"
     properties="channel=CANAL_1:connect=UDP(mcast_addr=231.12.21.132;mcast_port=45566;ip_ttl=32;
     mcast_send_buf_size=150000;mcast_recv_buf_size=80000):
     PING(timeout=2000;num_initial_members=6):
     MERGE2(min_interval=5000;max_interval=10000):
     FD_SOCK:VERIFY_SUSPECT(timeout=1500):
     pbcast.NAKACK(gc_lag=10;retransmit_timeout=3000):
     UNICAST(timeout=5000):
     pbcast.STABLE(desired_avg_gossip=20000):
     FRAG:
     pbcast.GMS(join_timeout=5000;join_retry_timeout=2000;
     shun=false;print_local_addr=true;)"
 propertySeparator="::"
     />
```

And this is what I see in the glassfish log:
\{quote\}
INFO: -------------------------------------------------------------------
GMS: address=xxxxxxx-xxxxx, cluster=EH\_CACHE, physical address=...
-------------------------------------------------------------------
\{quote\}
Am I doing anything wrong? Shouldn't "cluster" be "CANAL\_1" in this case (channel property)?

Thanks very much!







</div>


{:.comment-heading}
### **Juan G** <span class="date">2010-10-10</span>

<div markdown="1" class="comment">

I tried with the config shown in this URL: http://ehcache.org/documentation/configuration.html

The config is this:


```
 <cacheManagerPeerProviderFactory
         class="net.sf.ehcache.distribution.jgroups.JGroupsCacheManagerPeerProviderFactory"
         properties="channel=channeljuan^connect=UDP(mcast_addr=231.12.21.132;mcast_port=45566;ip_ttl=32;
         mcast_send_buf_size=150000;mcast_recv_buf_size=80000):
         PING(timeout=2000;num_initial_members=6):
         MERGE2(min_interval=5000;max_interval=10000):
         FD_SOCK:VERIFY_SUSPECT(timeout=1500):
         pbcast.NAKACK(gc_lag=10;retransmit_timeout=3000):
         UNICAST(timeout=5000):
         pbcast.STABLE(desired_avg_gossip=20000):
         FRAG:
         pbcast.GMS(join_timeout=5000;join_retry_timeout=2000;shun=false;print_local_addr=false)"
         propertySeparator="^"
     />
```


In this case I suppose I should be able to see in logs "CLUSTER=channeljuan". What I am doing wrong?

Thanks!

</div>


{:.comment-heading}
### **Juan G** <span class="date">2010-10-10</span>

<div markdown="1" class="comment">

In previous comment the property "print\_local\_addr" was true, sorry.



</div>


{:.comment-heading}
### **Juan G** <span class="date">2010-10-10</span>

<div markdown="1" class="comment">

Well, after read properly the config documentacion, I've came across this line:

\{quote\}
"Multiple JGroups clusters may be run on the same network by specifying a different CacheManager name. The name
     is used as the cluster name".
\{quote\}

After setting "name" attribute in ehcache xml now I see that name in logs.

Perhaps I didn't understand correctly the concepts. What I want is to share the same channel between different caches in one app. I have some jars (JSF related) which contains some caches. Those caches are constructed independent from each other. I would like to make that caches working in the same channel (same thread of jGroups channel for every cache), with only providing a channel name that they should share to communicate with the other cluster instances.

Is this possible somehow?

Thanks very much!




</div>


{:.comment-heading}
### **Eric Dalquist** <span class="date">2010-10-11</span>

<div markdown="1" class="comment">

Hi Juan, I did most of the JGroups integration updates in trunk (1.4-SNAPSHOT).

In trunk a single JChannel is created for each CacheManager. The logic to determine the channel name is:

```
    public String getClusterName() {
        if (this.cacheManager.isNamed()) {
            return this.cacheManager.getName();
        }
        
        return "EH_CACHE";
    }
```


So whatever name to specify in your ehcache.xml will be the name of the JGroups channel.


For your usage, how many ehcache.xml files do you have to configure your caches? If it is just one the code should only be creating a single shared JChannel.


</div>


{:.comment-heading}
### **Eric Dalquist** <span class="date">2010-10-11</span>

<div markdown="1" class="comment">

Also adding a CHANNEL\_NAME property seems like a good enhancement. It would simply get added to the cluster name resolution logic:


```
    public String getClusterName() {
        if (this.channelName != null) {
            return this.channelName;
        }
   
        if (this.cacheManager.isNamed()) {
            return this.cacheManager.getName();
        }
        
        return "EH_CACHE";
    }
```


I'll get a patch put together for that change.

</div>


{:.comment-heading}
### **Eric Dalquist** <span class="date">2010-10-11</span>

<div markdown="1" class="comment">

Adds a channel\_name property to the JGroups peer provider configuration that explicitly sets the JChannel name.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2010-10-11</span>

<div markdown="1" class="comment">

targeted to Magnum so that QA will look at it.

</div>


{:.comment-heading}
### **Juan G** <span class="date">2010-10-13</span>

<div markdown="1" class="comment">

Hi Eric. Thanks for your quick response.

I want to load two different CacheManager from two different XML, but share the Threads that are created to communicate to the other cluster instances (I thought this could be accomplished with the same channel name).
My config is like this:


```
 <cacheManagerPeerProviderFactory class="net.sf.ehcache.distribution.jgroups.JGroupsCacheManagerPeerProviderFactory"
                                     properties="connect=UDP(singleton_name=UDP_SINGLE;mcast_addr=238.255.0.3;mcast_port=45566;ip_ttl=32;
                                     mcast_send_buf_size=150000;mcast_recv_buf_size=80000):
                                     PING(timeout=2000;num_initial_members=6):
                                     MERGE2(min_interval=5000;max_interval=10000):
                                     FD_SOCK:VERIFY_SUSPECT(timeout=1500):
                                     pbcast.NAKACK(gc_lag=10;retransmit_timeout=3000):
                                     UNICAST(timeout=5000):
                                     pbcast.STABLE(desired_avg_gossip=20000):
                                     FRAG:
                                     pbcast.GMS(join_timeout=5000;join_retry_timeout=2000;shun=false;print_local_addr=true)"
                                     propertySeparator="::"
                                     />
```


As you can see above, with "singleton\_name" property I can get only one shared transport for all ehcaches (since jGroups 2.6). Unfortunately, this doesn't work well if I don't set a different "name" attribute for each <ehcache> (it throws an error when loading JGroupsBootStrapCacheLoader).

Please can you send me an example of "channel\_name" in config XML? I suppose this change in 1.4 SNAPSHOT won't be in this URL 

http://oss.sonatype.org/content/repositories/sourceforge-snapshots/net/sf/ehcache/ehcache-jgroupsreplication/1.4-SNAPSHOT/

until tomorrow, isn't it?


Thanks very much


</div>


{:.comment-heading}
### **Eric Dalquist** <span class="date">2010-10-13</span>

<div markdown="1" class="comment">

It will depend on when Greg gets the patch applied, I don't actually have commit access to ehcache.

From my level of familiarity with how the EhCache peer providers work I don't think what you want to do is easily achievable. The current JGroups code is completely written assuming that is is working within a single CacheManager. To share a JChannel between different CacheManagers the cache manager name would need to be added to the message object that is sent over the channel. Also there is no way without using something like a static field to share the JChannel object between cache managers and the problem there is how do you decide when you shutdown the JChannel? You can't simply bind it to the lifecycle of the corresponding CacheManager like it is right now.

My only thought here for a potential way to do this (and it would require some significant refactoring in the JGroups integration) would be to use something like Spring to manage the JChannel externally to the JGroups peer provider via a ThreadLocal or some other static like lookup mechanism. Again the JGroups integration code would need to be updated to handle events from multiple cache managers on a single JChannel.


</div>


{:.comment-heading}
### **Juan G** <span class="date">2010-10-13</span>

<div markdown="1" class="comment">

Hi! I think I have a working configuration to share a transport between different caches!

Environment:
ehCache 2.2.1 SNAPSHOT
ehcache-jgroupsreplication 1.4 SNAPSHOT
jGroups 2.10GA.

First we initialize the first cache (new CacheManager(URL)), with this config:


```
<?xml version="1.0" encoding="UTF-8"?>

<ehcache name="CACHE1" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="../config/ehcache.xsd">

 <defaultCache
            name="defaut"
            maxElementsInMemory="5"
            eternal="false"
            timeToIdleSeconds="20"
            timeToLiveSeconds="20"
            overflowToDisk="false"
            diskPersistent="false"
            memoryStoreEvictionPolicy="LRU"
            />


   <cacheManagerPeerProviderFactory
         class="net.sf.ehcache.distribution.jgroups.JGroupsCacheManagerPeerProviderFactory"
         properties="connect=UDP(singleton_name=UDP_SINGLE;mcast_addr=231.12.21.132;mcast_port=45566;ip_ttl=32;
         mcast_send_buf_size=150000;mcast_recv_buf_size=80000):
         PING(timeout=2000;num_initial_members=6):
         MERGE2(min_interval=5000;max_interval=10000):
         FD_SOCK:VERIFY_SUSPECT(timeout=1500):
         pbcast.NAKACK(gc_lag=10;retransmit_timeout=3000):
         UNICAST(timeout=5000):
         pbcast.STABLE(desired_avg_gossip=20000):
         FRAG:
         pbcast.GMS(join_timeout=5000;join_retry_timeout=2000;shun=false;print_local_addr=true)"
         propertySeparator="::"
     />


    <cache name="cacheApp1"
   maxElementsInMemory="1000"
   eternal="false"
   timeToIdleSeconds="1000"
   timeToLiveSeconds="1000"
   overflowToDisk="false">
        <cacheEventListenerFactory
    class="net.sf.ehcache.distribution.jgroups.JGroupsCacheReplicatorFactory"
    properties="replicateAsynchronously=true, replicatePuts=true,
replicateUpdates=true, replicateUpdatesViaCopy=false,
replicateRemovals=true" />
 <bootstrapCacheLoaderFactory class="net.sf.ehcache.distribution.jgroups.JGroupsBootstrapCacheLoaderFactory"/>
    </cache>
</ehcache>
```


Next we initialize a second cache manager with this config:


```
<?xml version="1.0" encoding="UTF-8"?>

<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="../config/ehcache.xsd">

 <defaultCache
            name="defaut"
            maxElementsInMemory="5"
            eternal="false"
            timeToIdleSeconds="20"
            timeToLiveSeconds="20"
            overflowToDisk="false"
            diskPersistent="false"
            memoryStoreEvictionPolicy="LRU"
            />

       <cacheManagerPeerProviderFactory
         class="net.sf.ehcache.distribution.jgroups.JGroupsCacheManagerPeerProviderFactory"
         properties="connect=UDP(singleton_name=UDP_SINGLE)"
         propertySeparator="::"
     />
 

    <cache name="cacheApp2"
   maxElementsInMemory="1000"
   eternal="false"
   timeToIdleSeconds="1000"
   timeToLiveSeconds="1000"
   overflowToDisk="false">
        <cacheEventListenerFactory
    class="net.sf.ehcache.distribution.jgroups.JGroupsCacheReplicatorFactory"
    properties="replicateAsynchronously=true, replicatePuts=true,
replicateUpdates=true, replicateUpdatesViaCopy=false,
replicateRemovals=true" />
 <bootstrapCacheLoaderFactory class="net.sf.ehcache.distribution.jgroups.JGroupsBootstrapCacheLoaderFactory"/>
    </cache>
</ehcache>
```


After that you'll something like this in logs:
\{quote\}
------------------------------------
GMS: address=xxxxxxxxx, cluster=CACHE1, physical address=xxxxxxxxx:yyyyy
-----------------------------------
\{quote\}
So we have a single thread for two ehcaches instances. The first ehcache must have the "name" attribute in <ehcache> tag, and the singleton\_name and UDP well configured.The second instances and beyond must have the "singleton\_name" attribute only to tell JGroups which transport we want to use.

Please tell me if what I am saying makes sense.

Thanks very much!

</div>


{:.comment-heading}
### **gluck** <span class="date">2010-10-13</span>

<div markdown="1" class="comment">

Patch committed.

</div>


{:.comment-heading}
### **gluck** <span class="date">2010-10-13</span>

<div markdown="1" class="comment">

Guys, applied Eric's patch. To remain consistent with Ehcache's configuration conventions, I renamed channel\_name to channelName. 

</div>



{% endraw %}
