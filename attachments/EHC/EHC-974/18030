package tests.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.*;
import net.sf.ehcache.config.*;
import net.sf.ehcache.event.CacheEventListenerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class CacheWithDiskStoreTest
{
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog( CacheWithDiskStoreTest.class.getName() );

	private CacheManager manager = null;
	private Cache cache;

	// Number of elements to put in cache
	private int nsteps = 0;

	private int numEvicted = 0;

	private String createKey( int index ) { return "key " + index; }
	private String createValue( int index ) { return "value " + index; }

	private void registerEvictionListener()
	{
		cache.getCacheEventNotificationService().registerListener( new CacheEventListenerAdapter()
				{
					@Override
					public void notifyElementEvicted( Ehcache cache, Element element )
					{
						++numEvicted;
						logger.debug( "notifyElementEvicted " + element.getKey() );
					}
				} );
	}

	@Before
	public void setUp()
	{
		nsteps = 10000;
		numEvicted = 0;

		String diskStorePath = System.getProperty( "java.io.tmpdir" );
		manager = CacheManager.create(
				new Configuration().diskStore( new DiskStoreConfiguration().path( diskStorePath ) ) );

		CacheConfiguration cfg = new CacheConfiguration( "testCache1", 0 )
				.maxBytesLocalHeap( 10, MemoryUnit.KILOBYTES )
				.persistence( new PersistenceConfiguration().strategy( PersistenceConfiguration.Strategy.LOCALTEMPSWAP ) )
				.eternal( true );

		cache = new Cache( cfg );
		manager.addCache( cache );

		registerEvictionListener();
	}

	@Test
	public void maxBytesLocalDiskShouldBeUnlimited()
	{
		assertThat( 0L, equalTo( cache.getCacheConfiguration().getMaxBytesLocalDisk() ) );
	}

	@Test
	public void noElementsShouldBeEvicted() throws Exception
	{
		loadElementsToCache();
		assertThat( "Non-zero number of evicted elements: " + numEvicted, 0, equalTo( numEvicted ) );
	}

	@Test
	public void testCachedElementsCanBeRetrieved() throws Exception
	{
		loadElementsToCache();

		for ( int i = 0; i < nsteps; ++i )
		{
			String key = createKey( i );
			Element element = cache.get( key );
			assertThat( "Failed retreiving value for key " + key, element, is( notNullValue() ) );
			
			String value = ( String ) element.getValue();
			String expected = createValue( i );

			assertThat( expected, equalTo( value ) );
		}
	}

	private void loadElementsToCache() throws Exception
	{
		for ( int i = 0; i < nsteps; ++i )
		{
			cache.put( new Element( createKey( i ), createValue( i ) ) ); 
		}
	}

	@After
	public void tearDown() throws Exception
	{
		if ( null != manager )
		{
			manager.shutdown();
		}
	}
}

