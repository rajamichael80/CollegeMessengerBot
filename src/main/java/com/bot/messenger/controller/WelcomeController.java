package com.bot.messenger.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bot.messenger.dao.ContactRepository;
import com.bot.messenger.model.entity.Contact;

@Controller
public class WelcomeController {
	@Autowired
    ContactRepository contactRepository;

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
            ContactRepository repo = getContactRepository();
            Contact c = new Contact();
           c.setFirstName("Raj");
           c.setLastName("Kumar");
            List<Contact> contacts = null;
            if(repo != null) {
            	c  = repo.save(c);
                System.out.println("contacts1===>"+c);

                contacts = (List<Contact>) repo.findAll();
                System.out.println("contacts2===>"+contacts);
               
                model.addAttribute("contacts", contacts);
            }
            return "contact";
        } catch (Exception e) {
            model.addAttribute("contacts", new LinkedList());
            e.printStackTrace();
        }
        return "contact";
    }

	private ContactRepository getContactRepository() {

        return contactRepository;
    }

}
