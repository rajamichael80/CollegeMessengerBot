package com.bot.messenger.controller;

import java.net.URISyntaxException;
import java.sql.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bot.messenger.dao.DataBaseConnection;

@RestController
public class ChatBotHookController {

	@GetMapping("/")
	public ResponseEntity<?> sayConnected() {
		String schemaName = null;
		Connection connection = null;
		try {
			connection = DataBaseConnection.getConnection();
			Statement stmt = connection.createStatement();
      			  stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
       			 stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
      			  stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      				  ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
        			while (rs.next()) {
        		    System.out.println("Read from DB: " + rs.getTimestamp("tick"));
       				 }
			schemaName = connection.getSchema();
		} catch (Exception e) {
			
		}
		return new ResponseEntity<String>("Default controller is Listening Schema Name:"+schemaName, HttpStatus.OK);
	}
}
//https://messengerdevelopers.com/resources/messaging
