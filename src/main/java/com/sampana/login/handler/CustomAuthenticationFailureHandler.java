package com.sampana.login.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * This class would be handle login authenticaton fail. Strategy used to handle
 * a failed authentication attempt. Typical behaviour might be to redirect the
 * user to the authentication page (in the case of a form login) to allow them
 * to try again. More sophisticated logic might be implemented depending on the
 * type of the exception. For example, a CredentialsExpiredException might cause
 * a redirect to a web controller which allowed the user to change their pa
 * 
 * @author Sudhanshu
 *
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

	@Autowired
	private Environment env;

	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("***********onAuthenticationFailure*************");
		StringBuilder targetUrl = new StringBuilder();
		String error = "Invalid usename and password.";
		targetUrl.append(env.getProperty("client.app.context") + "login" + "?error=" + error);
		redirectStrategy.sendRedirect(request, response, targetUrl.toString());

	}

}
