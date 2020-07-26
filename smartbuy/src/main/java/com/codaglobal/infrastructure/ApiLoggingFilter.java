package com.codaglobal.infrastructure;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Asif Jalaludeen
 *
 */

@Component
@WebFilter("/*")
public class ApiLoggingFilter implements Filter
{
  private static final Logger log = LoggerFactory.getLogger( ApiLoggingFilter.class );

  public ApiLoggingFilter()
  {
  }

  @Override
  public void init( FilterConfig filterConfig ) throws ServletException
  {
  }

  @Override
  public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
      throws IOException, ServletException
  {
    long startTime = System.currentTimeMillis();
    try
    {
      chain.doFilter( request, response );
    }
    finally
    {
      HttpServletResponse res = ( (HttpServletResponse) response );
      HttpServletRequest req = ( (HttpServletRequest) request );
      log.info( "Api URL: {} Method: {} Spend: {} ms, code: {}", req.getRequestURI(), req.getMethod(),
          ( System.currentTimeMillis() - startTime ), res.getStatus() );
    }
  }

  @Override
  public void destroy()
  {

  }

}
