package com.sampana.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sampana.login.model.User;
import com.sampana.login.service.UserService;
import com.sampana.login.vo.UserVO;



@Controller
public class HomeController {

	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
   
	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("/public/index");
		logger.info("App works");
		return model;
	}

	@GetMapping("/login")
	public ModelAndView getLogin() {
		ModelAndView model = new ModelAndView("/public/login");
		logger.info("login page called");
		return model;
	}
	@GetMapping("/signup")
	public ModelAndView getSignup() {
		ModelAndView model=new ModelAndView("/public/signup");
		model.addObject("userVO", new UserVO());
		logger.info("signup page called");
		return model;
		
	}
	/*
	@PostMapping(value="/signup")
	public ModelAndView signup(@ModelAttribute("user")  User user,BindingResult bindingResult,RedirectAttributes redirectAttributes) throws Exception {
	ModelAndView model=new ModelAndView("/public/login");
	if(user !=null) {
	userService.saveUser(user);
	}
		return model;
	}*/
	
	@PostMapping(value="/signup")
	public ModelAndView signup(@ModelAttribute("userVO")  UserVO userVO,BindingResult bindingResult,RedirectAttributes redirectAttributes) throws Exception {
	ModelAndView model=new ModelAndView("/public/login");
	if(userVO !=null) {
	userService.saveUser(userVO);
	}
		return model;
	}

}
