import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.TransactionController;
import net.sf.ehcache.config.CacheConfiguration;

public class TestCacheThreadSafety {

	private static final String CACHE_MANAGER_NAME = "TestThreadSafetyManager";

	private static final String CACHE_NAME = "TestThreadSafetyCache";

	private static final String KEY = "MyKey";

	private ExecutorService executorService;

	private TxCache txCache;

	@Before
	public void setUp() {
		executorService = Executors.newFixedThreadPool(2);
		txCache = TxCacheBuilder.build();
	}

	@After
	public void tearDown() {
		executorService.shutdown();
	}

	/**
	 * This tests demonstrates failed contract of the {@link Cache#replace(Element, Element)} method.
	 * If replace returns true, that means that the value was replaced, but as it turns out its not always the case.
	 */
	@Test
	public void shouldNotModifyEntryConcurrently() throws Exception {
		int errorCount = 0;
		for (int i = 0; i < 100; ++i) {
			if (runConcurrentlyTwoTasksAddingOne()) {
				errorCount += 1;
			}
		}
		Assert.assertEquals(errorCount, 0);
	}

	private boolean runConcurrentlyTwoTasksAddingOne() throws Exception {
		// initialize cache
		txCache.put(KEY, new CacheValue(0));

		// create two tasks which will try to increase the value inside CacheValue by 1
		final List<Callable<Boolean>> tasks = Arrays.asList(
				() -> txCache.increaseByOne(KEY),
				() -> txCache.increaseByOne(KEY));

		// run tasks concurrently
		final List<Future<Boolean>> results = executorService.invokeAll(tasks);

		// gather results of cache.replace(...) call
		final List<Boolean> statuses = Arrays.asList(results.get(0).get(), results.get(1).get());

		/*
		Possible outcomes:
		1. CacheValue contains number 1 - only one of the threads succeeded
		 a. one of the 'cache.replace' method calls returns false
		 b. both 'cache.replace' methods calls return true

		2. CacheValue contains number 2 - both thread succeeded
		 a. both 'cache.replace' method calls return true
		*/

		// return result (if it is situation 1.b., that means error)
		return txCache.get(KEY).getCurrentNumber() == 1 && !statuses.contains(Boolean.FALSE);
	}


	static class CacheValue {
		private final int currentNumber;

		private CacheValue(final int currentNumber) {
			this.currentNumber = currentNumber;
		}

		static CacheValue increaseByOne(final CacheValue value) {
			return new CacheValue(value.currentNumber + 1);
		}

		int getCurrentNumber() {
			return currentNumber;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			CacheValue that = (CacheValue) o;
			return currentNumber == that.currentNumber;
		}

		@Override
		public int hashCode() {
			return currentNumber;
		}
	}

	static class TxCache {
		private final CacheManager cacheManager;
		private final Cache cache;

		TxCache(final CacheManager cacheManager, final Cache cache) {
			this.cacheManager = cacheManager;
			this.cache = cache;
		}

		void put(final String key, final CacheValue value) {
			try {
				txRequired(() -> {
					cache.put(new Element(key, value));
					return null;
				});
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		CacheValue get(final String key) {
			try {
				return txRequired(() -> {
					Element result = cache.get(key);
					return (CacheValue) result.getObjectValue();
				});
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		boolean increaseByOne(final String key) {
			try {
				return txRequired(() -> {
					Element oldElement = cache.get(key);
					Element newElement = new Element(oldElement.getObjectKey(), CacheValue.increaseByOne((CacheValue) oldElement.getObjectValue()));
					return cache.replace(oldElement, newElement);
				});
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		private <R> R txRequired(final Callable<R> task) throws Exception {
			final TransactionController txManager = cacheManager.getTransactionController();
			try {
				txManager.begin();
				return task.call();
			} finally {
				txManager.commit();
			}
		}
	}

	static class TxCacheBuilder {

		static TxCache build() {
			final CacheConfiguration configuration = buildConfiguration();
			final CacheManager cacheManager = buildCacheManager();
			final Cache cache = new Cache(configuration);
			cacheManager.addCache(cache);
			return new TxCache(cacheManager, cache);
		}

		static CacheManager buildCacheManager() {
			final CacheManager cacheManager = new CacheManager();
			cacheManager.setName(CACHE_MANAGER_NAME);
			return cacheManager;
		}

		static CacheConfiguration buildConfiguration() {
			final CacheConfiguration configuration = new CacheConfiguration();
			configuration.setTransactionalMode(CacheConfiguration.TransactionalMode.LOCAL.name());
			configuration.setCopyOnRead(true);
			configuration.setCopyOnWrite(true);
			configuration.setOverflowToDisk(false);
			configuration.setEternal(true);
			configuration.setMaxElementsInMemory(0);
			configuration.setName(CACHE_NAME);
			configuration.getCopyStrategyConfiguration().setClass("net.sf.ehcache.store.compound.ImmutableValueElementCopyStrategy");
			configuration.validateConfiguration();
			return configuration;
		}
	}

}
