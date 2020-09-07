package com.toystore.ecomm.tenants.services;

import java.sql.Timestamp;
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
import com.toystore.ecomm.tenants.constants.PTMSConstants;
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
		subscriptionInfo.setIsValid(PTMSConstants.YES_VALUE);
		subscriptionInfo.setStartDate(new Date());
		subscriptionInfo.setEndDate(new Date());
		subscriptionInfo.withId((new Random()).nextInt(1000));
		
		subscriptionInfo.setCreatedTS(new Timestamp((new Date()).getTime()));
        subscriptionInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
        subscriptionInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
		
		subscriptionRepository.save(subscriptionInfo);
	}
	
	public void updateSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
		subscriptionInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
	    subscriptionInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
	        
		subscriptionRepository.save(subscriptionInfo);
	}
	
	public List<SubscriptionInfo> getAllSubscriptions() {
		List<SubscriptionInfo> subscriptionList = new ArrayList<SubscriptionInfo>();
		
		(subscriptionRepository.findAll()).forEach(subscriptionList::add);
		
		return subscriptionList;
	}
	
	public List<SubscriptionInfo> getSubscriptionsByTenantId(Integer tenantId) {
		return subscriptionRepository.findByTenantId(tenantId);
	}
	
	public List<SubscriptionInfo> getSubscriptionsByTenantUserNameValidity(String tenantUserName, String isValid) {
		return subscriptionRepository.findByTenantUserNameValidity(tenantUserName.toLowerCase(), isValid.toLowerCase());
	}
	
	public List<SubscriptionInfo> getSubscriptionsByTenantName(String tenantName) {
		ListIterator<TenantInfo> tenantInfoListIter = tenantRepository.findByTenantName(tenantName).listIterator();
		List<SubscriptionInfo> allSubscriptionInfoList = new ArrayList<SubscriptionInfo>();
		
		while (tenantInfoListIter.hasNext()) {
			List<SubscriptionInfo> fetchedSubscriptionInfoList = new ArrayList<SubscriptionInfo>();
			TenantInfo tenantInfo = tenantInfoListIter.next();
			
			if (!(fetchedSubscriptionInfoList = subscriptionRepository.findByTenantId(tenantInfo.getTenantId())).isEmpty()) {
				allSubscriptionInfoList.addAll(fetchedSubscriptionInfoList);
			}
		}
		
		return allSubscriptionInfoList;
	}
	
	public List<SubscriptionInfo> getSubscriptionsByPlanName(String planName) {
		SubscriptionTypeInfo  subscriptionTypeInfo = (subscriptionTypeRepository.findByPlanName(planName)).get(0);
		return subscriptionRepository.findByPlanTypeId(subscriptionTypeInfo.getPlanTypeId());
	}
	
	public List<SubscriptionInfo> getSubscriptionsByTenantNamePlanName(String tenantName, String planName) {
		return subscriptionRepository.findByTenantNamePlanName(tenantName.toLowerCase(), planName.toLowerCase());
	}
	
	public List<SubscriptionInfo> getSubscriptionsByTenantNameValidity(String tenantName, String isValid) {
		return subscriptionRepository.findByTenantNameValidity(tenantName.toLowerCase(), isValid.toLowerCase());
	}
	
	public List<SubscriptionInfo> getSubscriptionsByTenantNamePlanNameValidity(String tenantName, String planName, String isValid) {
		return subscriptionRepository.findByTenantNamePlanNameValidity(tenantName.toLowerCase(), planName.toLowerCase(), isValid.toLowerCase());
	}

	public List<SubscriptionInfo> getSubscriptionsByValidity(String isValid) {
		return subscriptionRepository.findByIsValid(isValid);
	}
	
	public List<SubscriptionInfo> getSubscriptionsByPlanNameValidity(String planName, String isValid) {
		return subscriptionRepository.findByPlanNameValidity(planName.toLowerCase(), isValid.toLowerCase());
	}
	/*
	 * public SubscriptionInfo updateSubscriptionInfo(SubscriptionInfo
	 * subscriptionInfo) { SubscriptionInfo existingSubscriptionInfo =
	 * subscriptionRepository.findBySubscriptionId(subscriptionInfo.
	 * getSubscriptionId());
	 * 
	 * existingSubscriptionInfo.getPlanType().setPlanTypeId(3); return
	 * subscriptionRepository.save(existingSubscriptionInfo); }
	 */
	
	public SubscriptionInfo getSubscriptionById(Integer subscriptionId) {
		return subscriptionRepository.findBySubscriptionId(subscriptionId);
	}
	
	public boolean isSubscriptionExisting(Integer subscriptionId) {
		return subscriptionRepository.findBySubscriptionId(subscriptionId) != null ? true : false;
	}
	
	public void removeSubscription(Integer subscriptionId) {
		subscriptionRepository.deleteById(subscriptionId);
	}
}
