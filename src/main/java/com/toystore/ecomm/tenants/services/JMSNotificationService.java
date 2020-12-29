package com.toystore.ecomm.tenants.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.toystore.ecomm.ptms.daorepo.repository.TenantRepository;
import com.toystore.ecomm.tenants.util.JsonFormatter;

@Service
public class JMSNotificationService {
	
	private static final Logger log = LoggerFactory.getLogger(JMSNotificationService.class);

	@Autowired
	private TenantRepository tenantRepository;
	
	@Transactional
	@JmsListener(destination = "${activemq.notification.otpverification.outbound}")
	public void receiveOTPVerificationResp(final Message<String> jmsMessage) throws JsonMappingException, JsonProcessingException {
		// Extract all field values from JSON
		JsonNode nodes = JsonFormatter.convertStringToJsonNode(jmsMessage.getPayload());
		String tenantId = nodes.get("tenantId").textValue();
		String verificationId = nodes.get("verificationId").textValue();
		
		System.out.println("For Tenant ID: " + tenantId +", this is the Verification ID: " + verificationId);
		
		/*
		 * TenantInfo tenantInfo =
		 * tenantRepository.findByTenantId(Integer.parseInt(tenantId));
		 * tenantInfo.setVerificationId(verificationId);
		 * 
		 * //tenantRepository.save(tenantInfo);
		 * tenantService.updateTenantInfo(tenantInfo);
		 */
		
		tenantRepository.updateTenantWithVerification(verificationId, Integer.parseInt(tenantId));
		
		//log.trace("Updated Tenant Info POJO: " + tenantInfo);
	}
	
	@Transactional
	@JmsListener(destination = "${activemq.notification.otpverificationconfirmation.outbound}")
	public void receiveOTPVerificationConfirmationResp(final Message<String> jmsMessage) throws JsonMappingException, JsonProcessingException {
		// Extract all field values from JSON
		JsonNode nodes = JsonFormatter.convertStringToJsonNode(jmsMessage.getPayload());
		String verificationId = nodes.get("verificationId").textValue();
		
		System.out.println("Verification ID: " + verificationId);
		
		tenantRepository.updateTenantAsVerified(verificationId);
	}
}
