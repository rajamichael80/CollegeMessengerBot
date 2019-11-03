package com.bot.messenger;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bot.messenger.model.fb.UserDetail;

public class QnaResponse1 {
	private static final Logger logger = LoggerFactory.getLogger(QnaResponse1.class);

	public String getJsonResponse(String senderId, String requestText, UserDetail userDetail) {
		String jsonResponse = "";
		switch (getActualKeyword(requestText)) {
		case "hi":
			jsonResponse = String.format(
					"{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }",
					"Hi " + userDetail.getFirstName() + " " + userDetail.getLastName()
							+ ", Welcome to New Prince Shri Bhavani College of ENgineering and Technology, How can I help you?");
			break;
		case "hai":
			jsonResponse = String.format(
					"{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }",
					"Hi " + userDetail.getFirstName() + " " + userDetail.getLastName()
							+ ", Welcome to New Prince Shri Bhavani College of ENgineering and Technology, How can I help you?");
			break;
		case "course":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Courses\", \"image_url\":\"https://college-messanger-bot.herokuapp.com/img/courses_offered.gif\", \"subtitle\":\"Here is cousre details\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"http://www.newprinceshribhavani.com/computer-applications.php\", \"title\":\"MCA\" }, { \"type\":\"web_url\", \"url\":\"http://www.newprinceshribhavani.com/computer-science.php\", \"title\":\"Information Technology\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] }  ] } } } }";			
			break;
		case "time":
			jsonResponse = String.format(
					"{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }",
					"College start at 9.00 AM and ends at 4.30PM");
			break;
	
		case "call our representative":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Contact\", \"image_url\":\"https://college-messanger-bot.herokuapp.com/img/contact.gif\", \"subtitle\":\"Here is cousre details\", \"buttons\":[ { \"type\":\"web_url\", \"url\":\"http://www.newprinceshribhavani.com/contact.php\", \"title\":\"Contact\" } ] }  ] } } } }";			
			break;
		case "bye":
		case "":
			jsonResponse = String.format(
					"{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }",
					"Welcome " + userDetail.getFirstName() + " " + userDetail.getLastName()
							+ ", Have a nice day ");
			break;

		default:

			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"button\", \"text\":\"I could not understand the request, if you wish I can put you in touch with one of our assistants.\", \"buttons\":[ { \"type\":\"phone_number\", \"title\":\"Contact Number\", \"payload\":\"9104422780404\" } ] } } } }";
			break;
		}

		jsonResponse = jsonResponse.replace("recipientId", senderId);
		logger.info("jsonresponse>>>>{}", jsonResponse);
		byte[] utf8Bytes;
		try {
			utf8Bytes = jsonResponse.getBytes("UTF8");
			// return new String(utf8Bytes,"UTF8").replace("è", "e");
			return new String(utf8Bytes, "UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jsonResponse;

	}

	private static String getActualKeyword(String textSearch) {
		String searchString = textSearch.toLowerCase();
		if (textSearch.equals("first hand shake")) {
			searchString = "welcome msg";
		} else if (textSearch.contains("all course") || textSearch.contains("course")) {
			searchString = "course";
		} else if (textSearch.contains("time") || textSearch.contains("timing")) {
			searchString = "time";
		} else if (textSearch.contains("thank")) {
			searchString = "bye";
		}
		
		logger.info("The actual Search String  is{}", searchString);
		return searchString.toLowerCase();

	}
}
