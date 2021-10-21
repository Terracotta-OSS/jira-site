package net.sf.ehcache.constructs.web;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GenericResponseWrapperTest {

    private GenericResponseWrapper impl;
    @Mock private HttpServletResponse mockResponse;
    @Mock private OutputStream mockOutputStream;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = new GenericResponseWrapper(mockResponse, mockOutputStream);
    }
    
    @Test
    public void verify_set_header_overwrites_existing_header_values() {
        impl.addHeader("Cache-Control", "public, max-age=0, stale-if-error=600");
        impl.addHeader("CACHE-CONTROL", "public, max-age=120, stale-if-error=600");
        int headerSize = impl.getHeaders().size();
        assertTrue(String.format("Expected size for headers is two but got %d", headerSize), headerSize == 2);
        
        String cacheHeader = "public, max-age=120, stale-if-error=300";
        
        impl.setHeader("cache-control", cacheHeader);
        
        headerSize = impl.getHeaders().size();
        assertTrue(String.format("Expected size for headers is 1 but got %d", headerSize), headerSize == 1);
        
        String[] retrievedHeader = (String[]) impl.getHeaders().iterator().next();
        
        assertEquals(cacheHeader, retrievedHeader[1]);
    }
    
}
