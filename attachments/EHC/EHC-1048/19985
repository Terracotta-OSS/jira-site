package com.renxo.cms.cache;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;

import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

/**
 * Extends {@link EhCacheManagerFactoryBean} in order to allow configuration of
 * the number of statistics threads used by the created cache managers.
 * 
 */
public class CustomEhCacheManagerFactoryBean extends EhCacheManagerFactoryBean {

	// ----------------------------------------------------------------------
	// Fields
	// ----------------------------------------------------------------------

	private int statisticsThreads = 1;

	// ----------------------------------------------------------------------
	// Setters for IoC
	// ----------------------------------------------------------------------

	/**
	 * Configures the number of statistics threads. Default value is one.
	 * 
	 * @param statisticsThreads
	 *            the number of threads to set.
	 */
	public void setStatisticsThreads(int statisticsThreads) {
		this.statisticsThreads = statisticsThreads;
	}

	// ----------------------------------------------------------------------
	// CacheEventListener implementations
	// ----------------------------------------------------------------------

	/**
	 * Initializes the cache manager.
	 * 
	 * @throws IOException
	 *             on I/O errors.
	 * @throws CacheException
	 *             on any other error.
	 */
	@Override
	public void afterPropertiesSet() throws IOException, CacheException {

		super.afterPropertiesSet();
		CacheManager cacheManager = getObject();

		try {

			Field field = CacheManager.class
					.getDeclaredField("statisticsExecutor");
			field.setAccessible(true);

			ScheduledExecutorService previousStatisticsExecutor = (ScheduledExecutorService) field
					.get(cacheManager);
			previousStatisticsExecutor.shutdownNow();

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField
					.setInt(field, field.getModifiers() & ~Modifier.FINAL);

			ScheduledExecutorService newStatisticsExecutor = Executors
					.newScheduledThreadPool(statisticsThreads,
							new ThreadFactory() {
								@Override
								public Thread newThread(Runnable r) {
									Thread t = new Thread(r,
											"Statistics Thread");
									t.setDaemon(true);
									return t;
								}
							});

			field.set(cacheManager, newStatisticsExecutor);
			modifiersField.setInt(field, field.getModifiers() | Modifier.FINAL);

		} catch (Exception e) {
			throw new CacheException(e);
		}
	}
}
