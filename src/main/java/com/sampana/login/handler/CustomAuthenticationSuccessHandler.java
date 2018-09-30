package com.sampana.login.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sampana.login.model.User;
import com.sampana.login.service.UserService;
import com.sampana.login.session.SessionObject;
import com.sampana.login.utils.IConstants;
import com.sampana.login.utils.ObjectUtils;
import com.sampana.login.vo.UserVO;


/**
 * This class working as handler for authentication Success Strategy used to
 * handle a successful user authentication. Implementations can do whatever they
 * want but typical behaviour would be to control the navigation to the
 * subsequent destination (using a redirect or a forward). For example, after a
 * user has logged in by submitting a login form, the application needs to
 * decide where they should be redirected to afterwards (see
 * AbstractAuthenticationProcessingFilter and subclasses). Other logic may also
 * be included if required.
 * 
 * @author Sudhanshu
 *
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("***************************  onAuthenticationSuccess *************************");
		/* Set some session variables */
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// Condition should be invoked while user authenticate through database.
		if (!ObjectUtils.isObjectEmpty(object)) {
				User user = userService.findByUserName(((UserDetails) object).getUsername());
				HttpSession session = request.getSession();
				SessionObject sessionObject = new SessionObject();
				UserVO userVO = new UserVO();
				userVO.setUserId(user.getUserId());
				userVO.setEmail(user.getEmail());
				userVO.setFullName(user.getFullName());
				userVO.setUserName(user.getUserName());
				userVO.setPassword(user.getPassword());
				sessionObject.setUserVO(userVO);
				session.setAttribute(IConstants.SESSION_OBJECT, sessionObject);
				logger.info("User authenticated..");
				String targetUrl = determineTargetUrl(authentication, user);
				//response.sendRedirect(targetUrl);

				redirectStrategy.sendRedirect(request, response, targetUrl);
		}
	}
	
	
	/**
	 * Evaluate target url.
	 * 
	 * @param authentication
	 * @param authUser
	 * @return
	 */
	@SuppressWarnings("unused")
	protected String determineTargetUrl(Authentication authentication, User user) {
		/* redirect to dashboard by default. */
		
		String redirectTargetUrl;
		//Long id = user.getUserRoles().get(0).getRole().getRoleId();
		
		if(user.getActive() == IConstants.ACTIVE_USER){
			redirectTargetUrl = "/user/dashboard";
		}
		else
			redirectTargetUrl = "/validateuser";
//		if(user.getUserRoles().get(0).getRole().getRoleName().equals("admin"))
//		{
//			 redirectTargetUrl = "/user/dashboard";
//		
//	}
		
		return redirectTargetUrl;
	}

}
