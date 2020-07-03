package com.toystore.ecomm.tenants.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toystore.ecomm.tenants.model.Registration;
import com.toystore.ecomm.tenants.model.Registrationresponse;
import com.toystore.ecomm.tenants.model.TenantInfo;
import com.toystore.ecomm.tenants.services.EmailService;
import com.toystore.ecomm.tenants.services.TenantService;
import com.toystore.ecomm.tenants.util.EmailNotificationPreparator;
import com.toystore.ecomm.tenants.util.ResponsePreparator;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-25T11:20:36.509Z")

@Controller
public class RegistrationApiController implements RegistrationApi {

    private static final Logger log = LoggerFactory.getLogger(RegistrationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    TenantService tenantService;
    
    @Autowired
    EmailService emailService;

    @org.springframework.beans.factory.annotation.Autowired
    public RegistrationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> registrationByTenantIdDELETE(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId) {
        //String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Registration> registrationByTenantIdGET(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Registration>(objectMapper.readValue("{  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"}", Registration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Registration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Registration>(HttpStatus.NOT_IMPLEMENTED);
    }

    // NOT REQUIRED
    public ResponseEntity<Registration> registrationByTenantIdPOST(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Registration>(objectMapper.readValue("{  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"}", Registration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Registration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Registration>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Registration> registrationByTenantIdPUT(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Registration body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Registration>(objectMapper.readValue("{  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"}", Registration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Registration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Registration>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Registrationresponse> registrationEmailverificationByTenantIdGET(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId,@NotNull @ApiParam(value = "This is the Verification Code for this tenant", required = true) @Valid @RequestParam(value = "code", required = true) String code) {
    	log.info("registrationEmailverificationByTenantIdGET() invoked");
    	log.debug("registrationEmailverificationByTenantIdGET() invoked with URI Param: " + tenantId + " Query Param: " + code);
    	
    	try {
    			if (tenantService.isTenantExisting(Integer.parseInt(tenantId))) {
		    		if (!tenantService.isTenantVerified(Integer.parseInt(tenantId))) {
				    	if (tenantService.isTenantRegistered(Integer.parseInt(tenantId), code)) {
				    		TenantInfo updatedTenantInfo = tenantService.updateTenantInfo(Integer.parseInt(tenantId));
				    		
				    		emailService.sendNotification(EmailNotificationPreparator.prepareEmailForPostVerification(updatedTenantInfo.getTenantEmail()));
				    		
				    		log.info("registrationEmailverificationByTenantIdGET() exited");
				    		
				    		Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, "Your Registration is Verified", true, null);
				    		
				    		return new ResponseEntity<Registrationresponse>(resp, HttpStatus.OK);
				    	} else {
				    		log.info("registrationEmailverificationByTenantIdGET() exited");
				    		
				    		Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, "Your Verification Code is not correct. Please make sure your Verification Code is correct", false, -1);
				    		
				    		return new ResponseEntity<Registrationresponse>(resp, HttpStatus.BAD_REQUEST);
				    	}
		    		} else {
		    			log.info("registrationEmailverificationByTenantIdGET() exited");
		    			
		    			Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, "Your Registration is Already Verified!!", false, -1);
		    			
		    			return new ResponseEntity<Registrationresponse>(resp, HttpStatus.NOT_ACCEPTABLE);
		    		}
    			} else {
    				log.info("registrationEmailverificationByTenantIdGET() exited");
	    			
	    			Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, "Tenant with ID: " + tenantId + " does not exists. Please check", false, -1);
	    			
	    			return new ResponseEntity<Registrationresponse>(resp, HttpStatus.BAD_REQUEST);
    			}
    	} catch(Exception e) {
    		log.info("registrationEmailverificationByTenantIdGET() exited with error(s)");
    		log.error("Couldn't serialize response for content type application/json", e);
    		
    		Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, "Error - " + e.getMessage(), false, -1);
    		
            return new ResponseEntity<Registrationresponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

    public ResponseEntity<List<Registration>> registrationGET(@ApiParam(value = "Get List of Tenant Info based on a given Tenant Name") @Valid @RequestParam(value = "tenantName", required = false) String tenantName,@ApiParam(value = "Get List of Tenant Info based on a given Tenant Email") @Valid @RequestParam(value = "tenantEmail", required = false) String tenantEmail,@ApiParam(value = "Get List of Tenant Info based on Verified or Not Verified") @Valid @RequestParam(value = "tenantVerified", required = false) String tenantVerified) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {	
                return new ResponseEntity<List<Registration>>(HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Registration>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Registration>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Registrationresponse> registrationPOST(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Registration body) {
    	log.info("registrationPOST() invoked");
    	log.debug("registrationPOST() invoked with Request body: " + body);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	
            	
            	if (!tenantService.isTenantUsernameUnique(body.getTenantUsername())) {
            		log.info("registrationPOST() exited");
            		
            		Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, body.getTenantUsername()  + " Already Exists!!!", false, -1);
            		
            		return new ResponseEntity<Registrationresponse>(resp, HttpStatus.BAD_REQUEST);
            	}
            	if (!tenantService.isTenantEmailUnique(body.getTenantEmail())) {
            		log.info("registrationPOST() exited");
            		
            		Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, body.getTenantEmail()  + " Already Exists!!!", false, -1);
            		return new ResponseEntity<Registrationresponse>(resp, HttpStatus.BAD_REQUEST);
            	}
            	
            	TenantInfo tenantInfo = new TenantInfo();
            	tenantInfo.setTenantName(body.getTenantName());
            	tenantInfo.setTenantEmail(body.getTenantEmail());
            	tenantInfo.setTenantUsername(body.getTenantUsername());
            	tenantInfo.setTenantPassword(body.getTenantPassword());
            	
            	tenantInfo = tenantService.saveTenantInfo(tenantInfo);
            	
            	log.trace("Tenant Info POJO: " + tenantInfo);
            	
            	//Email for Welcome message
            	emailService.sendNotification(EmailNotificationPreparator.prepareEmailForWelcome(tenantInfo.getTenantEmail()));
            	
            	//Email for Registration Verification
            	emailService.sendNotification(EmailNotificationPreparator.prepareEmailForTenantVerification(tenantInfo.getTenantId(), tenantInfo.getTenantEmail(), tenantInfo.getTenantVerificationCode()));
            	
            	// Prepare Response
            	Registrationresponse registrationResponse = ResponsePreparator.prepareRegistrationResponse(tenantInfo.getTenantId(), "The Registration has been created successfully", true, null);
            	
            	log.info("registrationPOST() exited");
            	
                return new ResponseEntity<Registrationresponse>(registrationResponse, HttpStatus.CREATED);
            } catch (Exception e) {
            	log.info("registrationPOST() exited with Errors");
                log.error("Couldn't serialize response for content type application/json", e);
                
                Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, "Error - " + e.getMessage(), false, -1);
        		
                return new ResponseEntity<Registrationresponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        log.info("registrationPOST() exited");
        return new ResponseEntity<Registrationresponse>(HttpStatus.BAD_REQUEST);
    }
}
