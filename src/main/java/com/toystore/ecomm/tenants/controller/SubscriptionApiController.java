package com.toystore.ecomm.tenants.controller;

import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.model.Subscription;
import com.toystore.ecomm.tenants.model.SubscriptionInfo;
import com.toystore.ecomm.tenants.model.Subscriptionresponse;
import com.toystore.ecomm.tenants.model.TenantInfo;
import com.toystore.ecomm.tenants.services.SubscriptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-16T20:08:56.623Z")

@Controller
public class SubscriptionApiController implements SubscriptionApi {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private SubscriptionService subscriptionService;

    @org.springframework.beans.factory.annotation.Autowired
    public SubscriptionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> subscriptionBySubscriptionIdDELETE(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Subscription> subscriptionBySubscriptionIdGET(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Subscription>(objectMapper.readValue("{  \"endDate\" : \"endDate\",  \"renewalType\" : \"renewalType\",  \"isValid\" : \"isValid\",  \"planName\" : \"planName\",  \"startDate\" : \"startDate\"}", Subscription.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Subscription>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Subscription>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Subscription> subscriptionBySubscriptionIdPOST(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Subscription>(objectMapper.readValue("{  \"endDate\" : \"endDate\",  \"renewalType\" : \"renewalType\",  \"isValid\" : \"isValid\",  \"planName\" : \"planName\",  \"startDate\" : \"startDate\"}", Subscription.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Subscription>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Subscription>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Subscription> subscriptionBySubscriptionIdPUT(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Subscription body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Subscription>(objectMapper.readValue("{  \"endDate\" : \"endDate\",  \"renewalType\" : \"renewalType\",  \"isValid\" : \"isValid\",  \"planName\" : \"planName\",  \"startDate\" : \"startDate\"}", Subscription.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Subscription>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Subscription>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Subscription>> subscriptionGET(@ApiParam(value = "Get Subscriptions for a given Tenant Name") @Valid @RequestParam(value = "tenantName", required = false) String tenantName,@ApiParam(value = "Get Subscriptions for a given Plan Name") @Valid @RequestParam(value = "planName", required = false) String planName,@ApiParam(value = "Get all Valid or Invalid Subscriptions") @Valid @RequestParam(value = "isValid", required = false) String isValid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	ListIterator<SubscriptionInfo> subscriptionInfoListIter = null;
            	
            	if (tenantName == null && planName == null && isValid == null) {
            		subscriptionInfoListIter = (StreamSupport.stream(subscriptionService.getAllSubscriptions().spliterator(), false).collect(Collectors.toList())).listIterator();
            	} else {
            		if (tenantName != null) {
            			subscriptionInfoListIter = (subscriptionService.getSubscriptionsByTenantName(tenantName)).listIterator();
            		}
            		
            		if (planName != null) {
            			subscriptionInfoListIter = (subscriptionService.getSubscriptionsByPlanName(planName)).listIterator();
            		}
            		
            		if (isValid != null) {
            			subscriptionInfoListIter = (subscriptionService.getSubscriptionsByIsValid(isValid)).listIterator();
            		}
            	}
            	List<Subscription> subscriptionList = new ArrayList<Subscription>();
            	
            	while (subscriptionInfoListIter.hasNext()) {
            		SubscriptionInfo currentItem = subscriptionInfoListIter.next();
            		Subscription newItem = new Subscription();
            		
            		newItem.setPlanName(currentItem.getPlanType().getPlanName());
            		newItem.setTenantName(currentItem.getTenant().getTenantName());
            		newItem.setRenewalType(currentItem.getRenewalType().getRenewalName());
            		newItem.setIsValid(currentItem.getIsValid());
            		newItem.setStartDate(currentItem.getStartDate().toString());
            		newItem.setEndDate(currentItem.getEndDate().toString());
            		
            		subscriptionList.add(newItem);
            	}
            	
            	return new ResponseEntity<List<Subscription>>(subscriptionList, HttpStatus.OK);
                //return new ResponseEntity<List<Subscription>>(objectMapper.readValue("[ {  \"endDate\" : \"endDate\",  \"renewalType\" : \"renewalType\",  \"isValid\" : \"isValid\",  \"planName\" : \"planName\",  \"startDate\" : \"startDate\"}, {  \"endDate\" : \"endDate\",  \"renewalType\" : \"renewalType\",  \"isValid\" : \"isValid\",  \"planName\" : \"planName\",  \"startDate\" : \"startDate\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Subscription>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Subscription>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Subscriptionresponse> subscriptionPOST(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Subscription body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                SubscriptionInfo subscriptionInfo = new SubscriptionInfo();
                
                subscriptionInfo.setTenantId(Integer.parseInt(body.getTenantName()));
                subscriptionInfo.setPlanTypeId(Integer.parseInt(body.getPlanName()));
                subscriptionInfo.setRenewalTypeId(Integer.parseInt(body.getRenewalType()));
                subscriptionInfo.setIsValid("Y");
                subscriptionInfo.setCreatedTS(new Timestamp((new Date()).getTime()));
                subscriptionInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
                subscriptionInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
                
                subscriptionService.saveSubscriptionInfo(subscriptionInfo);
                
            	return new ResponseEntity<Subscriptionresponse>(objectMapper.readValue("{  \"message\" : \"The Subscription has been created successfully\"}", Subscriptionresponse.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Subscriptionresponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Subscriptionresponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
