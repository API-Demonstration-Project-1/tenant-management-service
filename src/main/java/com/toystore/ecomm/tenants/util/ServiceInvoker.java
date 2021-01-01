package com.toystore.ecomm.tenants.util;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.toystore.ecomm.tenants.constants.PTMSConstants;

public class ServiceInvoker {
	
	private static final String CLIENT_APP_USERNAME = "eagleeye";
	private static final String CLIENT_APP_PASSWORD = "thisissecret";
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BASIC_TYPE_AUTH = "Basic";
	private static final String GRANT_TYPE_PARAM = "grant_type";
	private static final String SCOPE_PARAM = "scope";
	private static final String LOGGED_USERNAME_PARAM = "username";
	private static final String LOGGED_PASSWORD_PARAM = "password";
	private static final String GRANT_TYPE_VALUE1 = "password";
	private static final String SCOPE_VALUE1 = "webclient";
	
	public static ResponseEntity<String> invokeAuthServer(String url, String userName, String password) {
		
	    // Create Auth Credentials
	    String authStr = CLIENT_APP_USERNAME + PTMSConstants.COLON_SEPARATOR + CLIENT_APP_PASSWORD;
	    String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

	    // Create Headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(AUTHORIZATION_HEADER, BASIC_TYPE_AUTH + PTMSConstants.SINGLE_SPACE + base64Creds);
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    
	    // Create Form Data
	    LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	    map.add(GRANT_TYPE_PARAM, GRANT_TYPE_VALUE1);
	    map.add(SCOPE_PARAM, SCOPE_VALUE1);
	    map.add(LOGGED_USERNAME_PARAM, userName);
	    map.add(LOGGED_PASSWORD_PARAM, password);
	    
	    // Set the Request Body
	    HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
	    
	    // Hit the JWT-AUTH-SERVER to get the JWT Auth Token
	    ResponseEntity<String> responseEntity = new RestTemplate().exchange(url, HttpMethod.POST, requestEntity, String.class);

	    return responseEntity;
	}
	
	public static ResponseEntity<String> invokePaymentService(String url, Map<String, Object> reqParams) {
		HttpHeaders headers = new HttpHeaders();
	    
		// Set 'CONTENT-TYPE' Header
		headers.setContentType(MediaType.APPLICATION_JSON);
	    
		// Set 'ACCEPT' Header
	    List<MediaType> mediaTypeList = new ArrayList<MediaType>();
	    mediaTypeList.add(MediaType.APPLICATION_JSON);
	    headers.setAccept(mediaTypeList);
	    
	    // Set the REQUEST Body
		JSONObject paramInObj = new JSONObject();
		
		reqParams.forEach((key,value) -> paramInObj.put(key, value));
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(paramInObj.toString(), headers);
		
		// Hit the PAYMENT-SERVICE for Customer Creation in Stripe Payment Gateway
	    ResponseEntity<String> responseEntity = new RestTemplate().exchange(url, HttpMethod.POST, requestEntity, String.class);

	    return responseEntity;
	}
	
	public static ResponseEntity<String> invokeNotificationService(String url, Map<String, Object> reqParams) {
		HttpHeaders headers = new HttpHeaders();
	    
		// Set 'CONTENT-TYPE' Header
		headers.setContentType(MediaType.APPLICATION_JSON);
	    
		// Set 'ACCEPT' Header
	    List<MediaType> mediaTypeList = new ArrayList<MediaType>();
	    mediaTypeList.add(MediaType.APPLICATION_JSON);
	    headers.setAccept(mediaTypeList);
	    
	    // Set the REQUEST Body
		JSONObject paramInObj = new JSONObject();
		
		reqParams.forEach((key,value) -> paramInObj.put(key, value));
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(paramInObj.toString(), headers);
		
		// Hit the PAYMENT-SERVICE for Customer Creation in Stripe Payment Gateway
	    ResponseEntity<String> responseEntity = new RestTemplate().exchange(url, HttpMethod.POST, requestEntity, String.class);

	    return responseEntity;
	}
	

}
