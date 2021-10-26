---

title: "Cache constructor bottleneck"
layout: issue
tags: 
permalink: /browse/EHC-88

issue_key: EHC-88
issue_numeric_sort_key: 88
issuetype: "Bug"
project: "Ehcache Core"
project_key: "EHC"
status: "Closed"
resolution: "Fixed"
priority: ""
components: ""
labels: 
assignee: "drb"
reporter: "sourceforgetracker"
votes:  0
watchers: 0

created: "2009-09-21T15:07:42.000-0400"
updated: "2009-09-22T23:44:29.000-0400"
resolved: "2009-09-22T23:44:29.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Hi,

The construction of a Cache instance has a bottleneck 
in the code:
   InetAddress.getLocalHost()

For most uses of EHCache, this will not be an issue.  
It is only when caches are created, used and 
discarded rapidly that the issue arises.

It would be appropriate to add the following:

private static final InetAddress LOCALHOST = 
InetAddress.getLocalHost();

This could be reused:

    private String guid;

    {
        try {
            guid = new StringBuffer()
                    .append(LOCALHOST)
                    .append("-")
                    .append(new UID())
                    .toString();
        } catch (UnknownHostException e) {
            LOG.error("Could not create GUID: " + 
e.getMessage());
        }
    }

Sourceforge Ticket ID: 1488853 - Opened By: dhulley - 15 May 2006 13:24 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

Derek

I made the change. I altered your LOCALHOST initializer a little as follows:

    
    private static InetAddress LOCALHOST; 
    
    static {
        try {
            LOCALHOST = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            LOG.error("Unable to set localhost. This prevents creation of a GUID. 
Cause was: " + e.getMessage(), e);
        }
    }

This will be in svn trunk shortly and released in ehcache-1.2.1.
Comment by: gregluck - 15 May 2006 22:53 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
