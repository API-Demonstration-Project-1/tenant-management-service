package com.toystore.ecomm.tenants.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.model.Login;
import com.toystore.ecomm.tenants.model.Loginresponse;
import com.toystore.ecomm.tenants.model.TenantInfo;
import com.toystore.ecomm.tenants.services.TenantService;
import com.toystore.ecomm.tenants.util.ResponsePreparator;
import com.toystore.ecomm.tenants.util.ServiceInvoker;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-16T20:08:56.623Z")

@RestController
public class LoginApiController implements LoginApi {

	private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;
	
	@Value("${service.auth.jwttype.url}")
	private String authSrvcUrl;
	
	@Autowired
	private TenantService tenantService;

	@org.springframework.beans.factory.annotation.Autowired
	public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	// NOT REQUIRED
	public ResponseEntity<Void> loginDELETE() {
		//String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	// NOT REQUIRED
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
				if ((body.getUserName() == null || PTMSConstants.BLANK_STRING.equals(body.getUserName())) || 
				    (body.getUserPassword() == null || PTMSConstants.BLANK_STRING.equals(body.getUserPassword()))) {
					log.error("loginPOST() - Credentials are blank/null");
					
					Loginresponse resp = ResponsePreparator.prepareLoginResponse(null, "Error - " + "Credentials cannot be blank/null", false, -1);
					
					return new ResponseEntity<Loginresponse>(resp, HttpStatus.BAD_REQUEST);
				}
				
				TenantInfo tenantInfo = tenantService.getTenantInfoByUsername(body.getUserName());
				
				if (tenantInfo == null) {
					log.error("loginPOST() - Username is invalid");
					
					Loginresponse resp = ResponsePreparator.prepareLoginResponse(null, "Entered 'userName' is invalid. Please enter correct one", false, -1);
					
					return new ResponseEntity<Loginresponse>(resp, HttpStatus.BAD_REQUEST);
				}
				
				if (tenantInfo.getTenantVerified().equalsIgnoreCase(PTMSConstants.NO_VALUE)) {
					log.error("loginPOST() - Registration is Not Verified!!");
					
					Loginresponse resp = ResponsePreparator.prepareLoginResponse(null, "Tenant's Registration is not Verified yet", false, -1);
					
					return new ResponseEntity<Loginresponse>(resp, HttpStatus.FORBIDDEN);
				}
		        
				ResponseEntity<String> authServerResp = ServiceInvoker.invokeAuthServer(authSrvcUrl, body.getUserName(), body.getUserPassword());
				String respStr = authServerResp.getBody();
				HttpStatus httpStatus = authServerResp.getStatusCode();
				
				if (httpStatus == HttpStatus.OK) {
					log.info("loginPOST() - Response from Auth Server: " + respStr);
			        
					String jwtToken = (new JSONObject(respStr)).getString(PTMSConstants.ACCESS_TOKEN_FIELD);
			        String loginSuccessMsg = "User with username - " + body.getUserName() + " successfully logged-in.";
			        
			        Loginresponse resp = ResponsePreparator.prepareLoginResponse(jwtToken, loginSuccessMsg, true, null);

			        return new ResponseEntity<Loginresponse>(resp, HttpStatus.OK);
				} else {
					log.error("loginPOST() - Error Response from Auth Server: " + respStr);
					
					String errorDesc = (new JSONObject(respStr)).getString(PTMSConstants.ERROR_DESC_FIELD);
					Loginresponse resp = ResponsePreparator.prepareLoginResponse(null, "Error - " + errorDesc, false, -1);

			        return new ResponseEntity<Loginresponse>(resp, httpStatus);
				}
			} catch (HttpClientErrorException hce) {
				log.error("loginPOST() - Error Response from Auth Server: " + hce.getLocalizedMessage());
				
				Loginresponse resp = ResponsePreparator.prepareLoginResponse(null, "Error - " + hce.getStatusCode().getReasonPhrase(), false, -1);

		        return new ResponseEntity<Loginresponse>(resp, hce.getStatusCode());
				
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				
				Loginresponse resp = ResponsePreparator.prepareLoginResponse(null, "Error - " + e.getMessage(), false, -1);
				
				return new ResponseEntity<Loginresponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Loginresponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	// NOT REQUIRED
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
	
}
