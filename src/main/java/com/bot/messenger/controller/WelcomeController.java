package com.bot.messenger.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World1";

	@RequestMapping("/msg")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "admin";
	}

}
