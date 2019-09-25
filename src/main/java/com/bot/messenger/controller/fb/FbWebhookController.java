
package com.bot.messenger.controller.fb;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bot.messenger.BotSession;
import com.bot.messenger.JsonUtil;
import com.bot.messenger.QnaResponse1;
import com.bot.messenger.model.fb.Entry;
import com.bot.messenger.model.fb.Messaging;
import com.bot.messenger.model.fb.RequestPayload;
import com.bot.messenger.model.fb.UserDetail;
import com.bot.messenger.service.IUserService;




@RestController
public class FbWebhookController {
	@Autowired
	IUserService userService;
	Map<String, BotSession> botSessionMap = new HashMap<String, BotSession>();
	private static String senderId;
    //https://sella.it/sellabot/chatinit?nome=nome1&cognome=cognome1&email=test@sella.it&CHANNEL=Sella_sito_free
	private static final String SIGNATURE_HEADER_NAME = "X-Hub-Signature";
	private static final String ACCESS_TOKEN = "EAAE3uHosZBYMBAJjwfDQTsdRuT2HYhWcZCbjeflFK6d3z0JcMPZCRj57Mp8E4FmZA88eT0ww5kCTZAyYtm9fuvYcLcZBi8G36C26cfJPWseFXqC0skoqUmg60XznLXODCxZBtE4MeCvwuKVXvOLbJPmo1lZAaNdXug9VKit4eaLABZBjTJ8MOt3nZC";
	private static final String FB_GRAPH_API_URL_MESSAGES = "https://graph.facebook.com/v2.6/me/messages?access_token=%s";
	
	private static final Logger logger = LoggerFactory.getLogger(FbWebhookController.class);
	@GetMapping("/webhook")
	public ResponseEntity<?> verify(@RequestParam("hub.challenge") String challenge,
			@RequestParam("hub.verify_token") String token) {
		logger.info("<<<<<<<<<<<<<Challenge is:{} and token is {}>>>>>>>>>>>", challenge, token);
		if (token.equals("demotoken123"))
			return new ResponseEntity<String>(challenge, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	
	@PostMapping(path = "/webhook", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getFbMessage(@RequestBody final String payLoad,
			@RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
		
		//IUserService userService = new UserService();
		new Thread() {
			public void run() {
				logger.info("<<<<<<<<<FaceBookResponse payload:{}>>>>>>>>>>", payLoad);
				RequestPayload reqPayload=getResponseObject(payLoad);
				logger.info("<<<<<<<<<<<<<<<<reqpayload>>>>{}>>>>>>>>>>>>>",reqPayload);
				final String senderId = reqPayload.getEntry().get(0).getMessaging().get(0).getSender().getId();
				final String recipientId = reqPayload.getEntry().get(0).getMessaging().get(0).getRecipient().getId();
				logger.info("<<<<<<<<<<senderId>>>>{},RecipientId>>>{}>>>>>>>>>>>>>>>", senderId, recipientId);
				UserDetail userDetail = getUserDetail(senderId);
				

		try {
			logger.info("save user try catch::{}",userService);
			//userDetail = getUserDetail(senderId);
			
			userService.saveUser(userDetail);
		} catch (Exception e) {
			logger.info("thiS is the save user::{}", e.getMessage(), e);
		}

		logger.info("try end");
		String eventType=getEventType(reqPayload);	
		for (Entry entry : reqPayload.getEntry()) {
			for (Messaging messaging : entry.getMessaging()) {
				final String textMessage = eventType.equals("PostbackEvent") ? messaging.getPostback().getPayload()	: messaging.getMessage().getText();
				logger.info("<<<<<<<<<<<<TextMessage::{},EventyType:::{}>>>>>>>>>>>>>>", textMessage, eventType);
				String senderActionAcknowledge = sendMessage(getSenderActionResonse("mark_seen", senderId));
				logger.info("<<<<<<<<<<senderActionAcknowledge::::{}>>>>>>>>>>>>", senderActionAcknowledge);
				senderActionAcknowledge = sendMessage(getSenderActionResonse("typing_on", senderId));
				logger.info("<<<<<<<<<<<<<senderActionAcknowledge::::{}>>>>>>>>>>>>>>", senderActionAcknowledge);
				logger.info("<<<<<<<<<<<<<Actual message sending started>>>>>>>>>>>>>>");
				try {
					sendMessage(QnaResponse1.getJsonResponse(senderId, textMessage!=null?textMessage.toLowerCase():"",userDetail));

				}catch(Exception e) {
					logger.info("thiS is the error demo bot caught::{}",e.getMessage(),e);
				}				
				senderActionAcknowledge = sendMessage(getSenderActionResonse("typing_off", senderId));
				logger.info("senderActionAcknowledge>>>>{}", senderActionAcknowledge);
			}
		}	
		}}.start();
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	public String sendMessage(String payLoad) {
		logger.info("<<<<<<<current log:::{}>>>>>",payLoad);
		String url = String.format(FB_GRAPH_API_URL_MESSAGES, ACCESS_TOKEN);
		logger.info("<<<<<<<<FB_GRAPH_API_URL_MESSAGES:::::::{}>>>>>>>>>>>",url);
		RestTemplate restTemplate=new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);	
		HttpEntity<String> entity = new HttpEntity<String>(payLoad, headers);		
		return restTemplate.postForObject(url, entity, String.class);
	}
	
	public UserDetail getUserDetail(String senderId) {
		String formattedUrl = "https://graph.facebook.com/%saccess_token=%s";
		String url = String.format(formattedUrl, senderId + "?fields=first_name,last_name,profile_pic&", ACCESS_TOKEN);
		RestTemplate restTemplate=new RestTemplate();	
		UserDetail userDetail = restTemplate.getForObject(url, UserDetail.class);
		return userDetail;
	}
	
	
	private String getSenderActionResonse(final String senderAction, final String senderId) {
		return String.format("{ \"recipient\":{ \"id\":\"%s\" }, \"sender_action\":\"%s\" }", senderId,senderAction);
	}	
	

	//to get the json to gson object
	public static RequestPayload getResponseObject(final String responsePayload) {		
		return JsonUtil.getInstance().fromJson(responsePayload, RequestPayload.class);
	}
	
	//to get the requested eventype
	private String getEventType(RequestPayload requestPayload) {
		String eventType="TextEvent";
		if(requestPayload.getEntry().get(0).getMessaging().get(0).getPostback()!=null) {
			eventType="PostbackEvent";
		}
		logger.info("The requested event Tyepe {}",eventType);
		return eventType;		
	}		
}
