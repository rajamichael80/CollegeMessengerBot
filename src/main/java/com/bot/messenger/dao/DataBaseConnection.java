package com.bot.messenger.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
public class DataBaseConnection {
	public static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
System.out.println("dbUri = "+dbUri);
		
		  String username = dbUri.getUserInfo().split(":")[0]; 
		System.out.println("username="+username);

		  String password =  dbUri.getUserInfo().split(":")[1]; 
		  String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +"?sslmode=require";
		  return DriverManager.getConnection(dbUrl, username, password);
		
	}
}
