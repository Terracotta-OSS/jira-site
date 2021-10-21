import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.extension.CacheExtension;
import net.sf.ehcache.store.MemoryStore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A cache extension that creates an expiration thread, which allows reclaiming
 * memory used up by expired elements in an EhCache {@link MemoryStore}.
 * 
 */
public class ExpiryThreadCacheExtension implements CacheExtension {

	private final Log log = LogFactory.getLog(ExpiryThreadCacheExtension.class);

	// ----------------------------------------------------------------------
	// Constants
	// ----------------------------------------------------------------------

	private static final float DEFAULT_LOAD_FACTOR = .75f;

	private static final int CONCURRENCY_LEVEL = 100;

	private static final long TEN_MILLIS = 10L;

	// ----------------------------------------------------------------------
	// Fields
	// ----------------------------------------------------------------------

	private Ehcache cache;

	private CacheEventListener cacheEventListener;

	private Status status;

	private Timer timer;

	private Map<Object, TimerTask> tasks;

	// ----------------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------------

	/**
	 * Constructs a new extension for the given cache.
	 * 
	 * @param cache
	 *            the cache for which to construct the extension.
	 */
	public ExpiryThreadCacheExtension(Ehcache cache) {
		this.cache = cache;
		this.status = Status.STATUS_UNINITIALISED;
	}

	// ----------------------------------------------------------------------
	// CacheExtension implementations
	// ----------------------------------------------------------------------

	/**
	 * Initializes the extension.
	 */
	@Override
	public void init() {
		this.cacheEventListener = new ExpiryThreadCacheEventListener();
		this.cache.getCacheEventNotificationService().registerListener(
				cacheEventListener);
		this.timer = new Timer("Store " + cache.getName() + " Expiry Thread",
				true);
		this.tasks = new ConcurrentHashMap<Object, TimerTask>(cache
				.getCacheConfiguration().getMaxElementsInMemory(),
				DEFAULT_LOAD_FACTOR, CONCURRENCY_LEVEL);
		this.status = Status.STATUS_ALIVE;
	}

	/**
	 * Shuts down the extension.
	 */
	@Override
	public void dispose() throws CacheException {
		this.status = Status.STATUS_SHUTDOWN;
		this.cache.getCacheEventNotificationService().unregisterListener(
				cacheEventListener);
		this.timer.cancel();
		this.timer.purge();
		this.tasks.clear();
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status.
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/**
	 * Gets a cloned cache extension.
	 * 
	 * @param cache
	 *            a cache.
	 * @return a cloned cache extension.
	 */
	@Override
	public CacheExtension clone(Ehcache cache)
			throws CloneNotSupportedException {

		return new ExpiryThreadCacheExtension(cache);
	}

	// ----------------------------------------------------------------------
	// Private helper methods
	// ----------------------------------------------------------------------

	private boolean isAlive() {
		return cache.getStatus().equals(Status.STATUS_ALIVE);
	}

	private boolean hasLifespan(Element element) {
		return !element.isEternal() && element.isLifespanSet()
				&& element.getExpirationTime() < Long.MAX_VALUE;
	}

	private void scheduleTask(Element element) {

		if (!isAlive()) {
			return;
		}

		if (!hasLifespan(element)) {
			return;
		}

		// Only schedule tasks to clean up the MemoryStore
		if (!cache.isElementInMemory(element.getObjectKey())) {
			return;
		}

		TimerTask task = new ExpiryTask(element.getObjectKey());
		tasks.put(element.getObjectKey(), task);
		Date trigger = new Date(element.getExpirationTime() + TEN_MILLIS);
		timer.schedule(task, trigger);

		if (log.isDebugEnabled()) {
			log.debug("Scheduled task for cache " + cache.getName() + ", key "
					+ element.getObjectKey() + ", expiration "
					+ element.getExpirationTime());
		}
	}

	private void cancelTask(Element element) {

		if (!isAlive()) {
			return;
		}

		TimerTask task = tasks.remove(element.getObjectKey());
		if (task != null && task.scheduledExecutionTime() > 0) {
			task.cancel();
			if (log.isDebugEnabled()) {
				log.debug("Cancelled task for cache " + cache.getName()
						+ ", key " + element.getObjectKey());
			}
		}
	}

	private void cancelAllTasks() {

		if (!isAlive()) {
			return;
		}

		for (TimerTask task : tasks.values()) {
			task.cancel();
		}

		tasks.clear();
		if (log.isDebugEnabled()) {
			log.debug("Cancelled all tasks for cache " + cache.getName());
		}
	}

	// ----------------------------------------------------------------------
	// CacheEventListener inner class
	// ----------------------------------------------------------------------

	private class ExpiryThreadCacheEventListener implements CacheEventListener {

		@Override
		public void notifyElementPut(Ehcache cache, Element element)
				throws CacheException {

			scheduleTask(element);
		}

		@Override
		public void notifyElementRemoved(Ehcache cache, Element element)
				throws CacheException {

			cancelTask(element);
		}

		@Override
		public void notifyElementUpdated(Ehcache cache, Element element)
				throws CacheException {

			cancelTask(element);
			scheduleTask(element);
		}

		@Override
		public void notifyRemoveAll(Ehcache cache) {
			cancelAllTasks();
		}

		@Override
		public void notifyElementExpired(Ehcache cache, Element element) {
			cancelTask(element);
		}

		@Override
		public void notifyElementEvicted(Ehcache cache, Element element) {
			cancelTask(element);
		}

		@Override
		public void dispose() {
			return;
		}

		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}

	// ----------------------------------------------------------------------
	// Task inner class
	// ----------------------------------------------------------------------

	private class ExpiryTask extends TimerTask {

		// ----------------------------------------------------------------------
		// Fields
		// ----------------------------------------------------------------------

		private final Object key;

		// ----------------------------------------------------------------------
		// Constructor
		// ----------------------------------------------------------------------

		private ExpiryTask(Object key) {
			this.key = key;
		}

		// ----------------------------------------------------------------------
		// Public methods
		// ----------------------------------------------------------------------

		@Override
		public void run() {

			if (!isAlive()) {
				return;
			}

			Element element = null;
			try {
				element = cache.getQuiet(key);
			} catch (Exception e) {
				log.error("Error while obtaining value for key " + key);
				return;
			}

			if (element != null) {

				boolean rescheduleTask = false;
				try {
					boolean withinTenMillis = (Math.abs(element
							.getExpirationTime() - System.currentTimeMillis()) < TEN_MILLIS);

					if (element.isExpired() || withinTenMillis) {

						// Preserve statistics
						cache.removeQuiet(key);

						// Notify expiration to listeners
						cache.getCacheEventNotificationService()
								.notifyElementExpiry(element, false);

						if (log.isDebugEnabled()) {
							log.debug("Expired entry for cache "
									+ cache.getName() + ", key " + key);
						}

					} else {

						// Expiration probably changed due to TTI
						rescheduleTask = true;
					}

				} catch (Exception e) {

					if (log.isErrorEnabled()) {
						log.error("Error while trying to remove "
								+ "expired entry for cache " + cache.getName()
								+ ", key " + key, e);
					}

				} finally {

					tasks.remove(key);
					if (rescheduleTask) {
						scheduleTask(element);
					}
				}
			}
		}
	}
}
