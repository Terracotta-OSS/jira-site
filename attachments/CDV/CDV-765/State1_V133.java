// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tc.state.idx143.com.example;

import com.tc.object.ObjectID;
import com.tc.object.dna.api.DNAWriter;
import com.tc.objectserver.managedobject.PhysicalManagedObjectState;
import com.tc.objectserver.managedobject.bytecode.ClassNotCompatableException;
import gnu.trove.THashSet;
import java.io.*;
import java.util.Map;
import java.util.Set;

public class State1_V133 extends PhysicalManagedObjectState
{

    public ObjectID getParentID()
    {
        return parentId;
    }

    public void setParentID(ObjectID objectid)
    {
        parentId = objectid;
    }

    public String getClassName()
    {
        return "com.example.State1";
    }

    public String getLoaderDescription()
    {
        return "Standard.system";
    }

    public Set getObjectReferences()
    {
        THashSet thashset = new THashSet(11);
        if((Object.this instanceof ObjectID) && !((ObjectID)Object.this).isNull())
            thashset.add(Object.this);
        if((field_1 instanceof ObjectID) && !((ObjectID)field_1).isNull())
            thashset.add(field_1);
        thashset.add(parentId);
        return thashset;
    }

    protected Object basicSet(String s, Object obj)
    {
        if("com.example.State1.this$1".equals(s))
        {
            Object obj1 = Object.this;
            this$1_0 = obj;
            return obj1;
        }
        if("com.example.State1.field".equals(s))
        {
            Object obj2 = field_1;
            field_1 = obj;
            return obj2;
        } else
        {
            throw new ClassNotCompatableException("Not found ! field = " + s + " value = " + obj);
        }
    }

    protected void basicDehydrate(DNAWriter dnawriter)
    {
        dnawriter.addPhysicalAction("com.example.State1.this$1", Object.this, true);
        dnawriter.addPhysicalAction("at.molindo.songtexte.pages.HomePage$SongtexteLoginPanel._legacyUserService", field_1, true);
    }

    public Map addValues(Map map)
    {
        map.put("com.example.State1.this$1", Object.this);
        map.put("at.molindo.songtexte.pages.HomePage$SongtexteLoginPanel._legacyUserService", field_1);
        return map;
    }

    public void writeObject(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(Object.this);
        objectoutput.writeObject(field_1);
    }

    public void readObject(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        this$1_0 = objectinput.readObject();
        field_1 = objectinput.readObject();
    }

    protected int getClassId()
    {
        return 133;
    }
  
    private ObjectID parentId;
    private Object this$1_0;
    private Object field_1;
}
