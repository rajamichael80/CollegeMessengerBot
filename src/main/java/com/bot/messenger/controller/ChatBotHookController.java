package com.bot.messenger.controller;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bot.messenger.dao.DataBaseConnection;

@RestController
public class ChatBotHookController {

	@GetMapping("/")
	public ResponseEntity<?> sayConnected() {
		String schemaName = "welcome";
		DataBaseConnection db= new DataBaseConnection();
		try {
			Connection connection = db.getConnection();
			schemaName = connection.getSchema();
		} catch (URISyntaxException | SQLException e) {
			schemaName =e.getMessage();
		}
		return new ResponseEntity<String>("Default controller is Listening schemaName is:"+schemaName, HttpStatus.OK);
	}
}
//https://messengerdevelopers.com/resources/messaging