---

title: "Log rotation should allow for compression/rotation of old log files"
layout: issue
tags: 
permalink: /browse/CDV-91

issue_key: CDV-91
issue_numeric_sort_key: 91
issuetype: "New Feature"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Open"
resolution: ""
priority: "2 Major"
components: "X-System Administration"
labels: 
assignee: "prodmgmt"
reporter: "dmangot"
votes:  2
watchers: 3

created: "2007-01-17T18:44:53.000-0500"
updated: "2010-03-19T18:59:35.000-0400"
resolved: ""




---

{% raw %}

## Description

<div markdown="1" class="description">

We are currently running in full debug mode in production for a TC client logs.  They take up an incredible amount of space.  Because they are text, they would compress down very well.

This problem could be solved in one of two ways:  (and maybe more)

1. Have a flag that tells the client to compress the old logs when it rotates them.  The log rotation is already built in.

2. Have JMX or some other way of sending a 'signal' (like HUP) to the application to tell it to open a new log file.  This way the log management could be handled by some external program like Linux's logrotate or Solaris' logadm.  This way administrators would have more control over log file management.  

I believe number 2 would be the preferred way.  No need to build tons of logic into the TC client to handle log files.  Each OS already has tools to deal with this.

</div>

## Comments


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-22</span>

<div markdown="1" class="comment">

PM should look at this

</div>


{:.comment-heading}
### **Steve Harris** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

Some comments on this stuff from the forum

The DSO Install/Setup guide describes how up to 20 backup log files are created: first log.1; then log.1 is renamed log.2 and there is a new log.1; then log.2 is renamed log.3 and log.1 is renamed log.2 and there is a new log.1, etc. 

We plan to have some long-running TC clients that will eventually fill up all 20 backup logs. If I understand this correctly, I see two potential issues with how TC rolls over the logs: 

1. Every time a log fills up, 1 old log will be deleted and 20 logs will be renamed. 
2. The renaming will force the old logs to be backed up much more frequently than they should because the same content will be in file log.1 first, then later in log.2 then later in log.3, etc. 

I'd prefer to have the backup logs never get renamed; when the log fills up, just rename log to log.1, next time rename log to log.2, and so on until you rename log to log.20 at which time you delete log.1. When the log fills up again, rename it to log.1 and delete log.2, etc. (By deleting the next log in the sequence, you always know what the next full log should be renamed to.)



</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

Those numbered logs were always confusing. It is more convenient to work with log files that are named with some kind of timestamp... or maybe even allow to configure naming pattern (we are using log4j underneath, aren't we?).

</div>


{:.comment-heading}
### **Saravanan Subbiah** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

I do agree with the comment from the forum that it is completely ridiculous to keep renaming all the logs everytime the log is rotated. Even when debugging it is a hindrance since there is no way of know which is what.

Timestamp is not a bad idea but I like the numbers since it is easy to read and our log messages anyway has timestamp in it.

Also we should probably consider not deleting logs ourselves and let the sysadmin handle it externally. Irrespective of that the numbers could just be ever increasing.

</div>


{:.comment-heading}
### **Dave Mangot** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

The best would probably to stick with what Saravanan suggests and not delete the logs ourselves.  However, as someone who has to deal with logs constantly, I would like the option to be able to specify that I will control log file maintenance and just have TC continually write to a single log.  Then, when I send it a 'signal', have it rotate the logs.

There are already many tools out there for managing log files.  We certainly don't want to get into the business of log file management when this has already been solved.  It is far from the core function of Terracotta.  

For example.  Here are most of the directives available if I use logrotate on Linux:
compress
nocompress
compresscmd
gzip
uncompresscmd
gunzip
compressext
compressoptions
copy
copytruncate
create mode  owner group
mode s
nocreate option.
daily
dateext
delaycompress
extension ext
ifempty
include file\_or\_directory
mail address
mailfirst
maillast
maxage count
minsize size
missingok
monthly
nocompress
compress
nocopy
nocopytruncate
nocreate
nodelaycompress
nodateext
nomail
nomissingok
noolddir
nosharedscripts
notifempty
olddir directory
postrotate endscript
prerotate endscript
firstaction endscript
lastaction endscript
sharedscripts
start count
tabooext [+] list
weekly
yearly


Unless we plan on duplicating all these directives within the Terracotta client than you are giving me an inferior solution to one that is 1) built in to the OS  and 2) probably is already being used to manage all the other log files in the infrastructure.

It seems that Saravanan's idea is good for the developer, but in production, I want the ability to really deal with my logs how I see fit.

</div>


{:.comment-heading}
### **Sreenivasan Iyer** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

IMHO agree with Dave. 
1.
I think TC should simply provide for some control over frequency of log rotation. every 30 minutes or every hour or every 4 hours etc. but no more.
2.
Log file should have DateTimeStamp as suffix so one can easily identify when the log was rotated. i.e. if a problem was reported at 2 am i know which file to go look at, via just a "ls"
3.
And the rest is upto IT administration. They may choose to delete log files beyond a certain age or move them to a dedicated log server (large shops, where IT has some SLAs around business continuity and legal constraints)
4. 
Ofcourse for small IT shops that do not have any specified log rotation policy specification, we could add the additional config where only a certain number of log files are kept around.





</div>


{:.comment-heading}
### **Hung Huynh** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

I really the way Cruisecontrol handles the logs. Log files are suffixed with timestamp and there are 2 options to deal with them: gzip  or delete them based on some period of time

<log>
    <gzip every="3" unit="DAY">     -- unit is either DAY, WEEK, MONTH or YEAR
    <delete every="5" unit="WEEK">
</log>


</div>


{:.comment-heading}
### **Eugene Kuleshov** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

It seems like a really heated issue. Is just want to suggest that if we do in fact use log4j, then we may as well use its features. It is heavily configurable and most likely already support things suggested here (i.e. Dave may like logging to the syslog). See http://logging.apache.org/log4j/docs/documentation.html

</div>


{:.comment-heading}
### **Tim Eck** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

just my $0.02 but one solution is to not enable so much debug logging ;-) 

 If you're talking about the runtime-logging debug stuff, if it is fully enabled, you'll get a log message (with a complete stack trace) anytime a lock is acquired or a field of a shared object is updated. That kind of logging is like turning on something that logs every byte flowing through a server. 

just something to think about before building some fancy compression features

</div>


{:.comment-heading}
### **Dave Mangot** <span class="date">2007-01-23</span>

<div markdown="1" class="comment">

Funny, I'm the one who is hoping for the simplest possible solution to be the resolution of this ticket (as opposed to anything fancy).  I realize that the current logging is excessive, but that does not eliminate the need for sane production logging management.


</div>


{:.comment-heading}
### **Paul Sebby** <span class="date">2007-01-24</span>

<div markdown="1" class="comment">

I strongly support some way to configure the total number or size of log files kept around (even if the default value is "no limit").  It's so easy for someone to set up a tool like Terracotta and then people forget about the log files until the server starts running out of disk space.

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2007-02-07</span>

<div markdown="1" class="comment">

Updating Due date to last day of Moraga dev iteration

</div>


{:.comment-heading}
### **Taylor Gautier** <span class="date">2007-02-23</span>

<div markdown="1" class="comment">

I think this feature request is too late for Moraga.  I am moving to Noriega.

There is a comment that we should support the features provided by log4j.  I agree.  We should have a reasonable default setup (it should clean up log files by default, or else users will inadvertently fill up disk space) and we should have a reasonable way of letting the user use the full expressiveness of the log4j configuration.

</div>


{:.comment-heading}
### **Dave Mangot** <span class="date">2007-12-03</span>

<div markdown="1" class="comment">

+1 to Eugene's suggestion of using log4j.  Operations staff are familiar with it an can use it to feed into an existing logging structure as they see fit.  Just need to have it come initially with sane defaults.



</div>


{:.comment-heading}
### **Kunal Bhasin** <span class="date">2009-01-12</span>

<div markdown="1" class="comment">

Any updates?

</div>


{:.comment-heading}
### **Gabe Monroy** <span class="date">2009-03-31</span>

<div markdown="1" class="comment">

+1 to Dave Mangot's proposed solution of allowing Terracotta to configured to perform "dumb" logging to a single file (with no rotation/cleanup).  This would allow us to leverage standard operations tools like logrotate which do a much better job at this sort of thing.  If we can accomplish the dumb/simple logging by moving to log4j-based configuration, then that should work just fine for our purposes.

</div>



{% endraw %}
