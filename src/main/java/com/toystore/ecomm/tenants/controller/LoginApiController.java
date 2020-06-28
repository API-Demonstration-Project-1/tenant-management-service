package com.toystore.ecomm.tenants.controller;

import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.model.Login;
import com.toystore.ecomm.tenants.model.Loginresponse;
import com.toystore.ecomm.tenants.model.TenantInfo;
import com.toystore.ecomm.tenants.services.TenantService;
import com.toystore.ecomm.tenants.config.DBContextHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-16T20:08:56.623Z")

@Controller
public class LoginApiController implements LoginApi {

	private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;
	
	 private Map<String, String> mapValue = new HashMap<>();
	 private Map<String, String> userDbMap = new HashMap<>();
	
	 @Autowired
	 TenantService tenantService;
	 
	 @Autowired
	 private AuthenticationManager authenticationManager;

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
				
				loadCurrentDatabaseInstance(tenantInfo.getTenantDBInfo().getTenantDBName(), tenantInfo.getTenantUsername());
				
				final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.getUserName(), body.getUserPassword()));
		        SecurityContextHolder.getContext().setAuthentication(authentication);
		        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				
				return new ResponseEntity<Loginresponse>(objectMapper.readValue("{ \"message\" : \"User with username - " + userDetails.getUsername() + " successfully logged-in.\"}",
														 Loginresponse.class),
														 HttpStatus.OK);
			} catch(AuthenticationException ae) {
				log.error("Authentication failed: " + ae.getMessage());
				
				return new ResponseEntity<Loginresponse>(objectMapper.readValue("{  \"message\" : \"Authentication Failure\"}", 
														 Loginresponse.class),
						 						 		 HttpStatus.UNAUTHORIZED);
			}
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

	private void loadCurrentDatabaseInstance(String databaseName, String userName) {
        DBContextHolder.setCurrentDb(databaseName);
        mapValue.put(userName, databaseName);
    }
}
