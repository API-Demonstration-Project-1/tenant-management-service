package com.toystore.ecomm.tenants.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.toystore.ecomm.ptms.daorepo.model.TenantInfo;


@Service
public class NotificationServiceFacadeImpl implements NotificationServiceFacade {

	@Value("${notification.email.on}")
	private boolean isEmailNotificationEnabled;
	
	@Value("${notification.sms.on}")
	private boolean isSMSNotificationEnabled;
	
	@Autowired
	private EmailService emailService;

	@Override
	public void sendNotification(TenantInfo tenantInfo, String action) throws IOException {
        
		if (isEmailNotificationEnabled) {
	        emailService.sendNotification(tenantInfo, action);
		}

	}

}
