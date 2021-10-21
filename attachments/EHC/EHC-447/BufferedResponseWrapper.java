/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesys.confmgr.web.filter.cache;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import net.sf.ehcache.constructs.web.GenericResponseWrapper;
import net.sf.ehcache.constructs.web.filter.FilterServletOutputStream;

/**
 *
 * This is a derived version of the GenericResponseWrapper of EHCache to
 * make it work with weblogic.
 *
 * @author HBourzeix
 */
public class BufferedResponseWrapper extends HttpServletResponseWrapper implements Serializable
{

    private static final long serialVersionUID = -5976708169031065498L;
    private static final Logger LOG = Logger.getLogger(GenericResponseWrapper.class.getName());
    private int statusCode = SC_OK;
    private int contentLength;
    private String contentType;
    private final List headers = new ArrayList();
    private final List cookies = new ArrayList();
    private ServletOutputStream outstr;
    private PrintWriter writer;

    /**
     * Creates a GenericResponseWrapper
     */
    public BufferedResponseWrapper(final HttpServletResponse response, final OutputStream outstr)
    {
        super(response);
        this.outstr = new FilterServletOutputStream(outstr);
    }

    /**
     * Gets the outputstream.
     */
    public ServletOutputStream getOutputStream()
    {
        return outstr;
    }

    /**
     * Sets the status code for this response.
     */
    public void setStatus(final int code)
    {
        statusCode = code;
    }

    /**
     * Send the error. If the response is not ok, most of the logic is bypassed and the error is sent raw
     * Also, the content is not cached.
     *
     * @param i      the status code
     * @param string the error message
     * @throws IOException
     */
    public void sendError(int i, String string) throws IOException
    {
        statusCode = i;
        super.sendError(i, string);
    }

    /**
     * Send the error. If the response is not ok, most of the logic is bypassed and the error is sent raw
     * Also, the content is not cached.
     *
     * @param i the status code
     * @throws IOException
     */
    public void sendError(int i) throws IOException
    {
        statusCode = i;
        super.sendError(i);
    }

    /**
     * Send the redirect. If the response is not ok, most of the logic is bypassed and the error is sent raw.
     * Also, the content is not cached.
     *
     * @param string the URL to redirect to
     * @throws IOException
     */
    public void sendRedirect(String string) throws IOException
    {
        statusCode = HttpServletResponse.SC_MOVED_TEMPORARILY;
        super.sendRedirect(string);
    }

    /**
     * Sets the status code for this response.
     */
    public void setStatus(final int code, final String msg)
    {
        statusCode = code;
        LOG.warning("Discarding message because this method is deprecated.");
    }

    /**
     * Returns the status code for this response.
     */
    public int getStatus()
    {
        return statusCode;
    }

    /**
     * Sets the content length.
     */
    public void setContentLength(final int length)
    {
        this.contentLength = length;
    }

    /**
     * Gets the content length.
     */
    public int getContentLength()
    {
        return contentLength;
    }

    /**
     * Sets the content type.
     */
    public void setContentType(final String type)
    {
        this.contentType = type;
    }

    /**
     * Gets the content type.
     */
    public String getContentType()
    {
        return contentType;
    }

    /**
     * Gets the print writer.
     */
    public PrintWriter getWriter() throws IOException
    {
        if (writer == null)
        {
            writer = new PrintWriter(new OutputStreamWriter(outstr, getCharacterEncoding()), true);
        }
        return writer;
    }

    /**
     * Adds a header.
     */
    public void addHeader(final String name, final String value)
    {
        final String[] header = new String[]
        {
            name, value
        };
        headers.add(header);
    }



    /**
     * @see #addHeader
     */
    public void setHeader(final String name, final String value)
    {
        addHeader(name, value);
    }

    /**
     * Gets the headers.
     */
    public Collection getHeaders()
    {
        return headers;
    }

    /**
     * Adds a cookie.
     */
    public void addCookie(final Cookie cookie)
    {
        cookies.add(cookie);
    }

    /**
     * Gets all the cookies.
     */
    public Collection getCookies()
    {
        return cookies;
    }

    /**
     * Flushes buffer and commits response to client.
     */
    public void flushBuffer() throws IOException
    {
        flush();
        //super.flushBuffer();
    }

    /**
     * Resets the response.
     */
    public void reset()
    {
        super.reset();
        cookies.clear();
        headers.clear();
        statusCode = SC_OK;
        contentType = null;
        contentLength = 0;
    }

    /**
     * Resets the buffers.
     */
    public void resetBuffer()
    {
        super.resetBuffer();
    }

    /**
     * Flushes all the streams for this response.
     */
    public void flush() throws IOException
    {
        if (writer != null)
        {
            writer.flush();
        }
        outstr.flush();
    }
}

