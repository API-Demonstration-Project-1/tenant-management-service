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
	List<SubscriptionInfo> findByTenantId(Integer tenantId);
	List<SubscriptionInfo> findByTenant(TenantInfo tenant);
	List<SubscriptionInfo> findByIsValid(String isValid);
	List<SubscriptionInfo> findByPlanTypeId(Integer planTypeId);
	
	@Query("SELECT s FROM SubscriptionInfo s WHERE s.tenant.tenantUsername = :tenantUsername AND LOWER(s.isValid) = :isValid")
	List<SubscriptionInfo> findByTenantUserNameValidity(@Param("tenantUsername") String tenantUsername, @Param("isValid") String isValid);
	
	@Query("SELECT s FROM SubscriptionInfo s WHERE LOWER(s.tenant.tenantName) = :tenantName AND LOWER(s.planType.planName) LIKE %:planName%")
	List<SubscriptionInfo> findByTenantNamePlanName(@Param("tenantName") String tenantName, @Param("planName") String planName);
	
	@Query("SELECT s FROM SubscriptionInfo s WHERE LOWER(s.tenant.tenantName) = :tenantName AND LOWER(s.isValid) = :isValid")
	List<SubscriptionInfo> findByTenantNameValidity(@Param("tenantName") String tenantName, @Param("isValid") String isValid);
	
	@Query("SELECT s FROM SubscriptionInfo s WHERE LOWER(s.tenant.tenantName) = :tenantName AND LOWER(s.planType.planName) LIKE %:planName% AND LOWER(s.isValid) = :isValid")
	List<SubscriptionInfo> findByTenantNamePlanNameValidity(@Param("tenantName") String tenantName, @Param("planName") String planName, @Param("isValid") String isValid);
	
	@Query("SELECT s FROM SubscriptionInfo s WHERE LOWER(s.planType.planName) LIKE %:planName% AND LOWER(s.isValid) = :isValid")
	List<SubscriptionInfo> findByPlanNameValidity(@Param("planName") String planName, @Param("isValid") String isValid);
}
