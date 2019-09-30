package com.bot.messenger;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.messenger.model.entity.UserQuestions;
import com.bot.messenger.model.fb.UserDetail;
import com.bot.messenger.service.IUserQuestionService;
import com.bot.messenger.service.IUserService;
import com.bot.messenger.service.UserQuestionService;

public class QnaResponse1 {
	private static final Logger logger = LoggerFactory.getLogger(QnaResponse1.class);
	
	public  String getJsonResponse(String senderId, String requestText, UserDetail userDetail) {
		String jsonResponse = "";		
		switch (getActualKeyword(requestText)) {
		case "hi":
			jsonResponse = String.format(
					"{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }",
					"Hi " + userDetail.getFirstName() + " " + userDetail.getLastName()+", Welcome to New Prince Shri Bhavani College of ENgineering and Technology, How can I help you?");
			break;		
		case "course":
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"generic\", \"elements\":[ { \"title\":\"Courses\", \"image_url\":\"https://udayanbot.herokuapp.com/img/hype_bse.gif\", \"subtitle\":\"Here is cousre details\", \"buttons\":[ { \"type\":\"postback\",  \"title\":\"MCA\" ,\"payload\":\"MCADemo\"}, { \"type\":\"web_url\", \"url\":\"http://www.newprinceshribhavani.com/computer-science.php\", \"title\":\"Information Technology\" }, { \"type\":\"postback\", \"title\":\"Any Other Query\", \"payload\":\"call our representative\" } ] }  ] } } } }";
		   break;
		case "time":
			jsonResponse = String.format("{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }","College start at 9.00 AM and ends at 4.30PM");
			break;
				
		case "call our representative":
			jsonResponse = String.format(
					"{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }",
					"Hi " + userDetail.getFirstName() + " " + userDetail.getLastName()+", puoi contattare l'assistenza clienti al numero 800.142.142 (per le chiamate da rete fissa nazionale) oppure al numero 0039.015.24.34.617 (per le chiamate dall'estero e da telefono cellulare) attivi dal luneda al venerda dalle ore 8.00 alle 21.00. Hai bisogno di altre informazioni?");
			break;
		case "bye":
		case "":
			jsonResponse = String.format(
					"{ \"recipient\": { \"id\": \"recipientId\" }, \"message\": { \"text\": \"%s\" } }",
					"Grazie " + userDetail.getFirstName() + " " + userDetail.getLastName()+", Speriamo di aver risposto alle tue domamde.  Buona giornata ");
		break;
		
		default:
			
			jsonResponse = "{ \"recipient\":{ \"id\":\"recipientId\" }, \"message\":{ \"attachment\":{ \"type\":\"template\", \"payload\":{ \"template_type\":\"button\", \"text\":\"I could not understand the request, if you wish I can put you in touch with one of our assistants.\", \"buttons\":[ { \"type\":\"phone_number\", \"title\":\"Contact Number\", \"payload\":\"+390152434600\" } ] } } } }";
			break; 
		}
		jsonResponse = jsonResponse.replace("recipientId", senderId);
		logger.info("jsonresponse>>>>{}", jsonResponse);
		byte[] utf8Bytes;
		try {
			utf8Bytes = jsonResponse.getBytes("UTF8");
			//return new String(utf8Bytes,"UTF8").replace("è", "e");
			return new String(utf8Bytes,"UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jsonResponse;
		
	}
	
	private static String getActualKeyword(String textSearch) {
		String searchString=textSearch.toLowerCase();
		if(textSearch.equals("first hand shake")) {
			searchString="welcome msg";
		}else if(textSearch.contains("all course") || textSearch.contains("course")) {
			searchString="course";
		}
		else if( textSearch.contains("time")) {
			searchString="time";
		}
		logger.info("The actual Search String  is{}",searchString);
		return searchString.toLowerCase();
		
	}
}





