/**
 * 
 */
package com.sampana.login.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.Assert;

/**
 * This filter should be using for Filtering user at the time of login.
 * 
 * @author Sudhanshu
 */
public class UserAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationFilter.class);

	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

	private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;


	public UserAuthenticationFilter() {
		super("/spring_security_check");
	}

	protected UserAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	/**
	 * Sets the parameter name which will be used to obtain the username from the
	 * login request.
	 *
	 * @param usernameParameter
	 *            the parameter name. Defaults to "j_username".
	 */
	public void setUsernameParameter(String usernameParameter) {
		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
		this.usernameParameter = usernameParameter;
	}

	/**
	 * Sets the parameter name which will be used to obtain the password from the
	 * login request..
	 *
	 * @param passwordParameter
	 *            the parameter name. Defaults to "j_password".
	 */
	public void setPasswordParameter(String passwordParameter) {
		Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
		this.passwordParameter = passwordParameter;
	}

	/**
	 * Gets usernameParameter.
	 *
	 * @return usernameParameter String
	 */
	public final String getUsernameParameter() {
		return usernameParameter;
	}

	/**
	 * Gets passwordParameter.
	 *
	 * @return passwordParameter String
	 */
	public final String getPasswordParameter() {
		return passwordParameter;
	}

	/**
	 * Performs actual authentication. The implementation should do one of the
	 * following: - Return a populated authentication token for the authenticated
	 * user, indicating successful authentication - Return null, indicating that the
	 * authentication process is still in progress. Before returning, the
	 * implementation should perform any additional work required to complete the
	 * process. - Throw an AuthenticationException if the authentication process
	 * fails
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
		logger.info("****attemptAuthentication*****");
		// Retrieve authentication data from the request
		String username = this.obtainUsername(httpServletRequest);
		String password = this.obtainPassword(httpServletRequest);
		Authentication authenticate  = null;
		try {

			AuthenticationManager authenticationManager = super.getAuthenticationManager();
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					username, password);
			 authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authenticate);
		} catch (Exception e) {
				e.printStackTrace();
				throw new UsernameNotFoundException(username);
		}
		return authenticate;

	}

	/**
	 * Enables subclasses to override the composition of the password, such as by
	 * including additional values and a separator.
	 * <p>
	 * This might be used for example if a postcode/zipcode was required in addition
	 * to the password. A delimiter such as a pipe (|) should be used to separate
	 * the password and extended value(s). The <code>AuthenticationDao</code> will
	 * need to generate the expected password in a corresponding manner.
	 * </p>
	 *
	 * @param request
	 *            so that request attributes can be retrieved
	 *
	 * @return the password that will be presented in the
	 *         <code>Authentication</code> request token to the
	 *         <code>AuthenticationManager</code>
	 */
	protected String obtainPassword(HttpServletRequest request) {
		return request.getParameter(passwordParameter);
	}

	/**
	 * Enables subclasses to override the composition of the username, such as by
	 * including additional values and a separator.
	 *
	 * @param request
	 *            so that request attributes can be retrieved
	 *
	 * @return the username that will be presented in the
	 *         <code>Authentication</code> request token to the
	 *         <code>AuthenticationManager</code>
	 */
	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(usernameParameter);
	}


	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		super.doFilter(req, res, chain);
	}

}
