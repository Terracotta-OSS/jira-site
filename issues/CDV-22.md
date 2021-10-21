---

title: "System cannot find the path specified message is displayed when dso-env.bat is executed"
layout: issue
tags: 
permalink: /browse/CDV-22

issue_key: CDV-22
issue_numeric_sort_key: 22
issuetype: "Bug"
project: "Community Development (Terracotta Server)"
project_key: "CDV"
status: "Closed"
resolution: "Fixed"
priority: "2 Major"
components: "Kit & Installation"
labels: 
assignee: "juris"
reporter: "kkannaiy"
votes:  0
watchers: 0

created: "2006-12-04T13:18:53.000-0500"
updated: "2007-04-04T16:45:44.000-0400"
resolved: "2006-12-11T17:45:24.000-0500"

---



{% raw %}



## Description

<div markdown="1" class="description">

Steps:

1. dso kit is installed in C:\Program Files\Terracotta\
2. Run the script dso-env.bat from C:\Program Files\Terracotta\terracotta-2.2\dso\bin

Actual:

'Files\Terracotta\terracotta-2.2\dso\bin\..\..' is not recognized as an internal
 or external command,
operable program or batch file.
The system cannot find the path specified.
-Xbootclasspath/p:"C:\Program Files\Terracotta\terracotta-2.2\common\lib\dso-boo
t\dso-boot-hotspot\1win32\1150\109.jar" -Dtc.install-root="C:\Program Files\Terraco
tta\terracotta-2.2"



</div>

## Comments


{:.comment-heading}
### **Gary Keim** <span class="date">2006-12-04</span>

<div markdown="1" class="comment">

Appears the dso-env script doesn't handle spaces correctly.


</div>


{:.comment-heading}
### **Juris Galang** <span class="date">2006-12-06</span>

<div markdown="1" class="comment">

This happens for all the batch file script when we run it from the DOS console.
Double-clicking on the batch file from the Windows Explorer, will run it correctly,


</div>


{:.comment-heading}
### **Kalai Kannaiyan** <span class="date">2006-12-06</span>

<div markdown="1" class="comment">

except dso-env.bat, other scripts are running fine from comman prompt.

</div>


{:.comment-heading}
### **Juris Galang** <span class="date">2006-12-06</span>

<div markdown="1" class="comment">

See rev 267

Had to modify the first lines in dso-env.bat

Also modified the behavior for the tc-function call to tc\1set\1dso\1boot\1jar in both the batch and shell script so that it wont print the location of the DSO boot jar anymore when the DSO\1BOOT\1JAR variable is already set --- this allows the user to call the dso-env script several times and get the same results.

</div>


{:.comment-heading}
### **Kalai Kannaiyan** <span class="date">2006-12-11</span>

<div markdown="1" class="comment">

Retested this issue with Rev #312 Terracotta Enterprise Edition 2.2, 
Run dso-env.bat , it is not printing any variable in the command prompt. 


</div>


{:.comment-heading}
### **Juris Galang** <span class="date">2006-12-11</span>

<div markdown="1" class="comment">

The -v parameter needs to be passed  for dso-env to display the value of TC\1JAVA\1OPTS

I've switched it so that the default action for dso-env is to print TC\1JAVA\1OPTS, that way it will work if it was invoked via the Windows Explorer (no params), or from the command-line.

Pass -q to make it quiet.

This was done in rev 386 of the 2.2.0 branch.

</div>


{:.comment-heading}
### **Kalai Kannaiyan** <span class="date">2006-12-14</span>

<div markdown="1" class="comment">

Retested this issue with rev #441 (Terracotta Enterprise Edition Version 2.2.0, as of 20061214-081239 (Revision 441 by SYSTEM@WXPMO0 from 2.2.0), it is working as expected.

</div>



{% endraw %}
