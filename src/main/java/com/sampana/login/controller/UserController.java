package com.sampana.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sampana.login.session.SessionObject;
import com.sampana.login.vo.UserVO;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@GetMapping(value = "/dashboard")
	public ModelAndView dashboard(ModelAndView modelAndView) {

		modelAndView.setViewName("public/login");
		SessionObject sessionObject = getSessionObject();
		if (sessionObject != null) {
			UserVO userVO = sessionObject.getUserVO();
			if (userVO != null) {
				modelAndView.addObject("firstName", sessionObject.getUserVO().getFullName());
				modelAndView.setViewName("/dashboard");
				return modelAndView;
			}
		}
		return modelAndView;

	}

}
