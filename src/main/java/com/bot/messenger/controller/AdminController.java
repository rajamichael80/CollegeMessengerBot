package com.bot.messenger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bot.messenger.model.entity.Admin;
import com.bot.messenger.model.entity.User;
import com.bot.messenger.model.entity.UserQuestions;
import com.bot.messenger.service.IUserQuestionService;
import com.bot.messenger.service.IUserService;

@Controller
public class AdminController {
	@Autowired
	IUserService userService;
	@Autowired
	IUserQuestionService userQuestionService;

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
		if ("admin".equals(admin.getUsername()) && "admin".equals(admin.getPassword())) {
			mav = new ModelAndView("UserInfo");
		} else {
			mav = new ModelAndView("admin");
			mav.addObject("message", "Username or Password is wrong!!");
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public ModelAndView getUserInfo(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("admin") Admin admin) {
  		
		   ModelAndView mav = new ModelAndView("userDetails");
			List<User> users = userService.getUserDetails();
		
			logger.info("<<<<<<<<<user details>>>>>>>>>>::::{}", users);

			mav.addObject("users", users);
			return mav;
	}

	
	@RequestMapping(value = "/userQuestions", method = RequestMethod.GET)
	public ModelAndView getUserQuestions(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("admin") Admin admin) {
	System.out.println("--inside user Questions--"+userQuestionService);
		   ModelAndView mav = new ModelAndView("userQuestions");
			List<UserQuestions> userQuestions = userQuestionService.getUserQuestions();
			
			logger.info("<<<<<<<<<userQuestions>>>>>>>>>>::::{}", userQuestions);

			mav.addObject("userQuestions", userQuestions);
		
		return mav;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView backToHome(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("admin") Admin admin) {
  		
		   ModelAndView mav = new ModelAndView("UserInfo");
			return mav;
	}
	
	@RequestMapping(value="/deleteQuestion/{id}",method = RequestMethod.GET)    
    public String deleteQuestion(@PathVariable Long id){    
		logger.info("<<<<<<<<<question id>>>>>>>>>>::::{}", id);

		   userQuestionService.deleteUserQuestion(id);
	        return "redirect:/userQuestions";    
    }     
	
}
