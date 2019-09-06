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
			  stmt.executeUpdate("DROP TABLE IF EXISTS COMPANY");

       			 int i =  stmt.executeUpdate("CREATE TABLE COMPANY(CID INT PRIMARY KEY  NOT NULL,NAME  TEXT NOT NULL)");
			 System.out.println("table status:"+i);
			
      			  stmt.executeUpdate("INSERT INTO COMPANY (ID,NAME) VALUES (1, 'Raj')");
      			 	ResultSet rs = stmt.executeQuery("SELECT NAME FROM COMPANY");
			int size = rs.getFetchSize();
	 		 System.out.println("Total Record Size = "+size);
        			while (rs.next()) {
        		   System.out.println("Read from DB: " + rs.getInt(2));
       				 }
			schemaName = connection.getSchema();
		} catch (Exception e) {
			
		}
		return new ResponseEntity<String>("Default controller is Listening Schema Name:"+schemaName, HttpStatus.OK);
	}
}
//https://messengerdevelopers.com/resources/messaging
