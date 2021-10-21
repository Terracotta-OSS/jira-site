package ro.objects.quartz.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class TerracottaQuartzBug {

	public static class HelloWorld implements Job {

		public void execute(JobExecutionContext ctx)
				throws JobExecutionException {
			System.out.println("Hello World");
		}
	}

	public static void main(String[] args) throws Throwable {
		System.setProperty("org.quartz.threadPool.threadCount", "100");
		System.setProperty("org.quartz.scheduler.instanceId", "AUTO");
		System.setProperty("org.quartz.jobStore.class",
				"org.terracotta.quartz.TerracottaJobStore");
		System.setProperty("org.quartz.jobStore.tcConfigUrl", "localhost:9510");
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.unscheduleJob("test", "test");
		scheduler.deleteJob("test", "test");
		Calendar start = GregorianCalendar.getInstance();
		start.add(Calendar.SECOND, 30);
		JobDetail jd = new JobDetail("test", "test", HelloWorld.class);
		Trigger t = new SimpleTrigger(jd.getName(), jd.getGroup(), start
				.getTime());
		scheduler.scheduleJob(jd, t);
		scheduler.start();
	}
}
