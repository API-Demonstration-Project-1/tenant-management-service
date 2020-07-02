package com.toystore.ecomm.tenants.services;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toystore.ecomm.tenants.repository.SubscriptionRepository;
import com.toystore.ecomm.tenants.repository.SubscriptionTypeRepository;
import com.toystore.ecomm.tenants.repository.TenantRepository;
import com.toystore.ecomm.tenants.model.SubscriptionInfo;
import com.toystore.ecomm.tenants.model.SubscriptionTypeInfo;
import com.toystore.ecomm.tenants.model.TenantInfo;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private SubscriptionTypeRepository subscriptionTypeRepository;

	public void saveSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
		subscriptionInfo.setStartDate(new Date());
		subscriptionInfo.setEndDate(new Date());
		
		subscriptionInfo.withId((new Random()).nextInt(1000));
		
		subscriptionRepository.save(subscriptionInfo);
	}
	
	public Iterable<SubscriptionInfo> getAllSubscriptions() {
		return subscriptionRepository.findAll();
	}
	public List<SubscriptionInfo> getSubscriptionsByTenantId(Integer tenantId) {
		return subscriptionRepository.findByTenantId(tenantId);
	}
	
	public List<SubscriptionInfo> getSubscriptionsByTenantName(String tenantName) {
		TenantInfo tenantInfo = (tenantRepository.findByTenantName(tenantName)).get(0);
		return subscriptionRepository.findByTenantId(tenantInfo.getTenantId());
	}
	
	public List<SubscriptionInfo> getSubscriptionsByPlanName(String planName) {
		SubscriptionTypeInfo  subscriptionTypeInfo = (subscriptionTypeRepository.findByPlanName(planName)).get(0);
		return subscriptionRepository.findByPlanTypeId(subscriptionTypeInfo.getPlanTypeId());
	}

	public List<SubscriptionInfo> getSubscriptionsByIsValid(String isValid) {
		return subscriptionRepository.findByIsValid(isValid);
	}
	
	public SubscriptionInfo updateSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
		SubscriptionInfo existingSubscriptionInfo = subscriptionRepository.findBySubscriptionId(subscriptionInfo.getSubscriptionId()).get(0);
		
		existingSubscriptionInfo.getPlanType().setPlanTypeId(3);
		return subscriptionRepository.save(existingSubscriptionInfo);
	}
}
