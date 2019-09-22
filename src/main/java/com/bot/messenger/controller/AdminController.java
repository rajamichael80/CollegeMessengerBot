package com.bot.messenger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bot.messenger.controller.fb.FbWebhookController;
import com.bot.messenger.model.entity.Admin;
import com.bot.messenger.model.entity.User;
import com.bot.messenger.service.IUserService;
import com.bot.messenger.service.UserService;

@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("admin");
		mav.addObject("admin", new Admin());
		return mav;
	}
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("admin") Admin admin) {
	    ModelAndView mav = null;
	   // User user = userService.validateUser(login);
	    if (admin.getUsername().equals("admin")) {
	    mav = new ModelAndView("userDetails");
		IUserService userService = new UserService();
		List<User> users = userService.getUserDetails();
		logger.info("<<<<<<<<<users>>>>>>>>>>", users);
		mav.addObject("users",users);
	    } else {
	    mav = new ModelAndView("admin");
	    mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }
	
}
