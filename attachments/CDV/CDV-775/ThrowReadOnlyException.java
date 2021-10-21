import java.util.*;
import java.util.concurrent.locks.*;
import org.terracotta.modules.annotations.*;
 
@InstrumentedClass
public class ThrowReadOnlyException
{
  public static void main(String... args)
  {
    (new ThrowReadOnlyException()).test();
  }

  @Root
  private List<Object> list = new ArrayList<Object>(1);

  @Root
  private ReadWriteLock lock = new ReentrantReadWriteLock();

  public void test()
  {
    lock.writeLock().lock();
    lock.readLock().lock();

    list.add(new Object());

    lock.readLock().unlock();
    lock.writeLock().unlock();
  }
}
