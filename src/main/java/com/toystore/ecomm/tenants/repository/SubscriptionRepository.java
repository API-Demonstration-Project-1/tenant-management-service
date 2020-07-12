package com.toystore.ecomm.tenants.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.toystore.ecomm.tenants.model.SubscriptionInfo;
import com.toystore.ecomm.tenants.model.TenantInfo;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionInfo, Integer> {
	//List<TenantInfo> findByTenantUsernameAndPassword(String tenantUsername, String tenantPassword);
	SubscriptionInfo findBySubscriptionId(Integer subscriptionId);
	SubscriptionInfo findByTenantId(Integer tenantId);
	List<SubscriptionInfo> findByTenant(TenantInfo tenant);
	List<SubscriptionInfo> findByIsValid(String isValid);
	List<SubscriptionInfo> findByPlanTypeId(Integer planTypeId);
	
	@Query("SELECT s FROM SubscriptionInfo s WHERE LOWER(s.tenant.tenantName) = :tenantName AND LOWER(s.planType.planName) LIKE %:planName%")
	List<SubscriptionInfo> findByTenantNamePlanName(@Param("tenantName") String tenantName, @Param("planName") String planName);
	
}
