package com.bot.messenger.JSON;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
public class JSONResponse {
	Map<String, String> response = new HashMap<String, String>();

	public Map<String, String> getreRponse() {
		return response;
	}

	public void setResponse(Map<String, String> response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "ResponseMapper [map=" + response + "]";
	}
	
	

}
