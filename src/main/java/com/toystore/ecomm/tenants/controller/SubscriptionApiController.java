package com.toystore.ecomm.tenants.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.model.Subscription;
import com.toystore.ecomm.tenants.model.SubscriptionInfo;
import com.toystore.ecomm.tenants.model.Subscriptionresponse;
import com.toystore.ecomm.tenants.services.SubscriptionService;
import com.toystore.ecomm.tenants.services.TenantService;
import com.toystore.ecomm.tenants.util.ResponsePreparator;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-16T20:08:56.623Z")

@Controller
public class SubscriptionApiController implements SubscriptionApi {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionApiController.class);

    private final HttpServletRequest request;
    
    @Autowired
    private SubscriptionService subscriptionService;
    
    @Autowired
    private TenantService tenantService;

    @org.springframework.beans.factory.annotation.Autowired
    public SubscriptionApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<Subscriptionresponse> subscriptionBySubscriptionIdDELETE(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId) {
    	log.info("subscriptionBySubscriptionIdDELETE() invoked");
    	log.debug("subscriptionBySubscriptionIdDELETE() invoked with URI Param: " + subscriptionId);
    	
    	try {
    		if (subscriptionService.isSubscriptionExisting(Integer.parseInt(subscriptionId))) {
    			subscriptionService.removeSubscription(Integer.parseInt(subscriptionId));
    			
    			// Prepare the Response
    			Subscriptionresponse subscriptionresponse = ResponsePreparator.prepareSubscriptionResponse(Integer.parseInt(subscriptionId), "The Subscription has been deleted successfully", true, null);
    			
    			log.info("subscriptionBySubscriptionIdDELETE() exited");
            	
                return new ResponseEntity<Subscriptionresponse>(subscriptionresponse, HttpStatus.CREATED);
                
    		} else {
    			log.info("subscriptionBySubscriptionIdDELETE() exited - Given Subscription ID: " + subscriptionId + " does not exists");
    			
    			Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Subscription with ID: " + subscriptionId + " does not exists. Please check", false, -1);
    			
    			return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.BAD_REQUEST);
    		}
    	} catch (Exception e) {
    		log.info("subscriptionBySubscriptionIdDELETE() exited with error(s)");
    		
    		Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Server Error - " + e.getMessage(), false, -1);
    		
            return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

    public ResponseEntity<Subscriptionresponse> subscriptionBySubscriptionIdGET(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId) {
    	log.info("subscriptionBySubscriptionIdGET() invoked");
    	log.debug("subscriptionBySubscriptionIdGET() invoked with URI Param: " + subscriptionId);
    	
        try {
        	
        	SubscriptionInfo subscriptionInfo = null;
    		if ((subscriptionInfo = subscriptionService.getSubscriptionById(Integer.parseInt(subscriptionId))) == null) {
    			log.info("subscriptionBySubscriptionIdGET() exited");
        		
    			subscriptionInfo = null;
    			
    			Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Subscription with ID: " + subscriptionId + " does not exists. Please check", false, -1);
    			
    			return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.BAD_REQUEST);
    		}
        	
        	/* Extract Logged User Info - Username & Role - START */
        	
	   		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	   		 Object principal = authentication.getPrincipal();
	   		 Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	   		 
	   		 String tenantUsername = null;
	   		 
	   		 String loggedUserRole = null;
	   		 if (principal instanceof UserDetails) {
	   			 tenantUsername = ((UserDetails)principal).getUsername();
	   		 } else {
	   			 tenantUsername = principal.toString();
	   		 }
	   		
	   		 if (authorities != null && !authorities.isEmpty()) {
	   			 loggedUserRole = ((authorities.iterator().next()).getAuthority()).substring(5);
	   			 
	   		 }
	   		 
	   		 /* Extract Logged User Info - Username & Role - END */
	   		 
	   		List<SubscriptionInfo> subscriptionInfoList = new ArrayList<SubscriptionInfo>(1);
	   		subscriptionInfo = null;
	   		if (loggedUserRole.equalsIgnoreCase(PTMSConstants.TENANT_ADMIN_ROLE_NAME)) {
   			 
		   		ListIterator<SubscriptionInfo> listIterSubscriptionInfo = subscriptionService.getSubscriptionsByTenantName(tenantService.getTenantInfoByUsername(tenantUsername).getTenantName()).listIterator();
		   		
		   		boolean isSubscriptionAccessible = false;
		   		while (listIterSubscriptionInfo.hasNext()) {
		   			subscriptionInfo = listIterSubscriptionInfo.next();
		   			
		   			if (subscriptionInfo.getSubscriptionId() == Integer.parseInt(subscriptionId)) {
		   				isSubscriptionAccessible = true;
		   				break;
		   			}
		   		}
		   		
		   		if (!isSubscriptionAccessible) {
		   			log.info("subscriptionBySubscriptionIdGET() exited - Given Subscription Id is not accessible!!!!");
		   			
		   			Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Subscription ID: " + subscriptionId + " is not accessible!!", false, -1);
	        		
	    			return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.FORBIDDEN);
		   		}
	   		} 
	   		
	   		if (loggedUserRole.equalsIgnoreCase(PTMSConstants.TENANT_USER_ROLE_NAME)) {
	   			subscriptionInfo = subscriptionService.getSubscriptionsByTenantUserNameValidity(tenantUsername, PTMSConstants.YES_VALUE).get(0);
	   			Integer loggedUserSubscriptionId = subscriptionInfo.getSubscriptionId();
	   			
	   			if (loggedUserSubscriptionId != Integer.parseInt(subscriptionId)) {
	   				log.info("subscriptionBySubscriptionIdGET() exited - Given Subscription Id is not accessible!!!!");
	   				
	   				Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Subscription ID: " + subscriptionId + " is not accessible!!", false, -1);
	        		
	    			return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.FORBIDDEN);
	   			}
	   		}
	   		
	   		subscriptionInfoList.add(subscriptionInfo);
	   		
	   		log.info("subscriptionBySubscriptionIdGET() exited");
	   		
	   		Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(subscriptionInfoList, "The Subscription has been fetched Successfully", true, null);
	   		
    		return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.OK);
	   		 
        } catch (Exception e) {
        	log.info("subscriptionBySubscriptionIdGET() exited with Errors");
            log.error("Couldn't serialize response for content type application/json", e);
            
            Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Server Error - " + e.getMessage(), false, -1);
            
            return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // NOT REQUIRED
	
	/*
	 * public ResponseEntity<Subscription>
	 * subscriptionBySubscriptionIdPOST(@ApiParam(value =
	 * "",required=true) @PathVariable("subscriptionId") String subscriptionId) {
	 * String accept = request.getHeader("Accept"); if (accept != null &&
	 * accept.contains("application/json")) { try { return new
	 * ResponseEntity<Subscription>(objectMapper.
	 * readValue("{  \"endDate\" : \"endDate\",  \"renewalType\" : \"renewalType\",  \"isValid\" : \"isValid\",  \"planName\" : \"planName\",  \"startDate\" : \"startDate\"}"
	 * , Subscription.class), HttpStatus.NOT_IMPLEMENTED); } catch (IOException e) {
	 * log.error("Couldn't serialize response for content type application/json",
	 * e); return new
	 * ResponseEntity<Subscription>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 * 
	 * 
	 * return new ResponseEntity<Subscription>(HttpStatus.NOT_IMPLEMENTED); }
	 */

    public ResponseEntity<Subscriptionresponse> subscriptionBySubscriptionIdPUT(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Subscription body) {
    	log.info("subscriptionBySubscriptionIdPUT() invoked");
    	log.debug("subscriptionBySubscriptionIdPUT() invoked with URI Param: " + subscriptionId);
    	
    	String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	SubscriptionInfo subscriptionInfo = null;
        		if ((subscriptionInfo = subscriptionService.getSubscriptionById(Integer.parseInt(subscriptionId))) == null) {
        			log.info("subscriptionBySubscriptionIdPUT() exited");
            		
        			subscriptionInfo = null;
        			
        			Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Subscription with ID: " + subscriptionId + " does not exists. Please check", false, -1);
        			
        			return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.BAD_REQUEST);
        		}
            	
            	/* Extract Logged User Info - Username & Role - START */
            	
		   		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   		 Object principal = authentication.getPrincipal();
		   		 Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		   		 
		   		 String tenantUsername = null;
		   		 
		   		 String loggedUserRole = null;
		   		 if (principal instanceof UserDetails) {
		   			 tenantUsername = ((UserDetails)principal).getUsername();
		   		 } else {
		   			 tenantUsername = principal.toString();
		   		 }
		   		
		   		 if (authorities != null && !authorities.isEmpty()) {
		   			 loggedUserRole = ((authorities.iterator().next()).getAuthority()).substring(5);
		   			 
		   		 }
		   		 
		   		 /* Extract Logged User Info - Username & Role - END */
		   		 
		   		if (loggedUserRole.equalsIgnoreCase(PTMSConstants.TENANT_ADMIN_ROLE_NAME)) {
		   			
		   			ListIterator<SubscriptionInfo> listIterSubscriptionInfo = subscriptionService.getSubscriptionsByTenantName(tenantService.getTenantInfoByUsername(tenantUsername).getTenantName()).listIterator();
			   		
			   		boolean isSubscriptionAccessible = false;
			   		while (listIterSubscriptionInfo.hasNext()) {
			   			subscriptionInfo = listIterSubscriptionInfo.next();
			   			
			   			if (subscriptionInfo.getSubscriptionId() == Integer.parseInt(subscriptionId)) {
			   				isSubscriptionAccessible = true;
			   				break;
			   			}
			   		}
			   		
			   		if (!isSubscriptionAccessible) {
			   			log.info("subscriptionBySubscriptionIdPUT() exited - Given Subscription Id is not accessible & so the Subscription cannot be modified!!!!");
			   			
			   			Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Subscription ID: " + subscriptionId + " is not accessible & so the Subscription cannot be modified!!", false, -1);
		        		
		    			return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.FORBIDDEN);
			   		}
		   			
            		subscriptionInfo.setPlanTypeId(Integer.parseInt(body.getPlanName()));
            		subscriptionInfo.setRenewalTypeId(Integer.parseInt(body.getRenewalType()));
            		
            		subscriptionService.updateSubscriptionInfo(subscriptionInfo);
            		
            		log.trace("Updated Subscription Info POJO: " + subscriptionInfo);
            		
            		Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(Integer.parseInt(subscriptionId), "The Subscription has been updated Successfully", true, null);
    		   		
            		log.info("subscriptionBySubscriptionIdPUT() exited");
            		
            		return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.CREATED);
		   		}
		   		
            } catch (Exception e) {
            	log.info("subscriptionBySubscriptionIdPUT() exited with Errors");
                log.error("Couldn't serialize response for content type application/json", e);
                
                Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Server Error - " + e.getMessage(), false, -1);
                
                return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "ACCEPT header is required", false, -1);
        
        return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Subscriptionresponse> subscriptionGET(@ApiParam(value = "Get Subscriptions for a given Tenant Name") @Valid @RequestParam(value = "tenantName", required = false) String tenantName,@ApiParam(value = "Get Subscriptions for a given Plan Name") @Valid @RequestParam(value = "planName", required = false) String planName,@ApiParam(value = "Get all Valid or Invalid Subscriptions") @Valid @RequestParam(value = "isValid", required = false) String isValid) {
    	log.info("subscriptionGET() invoked");
    	
    	String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
	            	/* Extract Logged User Info - Username & Role - START */
	            	
			   		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			   		Object principal = authentication.getPrincipal();
			   		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			   		 
			   		String tenantUsername = null;
			   		 
			   		String loggedUserRole = null;
			   		if (principal instanceof UserDetails) {
			   			tenantUsername = ((UserDetails)principal).getUsername();
			   		} else {
			   			tenantUsername = principal.toString();
			   		}
			   		
			   		if (authorities != null && !authorities.isEmpty()) {
			   			loggedUserRole = ((authorities.iterator().next()).getAuthority()).substring(5);
			   		}
			   		 
			   		 /* Extract Logged User Info - Username & Role - END */
		       		
		       		if (loggedUserRole.equalsIgnoreCase(PTMSConstants.TENANT_USER_ROLE_NAME)) {
	        			log.info("subscriptionGET() exited - NOT ACCESSIBLE!!!");
	             		
	        			Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Logged User do not have access!!", false, -1);
		        		
		    			return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.FORBIDDEN);
	        		}
		       		
		       		List<SubscriptionInfo> subscriptionInfoList = null;
		       		
		       		if (tenantName == null) tenantName = PTMSConstants.BLANK_STRING;
	        		if (planName == null) planName = PTMSConstants.BLANK_STRING;
	        		if (isValid == null) isValid = PTMSConstants.BLANK_STRING;
		       		
		       		if (loggedUserRole.equalsIgnoreCase(PTMSConstants.TENANT_ADMIN_ROLE_NAME)) {
		       			String fetchedTenantName = tenantService.getTenantInfoByUsername(tenantUsername).getTenantName();
		            	
		       			// Search by any 1 parameter with Tenant Name as Static
		       			if (!planName.equals(PTMSConstants.BLANK_STRING) && isValid.equals(PTMSConstants.BLANK_STRING)) {
		       				subscriptionInfoList = subscriptionService.getSubscriptionsByTenantNamePlanName(fetchedTenantName, planName);
		        		} 
		       			else if (planName.equals(PTMSConstants.BLANK_STRING) && !isValid.equals(PTMSConstants.BLANK_STRING)) {
		        			subscriptionInfoList = subscriptionService.getSubscriptionsByTenantNameValidity(fetchedTenantName, isValid);
		        		}
		       			
		       			// Search by all 2 parameters with Tenant Name as Static
		        		else if (!planName.equals(PTMSConstants.BLANK_STRING) && !isValid.equals(PTMSConstants.BLANK_STRING)) {
		        			subscriptionInfoList = subscriptionService.getSubscriptionsByTenantNamePlanNameValidity(fetchedTenantName, planName, isValid);
		        		}
		       			
		        		else {
		        			subscriptionInfoList = subscriptionService.getSubscriptionsByTenantName(fetchedTenantName);
		        		}
		       			
		       		} else {
		       			
		       			// Search by any one parameter
		        		if (!tenantName.equals(PTMSConstants.BLANK_STRING) && planName.equals(PTMSConstants.BLANK_STRING) && isValid.equals(PTMSConstants.BLANK_STRING)) {
		        			subscriptionInfoList = subscriptionService.getSubscriptionsByTenantName(tenantName);
		        		}
		        		else if (tenantName.equals(PTMSConstants.BLANK_STRING) && !planName.equals(PTMSConstants.BLANK_STRING) && isValid.equals(PTMSConstants.BLANK_STRING)) {
		        			subscriptionInfoList = subscriptionService.getSubscriptionsByPlanName(planName);
		        		}
		        		else if (tenantName.equals(PTMSConstants.BLANK_STRING) && planName.equals(PTMSConstants.BLANK_STRING) && !isValid.equals(PTMSConstants.BLANK_STRING)) {
		        			subscriptionInfoList = subscriptionService.getSubscriptionsByValidity(isValid);
		        		}
		        		
		        		// Search by any 2 parameters
		        		else if (!tenantName.equals(PTMSConstants.BLANK_STRING) && !planName.equals(PTMSConstants.BLANK_STRING) && isValid.equals(PTMSConstants.BLANK_STRING)) {
		        			subscriptionInfoList = subscriptionService.getSubscriptionsByTenantNamePlanName(tenantName, planName);
		        		}
		        		else if (!tenantName.equals(PTMSConstants.BLANK_STRING) && planName.equals(PTMSConstants.BLANK_STRING) && !isValid.equals(PTMSConstants.BLANK_STRING)) {
		        			subscriptionInfoList = subscriptionService.getSubscriptionsByTenantNameValidity(tenantName, isValid);
		        		}
		        		else if (tenantName.equals(PTMSConstants.BLANK_STRING) && !planName.equals(PTMSConstants.BLANK_STRING) && !isValid.equals(PTMSConstants.BLANK_STRING)) {
		        			subscriptionInfoList = subscriptionService.getSubscriptionsByPlanNameValidity(planName, isValid);
		        		}
		        		
		        		// Search by all 3 parameters
		        		else if (!tenantName.equals(PTMSConstants.BLANK_STRING) && !planName.equals(PTMSConstants.BLANK_STRING) && !isValid.equals(PTMSConstants.BLANK_STRING)) {
		        			subscriptionInfoList = subscriptionService.getSubscriptionsByTenantNamePlanNameValidity(tenantName, planName, isValid);
		        		}
		        		
		        		else {
		        			subscriptionInfoList = subscriptionService.getAllSubscriptions();
		        		}
		       		}
		            	
		       		if (!subscriptionInfoList.isEmpty()) {
		       			log.info("subscriptionGET() exited");
		       			
		       			Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(subscriptionInfoList, "Subscription List has been fetched Successfully", true, null);
	             		
	             		return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.OK);
	             	} else {
	             		log.info("subscriptionGET() exited - No Subscription(s) for the selected criteria");
	             		
	             		Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "No Subscription(s) found for the selected criteria", false, -1);
	             		
	         			return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.BAD_REQUEST);
	             	}
	            	
            } catch (Exception e) {
            	log.info("subscriptionGET() exited with error(s)");
         		log.error("Couldn't serialize response for content type application/json", e);
         		
         		Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Server Error - " + e.getMessage(), false, -1);
         		
                return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "ACCEPT header is required", false, -1);
        
        return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.BAD_REQUEST);
    }

    
    public ResponseEntity<Subscriptionresponse> subscriptionPOST(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Subscription body) {
    	log.info("subscriptionPOST() invoked");
    	
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	/* Extract Logged User Info - Username - START */
            	
		   		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   		 Object principal = authentication.getPrincipal();
		   		 
		   		 String tenantUsername = null;
		   		 
		   		 if (principal instanceof UserDetails) {
		   			 tenantUsername = ((UserDetails)principal).getUsername();
		   		 } else {
		   			 tenantUsername = principal.toString();
		   		 }
		   		 
		   		 /* Extract Logged User Info - Username - END */
		   		 
                SubscriptionInfo subscriptionInfo = new SubscriptionInfo();
                
                subscriptionInfo.setTenantId(tenantService.getTenantInfoByUsername(tenantUsername).getTenantId());
                subscriptionInfo.setPlanTypeId(Integer.parseInt(body.getPlanName()));
                subscriptionInfo.setRenewalTypeId(Integer.parseInt(body.getRenewalType()));
                
                subscriptionService.saveSubscriptionInfo(subscriptionInfo);
                
                log.trace("Created Subscription Info POJO: " + subscriptionInfo);
                
                Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(subscriptionInfo.getSubscriptionId(), "The Subscription has been created Successfully", true, null);
		   		
        		log.info("subscriptionPOST() exited");
        		
        		return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.CREATED);
                
            } catch (Exception e) {
            	log.info("subscriptionPOST() exited with Errors");
                log.error("Couldn't serialize response for content type application/json", e);
                
                Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "Server Error - " + e.getMessage(), false, -1);
                
                return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        Subscriptionresponse resp = ResponsePreparator.prepareSubscriptionResponse(null, "ACCEPT header is required", false, -1);
        
        return new ResponseEntity<Subscriptionresponse>(resp, HttpStatus.BAD_REQUEST);
    }

}
