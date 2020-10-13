package com.toystore.ecomm.tenants.services;

import java.io.IOException;

import com.toystore.ecomm.ptms.daorepo.model.TenantInfo;


public interface NotificationServiceFacade {
	public void sendNotification(TenantInfo tenantInfo, String action) throws IOException;

}
