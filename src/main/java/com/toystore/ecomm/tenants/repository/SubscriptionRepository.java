package com.toystore.ecomm.tenants.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.toystore.ecomm.tenants.model.SubscriptionInfo;
import com.toystore.ecomm.tenants.model.TenantInfo;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionInfo, Integer> {
	//List<TenantInfo> findByTenantUsernameAndPassword(String tenantUsername, String tenantPassword);
	List<SubscriptionInfo> findBySubscriptionId(Integer subscriptionId);
	List<SubscriptionInfo> findByTenantId(Integer tenantId);
	List<SubscriptionInfo> findByTenant(TenantInfo tenant);
	List<SubscriptionInfo> findByIsValid(String isValid);
	List<SubscriptionInfo> findByPlanTypeId(Integer planTypeId);
}
