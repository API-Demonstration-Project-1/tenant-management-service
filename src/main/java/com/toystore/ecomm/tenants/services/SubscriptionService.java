package com.toystore.ecomm.tenants.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toystore.ecomm.ptms.daorepo.model.SubscriptionInfo;
import com.toystore.ecomm.ptms.daorepo.model.SubscriptionTypeInfo;
import com.toystore.ecomm.ptms.daorepo.model.TenantInfo;
import com.toystore.ecomm.ptms.daorepo.repository.RenewalTypeRepository;
import com.toystore.ecomm.ptms.daorepo.repository.SubscriptionTypeRepository;
import com.toystore.ecomm.ptms.daorepo.repository.TenantRepository;
import com.toystore.ecomm.ptms.daorepo.repository.SubscriptionRepository;
import com.toystore.ecomm.tenants.constants.PTMSConstants;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private SubscriptionTypeRepository subscriptionTypeRepository;
	
	@Autowired
	private RenewalTypeRepository renewalTypeRepository;

	public void saveSubscriptionInfo(SubscriptionInfo subscriptionInfo) throws IllegalAccessException, InstantiationException {
		subscriptionInfo.setIsValid(PTMSConstants.YES_VALUE);
		
		subscriptionInfo.setRenewalType(renewalTypeRepository.findById(subscriptionInfo.getRenewalTypeId()).get());
		
		Calendar cal = Calendar.getInstance();
		if (PTMSConstants.SUBS_MONTHLY.equalsIgnoreCase(subscriptionInfo.getRenewalType().getRenewalName())) {				// MONTHLY
			cal.setTime(subscriptionInfo.getStartDate());
			cal.add(Calendar.MONTH, +1);
		} else if (PTMSConstants.SUBS_QUATERLY.equalsIgnoreCase(subscriptionInfo.getRenewalType().getRenewalName())) {		// QUATERLY
			cal.setTime(subscriptionInfo.getStartDate());
			cal.add(Calendar.MONTH, +3);
		} else if (PTMSConstants.SUBS_HALFYEARLY.equalsIgnoreCase(subscriptionInfo.getRenewalType().getRenewalName())) {	// HALF YEARLY
			cal.setTime(subscriptionInfo.getStartDate());
			cal.add(Calendar.MONTH, +6);
		} else if (PTMSConstants.SUBS_YEARLY.equalsIgnoreCase(subscriptionInfo.getRenewalType().getRenewalName())) {		// YEARLY
			cal.setTime(subscriptionInfo.getStartDate());
			cal.add(Calendar.YEAR, +1);
		} else if (PTMSConstants.SUBS_TWOYEARS.equalsIgnoreCase(subscriptionInfo.getRenewalType().getRenewalName())) {		// TWO YEARS
			cal.setTime(subscriptionInfo.getStartDate());
			cal.add(Calendar.YEAR, +2);
		} else if (PTMSConstants.SUBS_THREEYEARS.equalsIgnoreCase(subscriptionInfo.getRenewalType().getRenewalName())) {	// THREE YEARS
			cal.setTime(subscriptionInfo.getStartDate());
			cal.add(Calendar.YEAR, +3);
		} else if (PTMSConstants.SUBS_FIVEYEARS.equalsIgnoreCase(subscriptionInfo.getRenewalType().getRenewalName())) {		// FIVE YEARS
			cal.setTime(subscriptionInfo.getStartDate());
			cal.add(Calendar.YEAR, +5);
		}
		
		subscriptionInfo.setEndDate(cal.getTime());
		//subscriptionInfo.withId((new Random()).nextInt(1000));
		
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
