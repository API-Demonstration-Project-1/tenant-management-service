package com.toystore.ecomm.tenants.controller;

import java.util.ArrayList;
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

import com.toystore.ecomm.tenants.constants.PTMSConstants;
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

    private final HttpServletRequest request;
    
    @Autowired
    private TenantService tenantService;
    
    @Autowired
    private EmailService emailService;
    

    @org.springframework.beans.factory.annotation.Autowired
    public RegistrationApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<Registrationresponse> registrationByTenantIdDELETE(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId) {
    	log.info("registrationByTenantIdDELETE() invoked");
    	log.debug("registrationByTenantIdDELETE() invoked with URI Param: " + tenantId);
    	
    	try {
    		if (tenantService.isTenantExisting(Integer.parseInt(tenantId))) {
    			tenantService.removeTenantInfo(Integer.parseInt(tenantId));
    			
    			// Prepare Response
            	Registrationresponse registrationResponse = ResponsePreparator.prepareRegistrationResponse(Integer.parseInt(tenantId), "The Registration has been deleted successfully", true, null);
            	
            	log.info("registrationByTenantIdDELETE() exited");
            	
                return new ResponseEntity<Registrationresponse>(registrationResponse, HttpStatus.CREATED);
                
    		} else {
    			log.info("registrationByTenantIdDELETE() exited");
    			
    			Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, "Tenant with ID: " + tenantId + " does not exists. Please check", false, -1);
    			
    			return new ResponseEntity<Registrationresponse>(resp, HttpStatus.BAD_REQUEST);
    		}
    		
    	} catch (Exception e) {
    		log.info("registrationByTenantIdDELETE() exited with error(s)");
    		log.error("Couldn't serialize response for content type application/json", e);
    		
    		Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, "Error - " + e.getMessage(), false, -1);
    		
            return new ResponseEntity<Registrationresponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

    public ResponseEntity<Registration> registrationByTenantIdGET(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId) {
    	log.info("registrationByTenantIdGET() invoked");
    	log.debug("registrationByTenantIdGET() invoked with URI Param: " + tenantId);
    	
        try {
        	TenantInfo tenantInfo = null;
        	if ((tenantInfo = tenantService.getTenantInfoByTenantId(Integer.parseInt(tenantId))) != null) {
        		List<TenantInfo> tenantInfoList = new ArrayList<TenantInfo>();
        		tenantInfoList.add(tenantInfo);
        		
        		return new ResponseEntity<Registration>((ResponsePreparator.prepareGETRegistrationResponse(tenantInfoList)).get(0), HttpStatus.OK);
        	} else {
        		log.info("registrationByTenantIdGET() exited");
        		
    			return new ResponseEntity<Registration>(HttpStatus.BAD_REQUEST);
        	}
        } catch (Exception e) {
        	log.info("registrationByTenantIdGET() exited with error(s)");
    		log.error("Couldn't serialize response for content type application/json", e);
    		
            return new ResponseEntity<Registration>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // NOT REQUIRED
	/*
	 * public ResponseEntity<Registration>
	 * registrationByTenantIdPOST(@ApiParam(value =
	 * "",required=true) @PathVariable("tenantId") String tenantId) { String accept
	 * = request.getHeader("Accept"); if (accept != null &&
	 * accept.contains("application/json")) { try { return new
	 * ResponseEntity<Registration>(objectMapper.
	 * readValue("{  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"}"
	 * , Registration.class), HttpStatus.NOT_IMPLEMENTED); } catch (IOException e) {
	 * log.error("Couldn't serialize response for content type application/json",
	 * e); return new
	 * ResponseEntity<Registration>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 * 
	 * return new ResponseEntity<Registration>(HttpStatus.NOT_IMPLEMENTED); }
	 */

    public ResponseEntity<Registrationresponse> registrationByTenantIdPUT(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Registration body) {
    	log.info("registrationByTenantIdPUT() invoked");
    	log.debug("registrationByTenantIdPUT() invoked with URI Param: " + tenantId);
    	
    	String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	TenantInfo tenantInfo = null;
            	
            	if ((tenantInfo = tenantService.getTenantInfoByTenantId(Integer.parseInt(tenantId))) != null) {
            		tenantInfo.setTenantName(body.getTenantName());
                	tenantInfo.setTenantEmail(body.getTenantEmail());
                	tenantInfo.setTenantUsername(body.getTenantUsername());
                	tenantInfo.setTenantPassword(body.getTenantPassword());
                	
                	tenantInfo = tenantService.saveTenantInfo(tenantInfo);
                	
                	log.trace("Updated Tenant Info POJO: " + tenantInfo);
                	
                	// Prepare Response
                	Registrationresponse registrationResponse = ResponsePreparator.prepareRegistrationResponse(Integer.parseInt(tenantId), "The Registration has been updated successfully", true, null);
                	
                	log.info("registrationByTenantIdPUT() exited");
                	
                    return new ResponseEntity<Registrationresponse>(registrationResponse, HttpStatus.CREATED);
            		
            	} else {
            		log.info("registrationByTenantIdPUT() exited");
        			
        			Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, "Tenant with ID: " + tenantId + " does not exists. Please check", false, -1);
        			
        			return new ResponseEntity<Registrationresponse>(resp, HttpStatus.BAD_REQUEST);
            	}
            } catch (Exception e) {
            	log.info("registrationByTenantIdPUT() exited with error(s)");
        		log.error("Couldn't serialize response for content type application/json", e);
        		
        		Registrationresponse resp = ResponsePreparator.prepareRegistrationResponse(null, "Error - " + e.getMessage(), false, -1);
        		
                return new ResponseEntity<Registrationresponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Registrationresponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Registrationresponse> registrationEmailverificationByTenantIdGET(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId,@NotNull @ApiParam(value = "This is the Verification Code for this tenant", required = true) @Valid @RequestParam(value = "code", required = true) String code) {
    	log.info("registrationEmailverificationByTenantIdGET() invoked");
    	log.debug("registrationEmailverificationByTenantIdGET() invoked with URI Param: " + tenantId + " Query Param: " + code);
    	
    	try {
    			if (tenantService.isTenantExisting(Integer.parseInt(tenantId))) {
		    		if (!tenantService.isTenantVerified(Integer.parseInt(tenantId))) {
				    	if (tenantService.isTenantRegistered(Integer.parseInt(tenantId), code)) {
				    		TenantInfo updatedTenantInfo = tenantService.updateTenantInfoPostVerification(Integer.parseInt(tenantId));
				    		
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
    	log.info("registrationGET() invoked");
    	
    	String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
        	 try {
        		 List<TenantInfo> tenantInfoList = null;
        		 
        		if (tenantName == null) tenantName = PTMSConstants.BLANK_STRING;
        		if (tenantEmail == null) tenantEmail = PTMSConstants.BLANK_STRING;
        		if (tenantVerified == null) tenantVerified = PTMSConstants.BLANK_STRING;
        		
        		// Search by any one parameter
        		if (!tenantName.equals(PTMSConstants.BLANK_STRING) && tenantEmail.equals(PTMSConstants.BLANK_STRING) && tenantVerified.equals(PTMSConstants.BLANK_STRING)) {
        			tenantInfoList = tenantService.getTenantInfoByName(tenantName);
        		} else if (tenantName.equals(PTMSConstants.BLANK_STRING) && !tenantEmail.equals(PTMSConstants.BLANK_STRING) && tenantVerified.equals(PTMSConstants.BLANK_STRING)) {
        			tenantInfoList = tenantService.getTenantInfoByEmail(tenantEmail);
        		} else if (tenantName.equals(PTMSConstants.BLANK_STRING) && tenantEmail.equals(PTMSConstants.BLANK_STRING) && !tenantVerified.equals(PTMSConstants.BLANK_STRING)) {
        			tenantInfoList = tenantService.getTenantInfoByVerification(tenantVerified);
        		}
        		
        		// Search by any 2 parameters
        		else if (!tenantName.equals(PTMSConstants.BLANK_STRING) && !tenantEmail.equals(PTMSConstants.BLANK_STRING) && tenantVerified.equals(PTMSConstants.BLANK_STRING)) {
        			tenantInfoList = tenantService.getTenantInfoByNameEmail(tenantName, tenantEmail);
        		} else if (!tenantName.equals(PTMSConstants.BLANK_STRING) && tenantEmail.equals(PTMSConstants.BLANK_STRING) && !tenantVerified.equals(PTMSConstants.BLANK_STRING)) {
        			tenantInfoList = tenantService.getTenantInfoByNameVerification(tenantName, tenantVerified);
        		} else if (tenantName.equals(PTMSConstants.BLANK_STRING) && !tenantEmail.equals(PTMSConstants.BLANK_STRING) && !tenantVerified.equals(PTMSConstants.BLANK_STRING)) {
        			tenantInfoList = tenantService.getTenantInfoByEmailVerification(tenantEmail, tenantVerified);
        		}
        		
        		// Search by all 3 parameters
        		else if (!tenantName.equals(PTMSConstants.BLANK_STRING) && !tenantEmail.equals(PTMSConstants.BLANK_STRING) && !tenantVerified.equals(PTMSConstants.BLANK_STRING)) {
        			tenantInfoList = tenantService.getTenantInfoByNameEmailVerification(tenantName, tenantEmail, tenantVerified);
        		}
        		
        		// Search by none - Fetch All Tenants
        		else {
        			tenantInfoList = tenantService.getAllTenantInfo();
        		}
        		
             	if (!tenantInfoList.isEmpty()) {
             		
             		return new ResponseEntity<List<Registration>>(ResponsePreparator.prepareGETRegistrationResponse(tenantInfoList), HttpStatus.OK);
             	} else {
             		log.info("registrationGET() exited");
             		
         			return new ResponseEntity<List<Registration>>(new ArrayList<Registration>(0), HttpStatus.BAD_REQUEST);
             	}
             } catch (Exception e) {
             	log.info("registrationGET() exited with error(s)");
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
            	
            	log.trace("Created Tenant Info POJO: " + tenantInfo);
            	
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
