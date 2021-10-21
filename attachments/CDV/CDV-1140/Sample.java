
import org.terracotta.modules.concurrent.collections.ConcurrentStringMap;

public class Sample {

	private ConcurrentStringMap <String> map;
	
	Sample(){
		this.map = new ConcurrentStringMap<String>();
	}

	public void run(){
			System.out.println("Size of the map :"+this.map.size());
	}
	
	
	public static void main(String []args)
	{
		Sample foo=new Sample();
		foo.run();
	}
}

