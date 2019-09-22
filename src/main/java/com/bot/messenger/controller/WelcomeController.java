package com.bot.messenger.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bot.messenger.dao.UserRepository;
import com.bot.messenger.model.entity.User;

@Controller
public class WelcomeController {
	@Autowired
    UserRepository userRepository;

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World1";

	@RequestMapping("/msg")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "admin";
	}
	
	@RequestMapping("/contacts")
    public String contacts(Model model) {
        try {
           UserRepository repo = getContactRepository();
            User user = new User();
         
            List<User> users = null;
            if(repo != null) {
            	user  = repo.save(user);
                System.out.println("contacts1===>"+user);

                users = (List<User>) repo.findAll();
                System.out.println("contacts2===>"+user);
               
                model.addAttribute("contacts", users);
            }
            return "contact";
        } catch (Exception e) {
            model.addAttribute("contacts", new LinkedList());
            e.printStackTrace();
        }
        return "contact";
    }

	private UserRepository getContactRepository() {

        return userRepository;
    }

}
