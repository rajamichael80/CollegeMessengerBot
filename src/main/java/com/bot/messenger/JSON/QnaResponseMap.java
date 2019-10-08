package com.bot.messenger.JSON;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement (name="JSONData")
@XmlAccessorType(XmlAccessType.FIELD)
public class QnaResponseMap {
     
    private Map<String, String> qnaResponse = new HashMap<String, String>();

	public Map<String, String> getQnaResponse() {
		return qnaResponse;
	}

	public void setQnaResponse(Map<String, String> qnaResponse) {
		this.qnaResponse = qnaResponse;
	}

	@Override
	public String toString() {
		return "QnaResponseMap [qnaResponse=" + qnaResponse + "]";
	}
 
    
    
}