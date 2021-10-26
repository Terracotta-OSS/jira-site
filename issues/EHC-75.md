---

title: "DiskStore does not create new IndexFile"
layout: issue
tags: 
permalink: /browse/EHC-75

issue_key: EHC-75
issue_numeric_sort_key: 75
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

created: "2009-09-21T15:07:41.000-0400"
updated: "2009-09-22T23:44:28.000-0400"
resolved: "2009-09-22T23:44:28.000-0400"

---



{% raw %}



## Description

<div markdown="1" class="description">

Hi got a possible bug in net.sf.ehcache.store.DiskStore.

The ObjectInputStream may be 'null' but the
'FileInputStream ' not


original:


   private synchronized void readIndex() throws
IOException \{
        ObjectInputStream objectInputStream = null;
        if (indexFile.exists()) {
            try {
                FileInputStream fin = new
FileInputStream(indexFile);
                objectInputStream = new
ObjectInputStream(fin);
                diskElements = (HashMap)
objectInputStream.readObject();
                freeSpace = (ArrayList)
objectInputStream.readObject();
            } catch (StreamCorruptedException e) {
                LOG.error("Corrupt index file. Creating
new index. ");
                createNewIndexFile();
            } catch (IOException e) {
                LOG.error("IOException reading index.
Creating new index.");
                createNewIndexFile();
            } catch (ClassNotFoundException e) {
                LOG.error("Class loading problem
reading index. Creating new index.", e);
                createNewIndexFile();
            } finally {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                //Zero out file. If there is a dirty
shutdown, the file will be empty.
                createNewIndexFile();
            }
        } else {
            createNewIndexFile();
        }
    }




should probably look like this


    private synchronized void readIndex() throws
IOException \{
        ObjectInputStream objectInputStream = null;
        FileInputStream fin = null;
        if (indexFile.exists()) {
            try {
                fin = new FileInputStream(indexFile);
                objectInputStream = new
ObjectInputStream(fin);
                diskElements = (HashMap)
objectInputStream.readObject();
                freeSpace = (ArrayList)
objectInputStream.readObject();
            } catch (StreamCorruptedException e) {
                LOG.error("Corrupt index file. Creating
new index. ");
            } catch (IOException e) {
                LOG.error("IOException reading index.
Creating new index.");
            } catch (ClassNotFoundException e) {
                LOG.error("Class loading problem
reading index. Creating new index.", e);
            } finally {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                else if(null != fin) {
                    fin.close();
                }
                
                //Zero out file. If there is a dirty
shutdown, the file will be empty.
                createNewIndexFile();
            }
        } else {
            createNewIndexFile();
        }
    }
Sourceforge Ticket ID: 1063908 - Opened By: nobody - 10 Nov 2004 15:58 UTC

</div>

## Comments


{:.comment-heading}
### **Sourceforge Tracker** <span class="date">2009-09-21</span>

<div markdown="1" class="comment">

Logged In: YES 
user\_id=693320

Hi

I have added a check for fin and close that too if it is not null where OOS 
is null. I added a try/catch around the lot to make sure we always create 
the new index.

My index corruption test passes both before and after the change. Can 
you give me a test case that will fail on the old code, or failing that a 
scenario where it can happen so I can add a test for it. Thanks
Comment by: gregluck - 23 Nov 2004 05:14 UTC

</div>


{:.comment-heading}
### **Fiona OShea** <span class="date">2009-09-22</span>

<div markdown="1" class="comment">

Re-opening so that I can properly close out these issues and have correct Resolution status in Jira

</div>



{% endraw %}
