
/**
All content copyright (c) 2003-2007 Terracotta, Inc.,
except as may otherwise be noted in a separate copyright notice.
All rights reserved.
 */
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static final Main instance = new Main();
	private Map<String,Routed> map = new HashMap<String,Routed>();

	public void init() {

		synchronized(map) {

			if (map.isEmpty()) {
				map.put("test1",new Routed());
				map.put("test2",new Routed());
				map.put("test3",new Routed());
				map.put("test4",new Routed());
			} else {

				System.out.println("map [" + map + "]");

			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		instance.init();
	}

	public class Routed {

		private Queue<Date> queue = new PriorityQueue<Date>();

		public Routed() {

			queue.add(new Date());
			queue.add(new Date());
			queue.add(new Date());
			queue.add(new Date());
		}

		@Override
		public String toString() {
                        System.out.println(queue.peek());
			return "queue [" + queue + "]";
		}


	}
}

