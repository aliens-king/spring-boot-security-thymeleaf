package com.sampana.login.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sampana.login.filter.AjaxTimeoutRedirectFilter;
import com.sampana.login.filter.UserAuthenticationFilter;
import com.sampana.login.handler.CustomAuthenticationFailureHandler;
import com.sampana.login.handler.CustomAuthenticationSuccessHandler;
import com.sampana.login.handler.OvoAccessDeniedHandler;
import com.sampana.login.service.UserService;

/**
 * This class handing Spring security beans. Allows customization to the
 * WebSecurity. In most instances users will use EnableWebSecurity and a create
 * Configuration that extends WebSecurityConfigurerAdapter which will
 * automatically be applied to the WebSecurity by the EnableWebSecurity
 * annotation.
 * 
 *  @author Sudhanshu
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	private static final String WILDCARD = "/**";

	/**
	 * By pass Spring security urls.
	 */
	private static final String[] BY_PASS_SECURITY_PERMITALL_URLS = { "/home", "/login", "/signup","/dashboard" };

	private static final String[] ALLOW_APP_RESOURCES = { "/css/**", "/custom/**", "/images/**", "/fonts/**","/js/**" };

	@Autowired
	private UserService userService;

	/**
	 * this bean should be return configured multiple authentication manager.
	 */
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		logger.info("AuthenticationManager bean successfully initiated");
		return super.authenticationManager();
	}

	/**
	 * configuring multiple authentication manager builder.
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());// DB
	}

	/**
	 * Database authentication provider.
	 * 
	 * @return
	 * @throws Exception
	 */
	private AuthenticationProvider authenticationProvider() throws Exception {
		logger.info("authenticationProvider function invoked ");
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	/**
	 * Service interface for encoding passwords. The preferred implementation is
	 * BCryptPasswordEncoder.
	 * 
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		logger.info("PasswordEncoder bean successfully initiated ");
		return new BCryptPasswordEncoder();
	}

	/**
	 * http or XmlHttp Request time out filter.
	 * 
	 * @return
	 */
	@Bean
	public AjaxTimeoutRedirectFilter ajaxTimeoutRedirectFilter() {
		logger.info("AjaxTimeoutRedirectFilter bean successfully initiated ");
		AjaxTimeoutRedirectFilter ajaxTimeoutRedirectFilter = new AjaxTimeoutRedirectFilter();
		ajaxTimeoutRedirectFilter.setCustomSessionExpiredErrorCode(901);
		return ajaxTimeoutRedirectFilter;
	}

	/**
	 * This bean is using for while user successfully logged in.
	 * 
	 * @return
	 */

	@Bean
	public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		logger.info("CustomAuthenticationSuccessHandler bean successfully initiated ");
		return new CustomAuthenticationSuccessHandler();
	}

	/**
	 * This bean is using for while user logging failed.
	 * 
	 * @return
	 */

	@Bean
	public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
		logger.info("CustomAuthenticationFailureHandler bean successfully initiated ");
		return new CustomAuthenticationFailureHandler();
	}

	/**
	 * The Class VeridianAccessDeniedHandler. this is using for handling control or
	 * access for url.
	 * 
	 * @return
	 */
	@Bean
	public OvoAccessDeniedHandler veridianAccessDeniedHandler() {
		logger.info("VeridianAccessDeniedHandler bean successfully initiated ");
		OvoAccessDeniedHandler veridianAccessDeniedHandler = new OvoAccessDeniedHandler();
		veridianAccessDeniedHandler.setAccessDeniedUrl("/denied");
		return veridianAccessDeniedHandler;
	}

	/**
	 * custom SimpleUrlLogoutSuccessHandler.
	 * 
	 * @return
	 */
	@Bean
	public SimpleUrlLogoutSuccessHandler successLogoutHandler() {
		SimpleUrlLogoutSuccessHandler successLogoutHandler = new SimpleUrlLogoutSuccessHandler();
		successLogoutHandler.setDefaultTargetUrl("/login");
		return successLogoutHandler;
	}

	/**
	 * custom logout handler
	 * 
	 * @return
	 */
	@Bean
	public SecurityContextLogoutHandler logoutHandler() {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.setInvalidateHttpSession(true);
		logoutHandler.setClearAuthentication(true);
		return logoutHandler;
	}

	/**
	 * This bean initiate custom Authentication filter for being distinguish login
	 * request.
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	public UserAuthenticationFilter userAuthenticationFilter() throws Exception {
		UserAuthenticationFilter userAuthenticationFilter = new UserAuthenticationFilter();
		userAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		userAuthenticationFilter
				.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login/authenticate", "POST"));
		userAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler());
		userAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());
		return userAuthenticationFilter;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources" + WILDCARD);
	}

	/**
	 * this method to configure the HttpSecurity. Typically subclasses should not
	 * invoke this method by calling super as it may override their configuration.
	 * The default configuration is:
	 * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("configure http Spring security");
		/*http.csrf().disable().authorizeRequests().antMatchers(BY_PASS_SECURITY_PERMITALL_URLS).permitAll()*/
				/*.antMatchers(ALLOW_APP_RESOURCES).permitAll().anyRequest().hasAnyRole("ANONYMOUS, USER")
				.antMatchers("/denied").fullyAuthenticated().anyRequest().authenticated()*/
		http.csrf().disable().authorizeRequests().antMatchers(BY_PASS_SECURITY_PERMITALL_URLS).permitAll()
		.antMatchers(ALLOW_APP_RESOURCES).permitAll().anyRequest().authenticated();
		
		// .antMatchers(REDIRECTED_TO_LOGIN_URLS).denyAll();

		securityFormLogin(http);
	}

	/**
	 * Spring form Login security configuration.
	 * 
	 * @param http
	 * @throws Exception
	 */
	private void securityFormLogin(HttpSecurity http) throws Exception {
		http.formLogin().usernameParameter("username").passwordParameter("password").loginPage("/login")
				.successHandler(customAuthenticationSuccessHandler())
				.failureHandler(customAuthenticationFailureHandler()).and().logout().logoutUrl("/logout")
				.logoutSuccessHandler(successLogoutHandler()).addLogoutHandler(logoutHandler()).permitAll()
				.deleteCookies("JSESSIONID").permitAll().and()
		.addFilterAfter(ajaxTimeoutRedirectFilter(), ExceptionTranslationFilter.class).exceptionHandling().and()
				.addFilterBefore(userAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).rememberMe()
				.tokenValiditySeconds(1209600).and().exceptionHandling()
				.accessDeniedHandler(veridianAccessDeniedHandler()).and().sessionManagement().maximumSessions(1)
				.expiredUrl("/home");

	}

}