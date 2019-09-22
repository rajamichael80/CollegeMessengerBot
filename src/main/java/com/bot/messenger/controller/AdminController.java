package com.bot.messenger.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.bot.messenger.model.fb.Admin;

@Controller
public class AdminController {
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
	    mav = new ModelAndView("welcome");
	    mav.addObject("firstname", "admin");
	    } else {
	    mav = new ModelAndView("admin");
	    mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }
	
}
