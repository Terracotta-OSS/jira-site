import java.util.*;
import com.tc.cluster.*;
import com.tcclient.cluster.*;

public class OnLoadTest
{
  private final DsoCluster dsoCluster = null;

  private final static OnLoadTest onLoadTest;

  static
  {
    OnLoadTest newOnLoadTest = new OnLoadTest();
    onLoadTest = newOnLoadTest;
    if (newOnLoadTest == onLoadTest)
    {
      onLoadTest.init();
    }
  }

  private Set<DsoNode> nodes = new HashSet<DsoNode>();

  private OnLoadTest()
  {
  }

  public synchronized void init()
  {
    System.out.println("init");
    nodes.add(dsoCluster.getCurrentNode());
  }

  private synchronized void dump()
  {
    System.out.println("nodes = " + nodes);
  }

  public static void main(String... args) throws Exception
  {
    //onLoadTest.init();
    while (true)
    {
      onLoadTest.dump();
      Thread.sleep(1000);
    }
  }
}
