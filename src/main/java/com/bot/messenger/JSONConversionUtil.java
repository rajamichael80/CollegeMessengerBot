package com.bot.messenger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.bot.messenger.JSON.QnaResponseMap;
import com.bot.messenger.JSON.JSONResponse;
import com.bot.messenger.JSON.ResponseMessage;
import com.google.gson.Gson;

public class JSONConversionUtil {

	public final static File fos = new File("CollegeMessengerBot//src//main//java//com//bot//messenger//JSONFile.xml");

	public static String convertObjectToJson(ResponseMessage responseMessage) {
		Gson gson = JsonUtil.getInstance();
		String jsonValue = gson.toJson(responseMessage);
		System.out.println(jsonValue);
		return jsonValue;
	}

	public static QnaResponseMap convertXmlToObject() {
		System.out.println("--FIle path--"+fos.getAbsolutePath());
		JAXBContext jaxbContext;
		QnaResponseMap qnaResponseMap = new QnaResponseMap();
		try {
			jaxbContext = JAXBContext.newInstance(QnaResponseMap.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			qnaResponseMap = (QnaResponseMap) jaxbUnmarshaller.unmarshal(fos);
			System.out.println("---inside convertXmlToObject---"+qnaResponseMap);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return qnaResponseMap;

	}
	
	public static void convertObjectToXML(String key,String value) {
		JAXBContext jaxbContext;
		QnaResponseMap qnaResponseMap = new QnaResponseMap();

		try {
		    jaxbContext = JAXBContext.newInstance(QnaResponseMap.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(qnaResponseMap, new FileOutputStream(fos));

		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
