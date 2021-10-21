/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesys.confmgr.web.filter.cache;

import com.genesys.confmgr.web.filter.cache.BufferedResponseWrapper;
import java.io.ByteArrayOutputStream;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.PageInfo;
import net.sf.ehcache.constructs.web.filter.SimpleCachingHeadersPageCachingFilter;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilterWithBlankPageProblem;

/**
 *
 * @author HBourzeix
 */
public class PreventResponseAlreadyCommittedCacheFilter extends SimpleCachingHeadersPageCachingFilter
{

    @Override
    protected PageInfo buildPage(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws AlreadyGzippedException, Exception
    {
        // Invoke the next entity in the chain

        final ByteArrayOutputStream outstr = new ByteArrayOutputStream();
         System.out.println("build page");
        final BufferedResponseWrapper wrapper = new BufferedResponseWrapper(response, outstr);
        chain.doFilter(request, wrapper);
        wrapper.flush();

        long timeToLiveSeconds = blockingCache.getCacheConfiguration().getTimeToLiveSeconds();

        // Return the page info
        return new PageInfo(wrapper.getStatus(), wrapper.getContentType(), wrapper.getHeaders(), wrapper.getCookies(),
                outstr.toByteArray(), true, timeToLiveSeconds);
    }
}
