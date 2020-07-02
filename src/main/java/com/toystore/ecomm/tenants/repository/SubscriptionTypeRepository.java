package com.toystore.ecomm.tenants.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.toystore.ecomm.tenants.model.SubscriptionTypeInfo;

@Repository
public interface SubscriptionTypeRepository extends CrudRepository<SubscriptionTypeInfo, Integer> {
	List<SubscriptionTypeInfo> findByPlanTypeId(Integer planTypeId);
	List<SubscriptionTypeInfo> findByPlanName(String planName);
}
