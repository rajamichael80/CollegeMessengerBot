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
		String errorMessage ="Success";
		Connection connection = null;
		StackTraceElement[] trace = null;
		try {
			connection = DataBaseConnection.getConnection();
			//schemaName = connection.getSchema();
		} catch (Exception e) {
			System.out.println(e);
			trace = e.getStackTrace();
			schemaName = e.getMessage();
			errorMessage = "Failure";
		}
		return new ResponseEntity<String>("Default controller is Listening errorMessage ="+errorMessage +";Trace"+trace+";connection="+connection, HttpStatus.OK);
	}
}
//https://messengerdevelopers.com/resources/messaging
