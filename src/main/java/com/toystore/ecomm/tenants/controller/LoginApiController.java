package com.toystore.ecomm.tenants.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.model.Data2;
import com.toystore.ecomm.tenants.model.Login;
import com.toystore.ecomm.tenants.model.Loginresponse;
import com.toystore.ecomm.tenants.model.TenantInfo;
import com.toystore.ecomm.tenants.services.TenantService;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-16T20:08:56.623Z")

@RestController
public class LoginApiController implements LoginApi {

	private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;
	
	
	 @Autowired
	 TenantService tenantService;
	 

	@org.springframework.beans.factory.annotation.Autowired
	public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<Void> loginDELETE() {
		String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<Login> loginGET() {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<Login>(
						objectMapper.readValue("{  \"userPassword\" : \"userPassword\",  \"userName\" : \"userName\"}",
								Login.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<Login>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Login>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<Loginresponse> loginPOST(@ApiParam(value = "", required = true) @Valid @RequestBody Login body) throws JsonMappingException, JsonProcessingException {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				if ((body.getUserName() == null || PTMSConstants.BLANK_STRING.equals(body.getUserName())) || (body.getUserPassword() == null || PTMSConstants.BLANK_STRING.equals(body.getUserPassword()))) {
					return new ResponseEntity<Loginresponse>(objectMapper.readValue("{  \"message\" : \"Both 'userName' & 'userPassword' are required\"}", Loginresponse.class),
													 HttpStatus.BAD_REQUEST
													);
				}
				
				//Get currently logged-in Tenant's DB Info
				TenantInfo tenantInfo = tenantService.getTenantInfoByUsername(body.getUserName());
				
				if (tenantInfo == null) {
					return new ResponseEntity<Loginresponse>(objectMapper.readValue("{  \"message\" : \"Entered 'userName' is invalid. Please enter correct one\"}", 
															 Loginresponse.class),
							 								 HttpStatus.BAD_REQUEST
															);
				}
				
				/*
				 * loadCurrentDatabaseInstance(tenantInfo.getTenantDBInfo().getTenantDBName(),
				 * tenantInfo.getTenantUsername());
				 * 
				 * final Authentication authentication = authenticationManager.authenticate(new
				 * UsernamePasswordAuthenticationToken(body.getUserName(),
				 * body.getUserPassword()));
				 * SecurityContextHolder.getContext().setAuthentication(authentication);
				 * UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				 */
		        
		        String authServerResp = invokeAuthServer(body.getUserName(), body.getUserPassword());
		        System.out.println("Response from Auth Server: " + authServerResp);
		        
		        JSONObject obj = new JSONObject(authServerResp);
		        String jwtToken = obj.getString("access_token");
		        
				
				/*
				 * return new ResponseEntity<Loginresponse>(objectMapper.
				 * readValue("{ \"message\" : \"User with username - " +
				 * userDetails.getUsername() + " successfully logged-in.\"}",
				 * Loginresponse.class), HttpStatus.OK);
				 */
		        
		        String loginSuccessMsg = "User with username - " + body.getUserName() + " successfully logged-in.";
		        Loginresponse resp = prepareLoginResponse(jwtToken, loginSuccessMsg, true, null);

				return new ResponseEntity<Loginresponse>(resp, HttpStatus.OK);
			} /*
				 * cat	ch(AuthenticationException ae) { log.error("Authentication failed: " +
				 * ae.getMessage());
				 * 
				 * return new ResponseEntity<Loginresponse>(objectMapper.
				 * readValue("{  \"message\" : \"Authentication Failure\"}",
				 * Loginresponse.class), HttpStatus.UNAUTHORIZED); }
				 */
			catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<Loginresponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Loginresponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<Login> loginPUT(@ApiParam(value = "", required = true) @Valid @RequestBody Login body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<Login>(
						objectMapper.readValue("{  \"userPassword\" : \"userPassword\",  \"userName\" : \"userName\"}",
								Login.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<Login>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Login>(HttpStatus.NOT_IMPLEMENTED);
	}

	
	private String invokeAuthServer(String userName, String password) {
		String result = null;
		
		String url = "http://localhost:8901/auth/oauth/token";
		
	    // create auth credentials
	    String authStr = "eagleeye:thisissecret";
	    String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

	    // create headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "Basic " + base64Creds);
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    
	    LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
	    map.add("grant_type", "password");
	    map.add("scope", "webclient");
	    map.add("username", userName);
	    map.add("password", password);
	    
	    HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
	    
	    ResponseEntity<String> responseEntity = new RestTemplate().exchange(url, HttpMethod.POST, requestEntity, String.class);

	    HttpStatus statusCode = responseEntity.getStatusCode();
	    
	    if (statusCode == HttpStatus.OK) {
	        result = responseEntity.getBody();
	    }
	    
	    return result;
	}
	
	private Loginresponse prepareLoginResponse(Object dataInfo, String msg, boolean isSuccess, Integer errorCode) {
		Data2 data = new Data2();
		data.setJwttoken(dataInfo.toString());;
		
		Loginresponse loginResponse = new Loginresponse();
		
		loginResponse.setData(data);
		loginResponse.setErrorCode(errorCode);
		loginResponse.setMessage(msg);
		//registrationResponse.setMessage("The Registration has been created successfully");
		loginResponse.setSuccess(isSuccess);
		return loginResponse;
	}
}
