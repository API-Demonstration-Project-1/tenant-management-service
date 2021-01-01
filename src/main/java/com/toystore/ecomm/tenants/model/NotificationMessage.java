package com.toystore.ecomm.tenants.model;

public class NotificationMessage {
	
	private String subject;
	
	private String toAddress;
	
	private String fromAddress;
	
	private String messageBody;

	public String getSubject() {
		return subject;
	}

	public String getToAddress() {
		return toAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	
}
