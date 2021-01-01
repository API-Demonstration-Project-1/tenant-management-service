package com.toystore.ecomm.tenants.util;

public class EmailNotificationPreparator {
	
	public static String prepareEmailForTenantVerification(Integer tenantId, String verificationCode) {
			String htmlMsg = "<h1><center>PTMS - Registration Verification</center></h1><br>"
	    					 + 
	    					 "<h2>To confirm your account, please click " + "<a href=https://localhost:8080/ptms/registration/" + tenantId + "/emailverification?code=" + verificationCode + ">here</a></h2>";
			
	        return htmlMsg;
	 }
	 
	 public static String prepareEmailForWelcome() {
	    	String htmlMsg = "<h1><center>WELCOME TO PTMS - Proarchs Tenant Management Service</center></h1><br>"
	    					 + 
	    					 "<h3>There is lot to explore here. Just wait & watch. For now, please confirm your Registration via Email Verification sent to your Registered Email.</h3>";
	        
	        return htmlMsg;
	 }
	 
	 public static String prepareEmailForPostVerification() {
	    	String htmlMsg = "<h1><center>PTMS - Registration Verification Confirmation</center></h1><br>"
	    					 + 
	    					 "<h3>This is to confirm that your Registration has been successfully verified.</h3>";
	        
	        return htmlMsg;
	 }
}
