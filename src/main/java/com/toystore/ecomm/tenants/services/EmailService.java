package com.toystore.ecomm.tenants.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	@Autowired 
	public EmailService(JavaMailSender javaMailSender) {
	  this.javaMailSender = javaMailSender; 
	}

	@Async
	public void sendNotification(MimeMessagePreparator preparator) {
		
		javaMailSender.send(preparator);
	}

}
