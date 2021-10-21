/* Test - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.tc.object.TCObject;
import com.tc.object.bytecode.Manageable;
import com.tc.object.bytecode.ManagerUtil;
import com.tc.object.bytecode.TransparentAccess;

public class Test implements Manageable, TransparentAccess
{
    private static MyList root;
    static final long serialVersionUID = 9023406538786989891L;
    private transient volatile TCObject $__tc_MANAGED;
    
    private static class MyList extends ArrayList
	implements Manageable, TransparentAccess
    {
	private transient ArrayList __delegate_tc_java_util_ArrayList;
	private int field = 3;
	static final long serialVersionUID = 8840781142054752096L;
	private transient volatile TCObject $__tc_MANAGED;
	
	private ArrayList __tc_get__delegate_tc_java_util_ArrayList() {
	    TCObject tcobject;
	    if ((tcobject = __tc_managed()) != null) {
		Object object;
		MONITORENTER (object = tcobject.getResolveLock());
		MISSING MONITORENTER
		synchronized (object) {
		    if (__delegate_tc_java_util_ArrayList == null)
			tcobject.resolveReference
			    ("Test$MyList.__delegate_tc_java_util_ArrayList");
		    ArrayList arraylist = __delegate_tc_java_util_ArrayList;
		    return arraylist;
		}
	    }
	    return __delegate_tc_java_util_ArrayList;
	}
	
	private void __tc_set__delegate_tc_java_util_ArrayList
	    (ArrayList arraylist) {
	    TCObject tcobject;
	    if ((tcobject = __tc_managed()) != null)
		tcobject.objectFieldChanged
		    ("Test$MyList",
		     "Test$MyList.__delegate_tc_java_util_ArrayList",
		     arraylist, -1);
	    __delegate_tc_java_util_ArrayList = arraylist;
	}
	
	private int __tc_getfield() {
	    return field;
	}
	
	private void __tc_setfield(int i) {
	    TCObject tcobject;
	    if ((tcobject = __tc_managed()) != null)
		tcobject.intFieldChanged("Test$MyList", "Test$MyList.field", i,
					 -1);
	    field = i;
	}
	
	private MyList() {
	    __tc_set__delegate_tc_java_util_ArrayList(new ArrayList());
	    __tc_setfield(3);
	}
	
	public int getField() {
	    return 3;
	}
	
	MyList(Test$1 x0) {
	    this();
	}
	
	public void add(int i, Object object) {
	    __tc_get__delegate_tc_java_util_ArrayList().add(i, object);
	}
	
	public int lastIndexOf(Object object) {
	    return __tc_get__delegate_tc_java_util_ArrayList()
		       .lastIndexOf(object);
	}
	
	public ListIterator listIterator(int i) {
	    return __tc_get__delegate_tc_java_util_ArrayList().listIterator(i);
	}
	
	public int size() {
	    return __tc_get__delegate_tc_java_util_ArrayList().size();
	}
	
	public ListIterator listIterator() {
	    return __tc_get__delegate_tc_java_util_ArrayList().listIterator();
	}
	
	public boolean removeAll(Collection collection) {
	    return __tc_get__delegate_tc_java_util_ArrayList()
		       .removeAll(collection);
	}
	
	public Object[] toArray(Object[] objects) {
	    return __tc_get__delegate_tc_java_util_ArrayList()
		       .toArray(objects);
	}
	
	public int indexOf(Object object) {
	    return __tc_get__delegate_tc_java_util_ArrayList().indexOf(object);
	}
	
	public Object remove(int i) {
	    return __tc_get__delegate_tc_java_util_ArrayList().remove(i);
	}
	
	public Object get(int i) {
	    return __tc_get__delegate_tc_java_util_ArrayList().get(i);
	}
	
	public boolean addAll(Collection collection) {
	    return __tc_get__delegate_tc_java_util_ArrayList()
		       .addAll(collection);
	}
	
	public boolean containsAll(Collection collection) {
	    return __tc_get__delegate_tc_java_util_ArrayList()
		       .containsAll(collection);
	}
	
	public boolean remove(Object object) {
	    return __tc_get__delegate_tc_java_util_ArrayList().remove(object);
	}
	
	public List subList(int i, int i_0_) {
	    return __tc_get__delegate_tc_java_util_ArrayList().subList(i,
								       i_0_);
	}
	
	public Object clone() {
	    return __tc_get__delegate_tc_java_util_ArrayList().clone();
	}
	
	public Object[] toArray() {
	    return __tc_get__delegate_tc_java_util_ArrayList().toArray();
	}
	
	public void clear() {
	    __tc_get__delegate_tc_java_util_ArrayList().clear();
	}
	
	public void trimToSize() {
	    __tc_get__delegate_tc_java_util_ArrayList().trimToSize();
	}
	
	public boolean addAll(int i, Collection collection) {
	    return __tc_get__delegate_tc_java_util_ArrayList()
		       .addAll(i, collection);
	}
	
	public boolean isEmpty() {
	    return __tc_get__delegate_tc_java_util_ArrayList().isEmpty();
	}
	
	public Iterator iterator() {
	    return __tc_get__delegate_tc_java_util_ArrayList().iterator();
	}
	
	public String toString() {
	    return __tc_get__delegate_tc_java_util_ArrayList().toString();
	}
	
	public int hashCode() {
	    return __tc_get__delegate_tc_java_util_ArrayList().hashCode();
	}
	
	public boolean contains(Object object) {
	    return __tc_get__delegate_tc_java_util_ArrayList()
		       .contains(object);
	}
	
	public Object set(int i, Object object) {
	    return __tc_get__delegate_tc_java_util_ArrayList().set(i, object);
	}
	
	public void ensureCapacity(int i) {
	    __tc_get__delegate_tc_java_util_ArrayList().ensureCapacity(i);
	}
	
	public boolean add(Object object) {
	    return __tc_get__delegate_tc_java_util_ArrayList().add(object);
	}
	
	public boolean equals(Object object) {
	    return __tc_get__delegate_tc_java_util_ArrayList().equals(object);
	}
	
	public boolean retainAll(Collection collection) {
	    return __tc_get__delegate_tc_java_util_ArrayList()
		       .retainAll(collection);
	}
	
	private void readObject(ObjectInputStream objectinputstream)
	    throws IOException, ClassNotFoundException {
	    __tc_set__delegate_tc_java_util_ArrayList((ArrayList)
						      objectinputstream
							  .readObject());
	}
	
	private void writeObject(ObjectOutputStream objectoutputstream)
	    throws IOException {
	    objectoutputstream
		.writeObject(__tc_get__delegate_tc_java_util_ArrayList());
	}
	
	protected boolean __tc_isSerializationOverride() {
	    return true;
	}
	
	public void __tc_getallfields(Map map) {
	    map.put("Test$MyList.__delegate_tc_java_util_ArrayList",
		    __delegate_tc_java_util_ArrayList);
	    map.put("Test$MyList.field", new Integer(field));
	}
	
	public void __tc_setfield(String string, Object object) {
	    if (string.equals("Test$MyList.__delegate_tc_java_util_ArrayList"))
		__delegate_tc_java_util_ArrayList = (ArrayList) object;
	    else if (string.equals("Test$MyList.field"))
		field = ((Integer) object).intValue();
	}
	
	public Object __tc_getmanagedfield(String string) {
	    if (string.equals("Test$MyList.__delegate_tc_java_util_ArrayList"))
		return __tc_get__delegate_tc_java_util_ArrayList();
	    if (string.equals("Test$MyList.field"))
		return new Integer(field);
	    return null;
	}
	
	public void __tc_setmanagedfield(String string, Object object) {
	    if (string.equals("Test$MyList.__delegate_tc_java_util_ArrayList"))
		__tc_set__delegate_tc_java_util_ArrayList((ArrayList) object);
	    else if (string.equals("Test$MyList.field"))
		__tc_setfield(((Integer) object).intValue());
	}
	
	public TCObject __tc_managed() {
	    return $__tc_MANAGED;
	}
	
	public void __tc_managed(TCObject tcobject) {
	    $__tc_MANAGED = tcobject;
	}
	
	public boolean __tc_isManaged() {
	    if ($__tc_MANAGED != null)
		return true;
	    return false;
	}
    }
    
    private static MyList __tc_getroot() {
	if (root == null) {
	    ManagerUtil.beginLock("Test.root", 2);
	    try {
		Object object = ManagerUtil.lookupRoot("Test.root");
		if (object != null) {
		    if (!(object instanceof MyList))
			throw new ClassCastException
				  ("The field '" + "root"
				   + "' with root name '" + "Test.root"
				   + "' cannot be assigned to a variable of type "
				   + "Test$MyList" + ". This root has a type "
				   + object.getClass().getName() + ". "
				   + "Perhaps you have the same root name assigned more than once to variables of different types.");
		    root = (MyList) object;
		}
	    } finally {
		ManagerUtil.commitLock("Test.root");
	    }
	}
	return root;
    }
    
    private static void __tc_setroot(MyList mylist) {
	if (mylist != null) {
	    ManagerUtil.beginLock("Test.root", 2);
	    try {
		Object object
		    = ManagerUtil.lookupOrCreateRoot("Test.root", mylist);
		if (!(object instanceof MyList))
		    throw new ClassCastException
			      ("The field '" + "root" + "' with root name '"
			       + "Test.root"
			       + "' cannot be assigned to a variable of type "
			       + "Test$MyList" + ". This root has a type "
			       + object.getClass().getName() + ". "
			       + "Perhaps you have the same root name assigned more than once to variables of different types.");
		root = (MyList) object;
	    } finally {
		ManagerUtil.commitLock("Test.root");
	    }
	}
    }
    
    public static void main(String[] args) {
	Object clone = __tc_getroot().clone();
	if (!(clone instanceof MyList))
	    throw new AssertionError((Object) new StringBuilder().append
						  ("wrong type: ").append
						  (clone.getClass().getName())
						  .toString());
	System.out.println(clone.getClass().getName());
    }
    
    static {
	__tc_setroot(new MyList((Test$1) null));
    }
    
    public void __tc_getallfields(Map map) {
	/* empty */
    }
    
    public void __tc_setfield(String string, Object object) {
	/* empty */
    }
    
    public Object __tc_getmanagedfield(String string) {
	return null;
    }
    
    public void __tc_setmanagedfield(String string, Object object) {
	/* empty */
    }
    
    public TCObject __tc_managed() {
	return $__tc_MANAGED;
    }
    
    public void __tc_managed(TCObject tcobject) {
	$__tc_MANAGED = tcobject;
    }
    
    public boolean __tc_isManaged() {
	if ($__tc_MANAGED != null)
	    return true;
	return false;
    }
}
