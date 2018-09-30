/**
 * 
 */
package com.sampana.login.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *  this is using for handling control or access for url. 
 * 
 * @author Sudhanshu
 */
public class OvoAccessDeniedHandler implements AccessDeniedHandler 
{

  /** The access denied url. */
  private String accessDeniedUrl;

  /**
   * Instantiates a new veridian access denied handler.
   */
  public OvoAccessDeniedHandler()
  {}

  /**
   * Instantiates a new veridian access denied handler.
   * 
   * @param accessDeniedUrl the access denied url
   */
  public OvoAccessDeniedHandler(String accessDeniedUrl)
  {
    this.accessDeniedUrl = accessDeniedUrl;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.web.access.AccessDeniedHandler#handle(javax.servlet.http.HttpServletRequest,
   * javax.servlet.http.HttpServletResponse, org.springframework.security.access.AccessDeniedException)
   */
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
      throws IOException, ServletException
  {
    response.sendRedirect(request.getContextPath()+accessDeniedUrl);
    request.getSession().setAttribute("message","You do not have permission to access this page!");
  }

  /**
   * Gets the access denied url.
   * 
   * @return the access denied url
   */
  public String getAccessDeniedUrl()
  {
    return accessDeniedUrl;
  }

  /**
   * Sets the access denied url.
   * 
   * @param accessDeniedUrl the new access denied url
   */
  public void setAccessDeniedUrl(String accessDeniedUrl)
  {
    this.accessDeniedUrl = accessDeniedUrl;
  }
}
