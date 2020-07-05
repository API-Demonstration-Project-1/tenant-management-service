package com.toystore.ecomm.tenants.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.toystore.ecomm.tenants.model.TenantInfo;

@Repository
public interface TenantRepository extends CrudRepository<TenantInfo, Integer> {
	//List<TenantInfo> findByTenantUsernameAndPassword(String tenantUsername, String tenantPassword);
	TenantInfo findByTenantId(Integer tenantId);
	
	List<TenantInfo> findByTenantName(String tenantName);
	List<TenantInfo> findByTenantUsername(String tenantUsername);
	List<TenantInfo> findByTenantEmail(String tenantEmail);
	List<TenantInfo> findByTenantVerified(String tenantVerified);
	List<TenantInfo> findByTenantVerificationCode(String tenantVerificationCode);
}
