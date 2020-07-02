package com.toystore.ecomm.tenants.repository;

import com.toystore.ecomm.tenants.model.TenantInfo;

public interface TenantRepositoryCustom  {
	TenantInfo findByTenantUsernameAndPassword(String tenantUsername, String tenantPassword);
}
