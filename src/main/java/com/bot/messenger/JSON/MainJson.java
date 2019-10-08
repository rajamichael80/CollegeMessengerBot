package com.bot.messenger.JSON;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.ResourceLoader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class MainJson {
	public static void main(String[] args) throws Exception {
		// File fos = new File("/CollegeMessengerBot/src/main/resources/JSONFile.xml");

		ResponseMessage postbackMessage = new ResponseMessage();
		postbackMessage.setRecipientId("1234");

		Element element = new Element();
		element.setTitle("Hi! " + ", Welcome to True Buying");
		element.setImageUrl(
				"https://static.tcimg.net/vehicles/primary/dfb65a418bb4d8a0/2019-Porsche-718_Boxster-red-full_color-driver_side_front_quarter.png");

		Button offerButton = new Button();
		offerButton.setPayload("offers");
		offerButton.setTitle("View Offers");
		element.addButtons(offerButton);
		

		Button priceButton = new Button();
		priceButton.setPayload("price");
		priceButton.setTitle("View Price");

		postbackMessage.getMessage().getAttachment().getPayload().getElements().add(element);
		ObjectMapper objectMapper = new ObjectMapper();

		String jsonInString = objectMapper.writeValueAsString(postbackMessage);
		System.out.println("jsonInString = " + jsonInString);
		// Gson g = new GsonBuilder().serializeNulls().create();
		Gson g = new Gson();
		String str = g.toJson(postbackMessage);
		System.out.println(str);

		Map<String, String> map = new HashMap<String, String>();

		map.put("1", str);
		map.put("2", "two");

		// Add employees in map
		QnaResponseMap employeeMap = new QnaResponseMap();
		employeeMap.setQnaResponse(map);

		/******************** Marshalling example *****************************/

		JAXBContext jaxbContext = JAXBContext.newInstance(QnaResponseMap.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(employeeMap, new FileOutputStream(
				"..//CollegeMessengerBot//src//main//java//com//bot//messenger//JSON//JSONFile.xml"));

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		QnaResponseMap empMap = (QnaResponseMap) jaxbUnmarshaller.unmarshal(
				new File("..//CollegeMessengerBot//src//main//java//com//bot//messenger//JSON//JSONFile.xml"));

		empMap.getQnaResponse().put("5", "five");
		System.out.println(empMap);
		jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(empMap, new FileOutputStream("JSONFile.xml"));

	}

}
