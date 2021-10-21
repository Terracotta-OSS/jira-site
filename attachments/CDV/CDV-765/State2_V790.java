// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tc.state.idx143.com.example;

import com.tc.object.ObjectID;
import com.tc.object.dna.api.DNAWriter;
import gnu.trove.THashSet;
import java.io.*;
import java.util.Map;
import java.util.Set;

public class State1_V790 extends State1_V133
{

    public ObjectID getParentID()
    {
        return parentId;
    }

    public void setParentID(ObjectID objectid)
    {
        parentId = objectid;
    }

    public Set getObjectReferences()
    {
        THashSet thashset = new THashSet(2);
        Set set = super.getObjectReferences();
        thashset.addAll(set);
        if((_userService_0 instanceof ObjectID) && !((ObjectID)_userService_0).isNull())
            thashset.add(_userService_0);
        if((_legacyUserService_1 instanceof ObjectID) && !((ObjectID)_legacyUserService_1).isNull())
            thashset.add(_legacyUserService_1);
        return thashset;
    }

    protected Object basicSet(String s, Object obj)
    {
        if("com.example.AbstractBase.newField".equals(s))
        {
            Object obj1 = newField_0;
            newField_0 = obj;
            return obj1;
        } else
        {
            return super.basicSet(s, obj);
        }
    }

    protected void basicDehydrate(DNAWriter dnawriter)
    {
        super.basicDehydrate(dnawriter);
        dnawriter.addPhysicalAction("com.example.AbstractBase.newField", newField_0, true);
    }

    public Map addValues(Map map)
    {
        super.addValues(map);
        map.put("com.example.AbstractBase.newField", newField_0);
        return map;
    }

    public void writeObject(ObjectOutput objectoutput)
        throws IOException
    {
        super.writeObject(objectoutput);
        objectoutput.writeObject(_userService_0);
    }

    public void readObject(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        super.readObject(objectinput);
        newField_0 = objectinput.readObject();
    }

    protected int getClassId()
    {
        return 790;
    }

    private ObjectID parentId;
    private Object newField_0;

    public State1_V790()
    {
    }
}
