package com.bot.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class CollegeMessengerBotApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CollegeMessengerBotApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CollegeMessengerBotApplication.class, args);
	}

}
