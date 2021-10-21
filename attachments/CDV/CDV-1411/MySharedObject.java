public class MySharedObject
{
	public synchronized void doWait() throws InterruptedException
	{
		wait();
	}
}
