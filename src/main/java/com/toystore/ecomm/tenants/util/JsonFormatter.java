package com.toystore.ecomm.tenants.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormatter {

	public static JsonNode convertStringToJsonNode(String payloadStr) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
	    JsonNode actualObj = mapper.readTree(payloadStr);
	    
	    return actualObj;
	}
	
	public static String convertMapToJson(Map<String, String> elements) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(elements);
		
		return jsonStr;
	}
}
