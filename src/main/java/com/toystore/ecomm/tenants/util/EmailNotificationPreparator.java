package com.toystore.ecomm.tenants.util;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessagePreparator;

public class EmailNotificationPreparator {
	
	 public static MimeMessagePreparator prepareEmailForTenantVerification(Integer tenantId, String toEmail, String verificationCode) {
	    	String htmlMsg = "<h1><center>PTMS - Registration Verification</center></h1><br>"
	    					 + 
	    					 "<h2>To confirm your account, please click " + "<a href=https://localhost:8080/ptms/registration/" + tenantId + "/emailverification?code=" + verificationCode + ">here</a></h2>";
	        
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {

	            public void prepare(MimeMessage mimeMessage) throws Exception {
	            	mimeMessage.setSubject("PTMS - Registration Verification");
	                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	                mimeMessage.setFrom(new InternetAddress("donotreply.proarchs@gmail.com"));
	                mimeMessage.addHeader("Content-Type", "application/json");
	                mimeMessage.setContent(htmlMsg, "text/html");
	            }
	        };
	        
	        return preparator;
	 }
	 
	 public static MimeMessagePreparator prepareEmailForWelcome(String toEmail) {
	    	String htmlMsg = "<h1><center>WELCOME TO PTMS - Proarchs Tenant Management Service</center></h1><br>"
	    					 + 
	    					 "<h3>There is lot to explore here. Just wait & watch. For now, please confirm your Registration via Email Verification sent to your Registered Email.</h3>";
	        
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {

	            public void prepare(MimeMessage mimeMessage) throws Exception {
	            	mimeMessage.setSubject("Welcome to PTMS");
	                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	                mimeMessage.setFrom(new InternetAddress("donotreply.proarchs@gmail.com"));
	                mimeMessage.addHeader("Content-Type", "application/json");
	                mimeMessage.setContent(htmlMsg, "text/html");
	            }
	        };
	        
	        return preparator;
	 }
	 
	 public static MimeMessagePreparator prepareEmailForPostVerification(String toEmail) {
	    	String htmlMsg = "<h1><center>PTMS - Registration Verification Confirmation</center></h1><br>"
	    					 + 
	    					 "<h3>This is to confirm that your Registration has been successfully verified.</h3>";
	        
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {

	            public void prepare(MimeMessage mimeMessage) throws Exception {
	            	mimeMessage.setSubject("PTMS - Registration Verification Confirmation");
	                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	                mimeMessage.setFrom(new InternetAddress("donotreply.proarchs@gmail.com"));
	                mimeMessage.addHeader("Content-Type", "application/json");
	                mimeMessage.setContent(htmlMsg, "text/html");
	            }
	        };
	        
	        return preparator;
	 }
}
