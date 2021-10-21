import java.util.concurrent.CyclicBarrier;
import java.util.ArrayList;

public class ThreadInterruptTest
{
	public static void main( String[] args ) throws Exception
	{
		System.out.println( "Waiting for other process..." );
		int processId = barrier.await();

		ArrayList<Thread> threads = new ArrayList<Thread>(1);
		threads.add( new Thread( new Runnable()
			{
				public void run()
				{
					try
					{
						sharedObject.doWait();
					}
					catch( Exception e )
					{
						e.printStackTrace();
					}
				}
			}
		));

		System.out.println( "Starting thread..." );
		for( Thread t : threads )
			t.start();

		if( processId == 0 )
			Thread.sleep( 5000 );
		else
			Thread.sleep( 10000 );

		System.out.println( "Interrupting thread..." );
		for( Thread t : threads )
			t.interrupt();
		
		System.out.println( "Done!" );
	}

	private static final CyclicBarrier barrier = new CyclicBarrier(2);
	private static final MySharedObject sharedObject = new MySharedObject();
}
