package com.sampana.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;

import com.sampana.login.session.SessionObject;
import com.sampana.login.utils.IConstants;

/**
 * This Controller is uses for handle like as Session Object and other stuff
 * that which are required to other controllers.
 * 
 *  @author Sudhanshu
 *
 */
public abstract class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Resource
	protected Environment environment;

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected HttpServletRequest request;

	/**
	 * Getting session object from session Checks if the session is invalidated or
	 * destroyed
	 * 
	 * @return
	 */
	protected SessionObject getSessionObject() {
		logger.info("get Session object from Base Controller");
		HttpSession session = getSession();
		SessionObject sessionObject;
		try {
			sessionObject = (SessionObject) session.getAttribute(IConstants.SESSION_OBJECT);
		} catch (IllegalStateException ise) {
			session = request.getSession(false);
			sessionObject = new SessionObject();
			setSessionObject(sessionObject);

		}
		return sessionObject;
	}

	/**
	 * Setting session object in session
	 * 
	 * @param sessionObject
	 */
	protected void setSessionObject(SessionObject sessionObject) {
		logger.info("set Session object ");
		HttpSession session = getSession();
		session.setAttribute(IConstants.SESSION_OBJECT, sessionObject);
	}

	public HttpSession getSession() {
		HttpSession session = request.getSession();
		return session;
	}

}
