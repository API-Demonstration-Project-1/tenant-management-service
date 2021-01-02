package com.toystore.ecomm.tenants.services;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.toystore.ecomm.ptms.daorepo.model.TenantInfo;
import com.toystore.ecomm.ptms.daorepo.repository.TenantRepository;
import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.util.DateFormatter;
import com.toystore.ecomm.tenants.util.JsonFormatter;
import com.toystore.ecomm.tenants.util.ServiceInvoker;

@Service
public class JMSNotificationService {
	
	private static final Logger log = LoggerFactory.getLogger(JMSNotificationService.class);

	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private TenantService tenantService;
	
	@Transactional
	@JmsListener(destination = "${activemq.notification.otpverification.outbound}")
	public void receiveOTPVerificationResp(final Message<String> jmsMessage) throws JsonMappingException, JsonProcessingException {
		// Extract all field values from JSON
		JsonNode nodes = JsonFormatter.convertStringToJsonNode(jmsMessage.getPayload());
		String tenantId = nodes.get("tenantId").textValue();
		String verificationId = nodes.get("verificationId").textValue();
		
		System.out.println("For Tenant ID: " + tenantId +", this is the Verification ID: " + verificationId);
		
		TenantInfo tenantInfo = updateTenantWithVerificationId(Integer.parseInt(tenantId), Integer.parseInt(verificationId));
		
		log.trace("Updated Tenant Info POJO: " + tenantInfo);
	}
	
	@Transactional
	@JmsListener(destination = "${activemq.notification.otpverificationconfirmation.outbound}")
	public void receiveOTPVerificationConfirmationResp(final Message<String> jmsMessage) throws ParseException, Exception {
		// Extract all field values from JSON
		JsonNode nodes = JsonFormatter.convertStringToJsonNode(jmsMessage.getPayload());
		String verificationId = nodes.get("verificationId").textValue();
		
		System.out.println("Verification ID: " + verificationId);
		
		//tenantRepository.updateTenantAsVerified(verificationId);
		
		TenantInfo tenantInfo = tenantRepository.findByVerificationId(Integer.parseInt(verificationId)).get(0);
		
		TenantInfo updatedTenantInfo = createCustomerSubscriptionInStripe(tenantInfo.getTenantId(), tenantInfo.getTenantName());
		
		if (updatedTenantInfo != null) {
			log.trace("Updated Tenant:" + updatedTenantInfo);
		} else {
			log.error("Error while creating Customer & Subscription in STRIPE");
		}
	}
	
	@Transactional
	@JmsListener(destination = "${activemq.notification.emailverification.outbound}")
	public void receiveEmailVerificationResp(final Message<String> jmsMessage) throws JsonMappingException, JsonProcessingException {
		// Extract all field values from JSON
		JsonNode nodes = JsonFormatter.convertStringToJsonNode(jmsMessage.getPayload());
		String tenantId = nodes.get("tenantId").textValue();
		String verificationId = nodes.get("verificationId").textValue();
		
		System.out.println("For Tenant ID: " + tenantId +", this is the Verification ID: " + verificationId);
		
		TenantInfo tenantInfo = updateTenantWithVerificationId(Integer.parseInt(tenantId), Integer.parseInt(verificationId));
		
		log.trace("Updated Tenant Info POJO: " + tenantInfo);
		
		//tenantRepository.updateTenantWithVerification(verificationId, Integer.parseInt(tenantId));
	}
	
	@Transactional
	@JmsListener(destination = "${activemq.notification.emailverificationconfirmation.outbound}")
	public void receiveEmailVerificationConfirmationResp(final Message<String> jmsMessage) throws ParseException, Exception {
		// Extract all field values from JSON
		JsonNode nodes = JsonFormatter.convertStringToJsonNode(jmsMessage.getPayload());
		String verificationId = nodes.get("verificationId").textValue();
		
		System.out.println("Verification ID: " + verificationId);
		
		//tenantRepository.updateTenantAsVerified(verificationId);
		
		TenantInfo tenantInfo = tenantRepository.findByVerificationId(Integer.parseInt(verificationId)).get(0);
		
		TenantInfo updatedTenantInfo = createCustomerSubscriptionInStripe(tenantInfo.getTenantId(), tenantInfo.getTenantName());
		
		if (updatedTenantInfo != null) {
			log.trace("Updated Tenant:" + updatedTenantInfo);
		} else {
			log.error("Error while creating Customer & Subscription in STRIPE");
		}
	}
	
	// Update 'TENANT' table with Verification ID from Notification API
	private TenantInfo updateTenantWithVerificationId(Integer tenantId, Integer verificationId) {
		 TenantInfo tenantInfo = tenantRepository.findByTenantId(tenantId);
		 
		 tenantInfo.setVerificationId(verificationId);
		 
		 tenantService.updateTenantInfo(tenantInfo);
		 
		 return tenantInfo;
	}
	
	// Create Customer & Subscription in STRIPE Payment Gateway. Also, Update 'TENANT' table.
	private TenantInfo createCustomerSubscriptionInStripe(Integer tenantId, String tenantName) throws IllegalAccessException, InstantiationException, ParseException {
		// Create 'Customer' in Stripe - START
		Map<String, Object> reqParams = new HashMap<String, Object>(1);
		reqParams.put("customerName", tenantName);
		
		ResponseEntity<String> paymentSrvcResp = ServiceInvoker.invokePaymentService("http://localhost:8083/payments/subscriptioncustomer", reqParams);
		String respStr = paymentSrvcResp.getBody();
		HttpStatus httpStatus = paymentSrvcResp.getStatusCode();
		
		if (httpStatus != HttpStatus.CREATED) {
			log.error("registrationEmailverificationByTenantIdGET() - Error Response from Payment Service: " + respStr);
			
			//String errorDesc = (new JSONObject(respStr)).getString(PTMSConstants.ERROR_DESC_FIELD);
			//String resp = ResponsePreparator.prepareLoginResponse(null, "Error - " + errorDesc, false, -1);

	        //return new ResponseEntity<String>(resp, httpStatus);
			
			return null;
		} 
		
		log.info("registrationEmailverificationByTenantIdGET() - Response from Payment Service: " + respStr);
		
		int customerId = (new JSONObject(respStr)).getJSONObject("data").getInt("id");
		// Create 'Customer' in Stripe - END
		
		// Create 'Subscription' (Trial) in Stripe - START
		
		reqParams = new HashMap<String, Object>(6);
   		
   		reqParams.put("subscriptionCustomerId", customerId);
   		reqParams.put("planType", PTMSConstants.SUBS_TRIAL);
   		reqParams.put("renewalType", PTMSConstants.SUBS_TRIAL);
   		reqParams.put("trialDays", PTMSConstants.TRIAL_SUBSCRIPTION_DAYS);
   				
   		paymentSrvcResp = ServiceInvoker.invokePaymentService("http://localhost:8083/payments/subscriptionpayment", reqParams);
   		
   		httpStatus = paymentSrvcResp.getStatusCode();
   		JSONObject respJSONObj = new JSONObject(paymentSrvcResp.getBody());
		
		if (httpStatus != HttpStatus.CREATED) {
			log.error("subscriptionPOST() - Error Response from Payment Service: " + paymentSrvcResp.getBody());
			
			//String errorDesc = respJSONObj.getString(PTMSConstants.ERROR_DESC_FIELD);
			//String resp = ResponsePreparator.prepareLoginResponse(null, "Error - " + errorDesc, false, -1);

	        //return new ResponseEntity<String>(resp, httpStatus);
			
			return null;
		}
		
		String startDate = respJSONObj.getJSONObject("data").getString("startDate");
        String endDate = respJSONObj.getJSONObject("data").getString("endDate");
            
		// Create 'Subscription' (Trial) in Stripe - END
		
		TenantInfo updatedTenantInfo = tenantService.updateTenantInfoPostVerification(tenantId, customerId, DateFormatter.format(startDate), DateFormatter.format(endDate));
		
		return updatedTenantInfo;
	}
}
