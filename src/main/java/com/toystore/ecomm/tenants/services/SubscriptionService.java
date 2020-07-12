package com.toystore.ecomm.tenants.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
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
	public SubscriptionInfo getSubscriptionsByTenantId(Integer tenantId) {
		return subscriptionRepository.findByTenantId(tenantId);
	}
	
	public List<SubscriptionInfo> getSubscriptionsByTenantName(String tenantName) {
		ListIterator<TenantInfo> tenantInfoListIter = tenantRepository.findByTenantName(tenantName).listIterator();
		List<SubscriptionInfo> subscriptionInfoList = new ArrayList<SubscriptionInfo>();
		
		while (tenantInfoListIter.hasNext()) {
			TenantInfo tenantInfo = tenantInfoListIter.next();
			
			if (subscriptionRepository.findByTenantId(tenantInfo.getTenantId()) != null) {
				subscriptionInfoList.add(subscriptionRepository.findByTenantId(tenantInfo.getTenantId()));
			}
		}
		
		return subscriptionInfoList;
	}
	
	public List<SubscriptionInfo> getSubscriptionsByPlanName(String planName) {
		SubscriptionTypeInfo  subscriptionTypeInfo = (subscriptionTypeRepository.findByPlanName(planName)).get(0);
		return subscriptionRepository.findByPlanTypeId(subscriptionTypeInfo.getPlanTypeId());
	}
	
	public List<SubscriptionInfo> getSubscriptionsByTenantNamePlanName(String tenantName, String planName) {
		return subscriptionRepository.findByTenantNamePlanName(tenantName, planName.toLowerCase());
	}

	public List<SubscriptionInfo> getSubscriptionsByIsValid(String isValid) {
		return subscriptionRepository.findByIsValid(isValid);
	}
	
	public SubscriptionInfo updateSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
		SubscriptionInfo existingSubscriptionInfo = subscriptionRepository.findBySubscriptionId(subscriptionInfo.getSubscriptionId());
		
		existingSubscriptionInfo.getPlanType().setPlanTypeId(3);
		return subscriptionRepository.save(existingSubscriptionInfo);
	}
	
	public SubscriptionInfo getSubscriptionById(Integer subscriptionId) {
		return subscriptionRepository.findBySubscriptionId(subscriptionId);
	}
}
