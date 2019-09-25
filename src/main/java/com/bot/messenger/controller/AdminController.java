package com.bot.messenger.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bot.messenger.model.entity.Admin;
import com.bot.messenger.model.entity.User;
import com.bot.messenger.service.IUserService;

@Controller
public class AdminController {
	@Autowired
	IUserService userService;
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		logger.info("----inside showLogin------");

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
			List<User> users = userService.getUserDetails();
			List<User> userDetails = new ArrayList<>();
			if (users != null) {
				userDetails = users.stream().distinct().collect(Collectors.toList());
			}
			logger.info("<<<<<<<<<user details>>>>>>>>>>::::{}", userDetails);

			mav.addObject("users", userDetails);
		} else {
			mav = new ModelAndView("admin");
			mav.addObject("message", "Username or Password is wrong!!");
		}
		return mav;
	}

}
