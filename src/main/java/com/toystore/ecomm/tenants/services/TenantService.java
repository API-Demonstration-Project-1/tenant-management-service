package com.toystore.ecomm.tenants.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.factory.POJOFactory;
import com.toystore.ecomm.tenants.model.SubscriptionInfo;
import com.toystore.ecomm.tenants.model.TenantDBInfo;
import com.toystore.ecomm.tenants.model.TenantInfo;
import com.toystore.ecomm.tenants.repository.TenantRepository;
import com.toystore.ecomm.tenants.util.RandomStringGenerator;

@Service
public class TenantService {

	@Autowired
	private TenantRepository tenantRepository;
	
	@Value("${spring.datasource.username}")
	private String dbUsername;
	
	@Value("${spring.datasource.password}")
	private String dbPassword;
	
	@Value("${datasource.url.partial}")
	private String dbUrlPartial;

	/*
	 * public List<TenantInfo> getTenantByUsernameAndPassword(String userName,
	 * String password) { return
	 * tenantRepository.findByTenantUsernameAndPassword(userName, password); }
	 */

	public TenantInfo saveTenantInfo(TenantInfo tenantInfo) {
		tenantInfo.withId((new Random()).nextInt(1000));
		tenantInfo.setTenantVerified(PTMSConstants.NO_VALUE);
    	tenantInfo.setTenantVerificationCode(RandomStringGenerator.getRandomAlphaNumericString(15));
    	tenantInfo.setCreatedTS(new Timestamp((new Date()).getTime()));
    	tenantInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
    	tenantInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
    	
    	// If new Tenant registration belongs to any existing Tenant or Org (check by Tenant Name) or Not
    	if (tenantRepository.findByTenantName(tenantInfo.getTenantName()).isEmpty()) {
    		tenantInfo.setTenantRoleId(PTMSConstants.TENANT_ADMIN_ROLE_TYPE_CODE);
    		
    	} else {
    		tenantInfo.setTenantRoleId(PTMSConstants.TENANT_USER_ROLE_TYPE_CODE);
    	}
    	
		return tenantRepository.save(tenantInfo);
	}

	public void updateTenantInfo(TenantInfo existingTenantInfo) {
		existingTenantInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
		existingTenantInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
		
		tenantRepository.save(existingTenantInfo);
	}
	
	@Transactional
	public TenantInfo updateTenantInfoPostVerification(Integer tenantId) throws Exception {
		TenantInfo existingTenantInfo = tenantRepository.findByTenantId(tenantId);
		
		existingTenantInfo.setTenantVerified(PTMSConstants.YES_VALUE);
		existingTenantInfo.setTenantVerificationCode(null);
		existingTenantInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
		existingTenantInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
		
		// Create Community (Free) Subscription by default once Verification is confirmed
		SubscriptionInfo subscriptionInfo = (SubscriptionInfo)POJOFactory.getInstance("SUBSCRIPTIONINFO");
        
		subscriptionInfo.withId((new Random()).nextInt(1000));
		subscriptionInfo.setStartDate(new Date());
		subscriptionInfo.setEndDate(new Date());
        subscriptionInfo.setTenantId(tenantId);
        subscriptionInfo.setPlanTypeId(PTMSConstants.FREE_SUBSCRIPTION_TYPE);
        subscriptionInfo.setRenewalTypeId(PTMSConstants.MONTHLY_RENEWAL_TYPE);
        subscriptionInfo.setIsValid(PTMSConstants.YES_VALUE);
        subscriptionInfo.setCreatedTS(new Timestamp((new Date()).getTime()));
        subscriptionInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
        subscriptionInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
        
        List<SubscriptionInfo> subscriptionInfoList = new ArrayList<SubscriptionInfo>(1);
		subscriptionInfoList.add(subscriptionInfo);
		
		// Create DB-related Info once Verification is confirmed
		TenantDBInfo tenantDBInfo = (TenantDBInfo)POJOFactory.getInstance("TENANTDBINFO");
		
		tenantDBInfo.withId((new Random()).nextInt(1000));
		tenantDBInfo.setTenantId(tenantId);
		tenantDBInfo.setTenantDBUrl(dbUrlPartial + existingTenantInfo.getTenantUsername() + "?useSSL=false");
		tenantDBInfo.setTenantDBName(existingTenantInfo.getTenantUsername());
		tenantDBInfo.setTenantDBUsername(dbUsername);
		tenantDBInfo.setTenantDBPassword(dbPassword);
		
		existingTenantInfo.setTenantDBInfo(tenantDBInfo);
		existingTenantInfo.setSubscriptionInfoList(subscriptionInfoList);
		 
		existingTenantInfo = tenantRepository.save(existingTenantInfo);
		
		// Create DB Schema & necessary tables for the newly Registered Tenant
		tenantRepository.createDbSchemaNTables(existingTenantInfo.getTenantUsername());
		
		return existingTenantInfo;
	}
	
	public boolean isTenantExisting(Integer tenantId) {
		return ((tenantRepository.findByTenantId(tenantId)) == null ? false : true);
	}
	
	public boolean isTenantUsernameUnique(String tenantUsername) {
		return ((tenantRepository.findByTenantUsername(tenantUsername).size()) > 0) ? false : true;
	}
	
	public boolean isTenantEmailUnique(String tenantEmail) {
		return ((tenantRepository.findByTenantEmail(tenantEmail).size()) > 0) ? false : true;
	}
	
	public boolean isTenantRegistered(Integer tenantId, String code) {
		
		TenantInfo tenantInfo = null;
		
		return ((tenantInfo = tenantRepository.findByTenantId(tenantId)) == null ? false : ((tenantInfo.getTenantVerificationCode()).equals(code) ? true : false));
	}
	
	public boolean isTenantVerified(Integer tenantId) {
		TenantInfo tenantInfo = null;
		
		return ((tenantInfo = tenantRepository.findByTenantId(tenantId)) == null ? false : ((tenantInfo.getTenantVerified()).equals(PTMSConstants.YES_VALUE) ? true : false));
	}
	
	public TenantInfo getTenantInfoByUsername(String tenantUsername) {
		List<TenantInfo> tenantInfoList = tenantRepository.findByTenantUsername(tenantUsername);
		
		return tenantInfoList.size() == 0 ? null : tenantInfoList.get(0);
	}
	
	public void removeTenantInfo(Integer tenantId) throws Exception {
		String dbName = tenantRepository.findByTenantId(tenantId).getTenantUsername();
		tenantRepository.deleteById(tenantId);
		
		// Drop the corresponding DB Schema as well
		tenantRepository.dropDbSchema(dbName);
	}
	
	public TenantInfo getTenantInfoByTenantId(Integer tenantId) {
		return tenantRepository.findByTenantId(tenantId);
	}
	
	public List<TenantInfo> getAllTenantInfo() {
		List<TenantInfo> fetchedTenantList = new ArrayList<TenantInfo>();
		
		(tenantRepository.findAll()).forEach(fetchedTenantList::add);
		
		return fetchedTenantList;
	}
	
	public List<TenantInfo> getTenantInfoByName(String tenantName) {
		return tenantRepository.modifiedFindByTenantName(tenantName.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByEmail(String tenantEmail) {
		return tenantRepository.modifiedFindByTenantEmail(tenantEmail.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByEmail(String tenantUsername, String tenantEmail) {
		return tenantRepository.modifiedFindByTenantEmail(tenantUsername.toLowerCase(), tenantEmail.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByVerification(String tenantVerified) {
		return tenantRepository.findByTenantVerified(tenantVerified.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByVerification(String tenantUsername, String tenantVerified) {
		return tenantRepository.findByTenantVerified(tenantUsername.toLowerCase(), tenantVerified.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByNameEmail(String tenantName, String tenantEmail) {
		return tenantRepository.findByTenantNameEmail(tenantName.toLowerCase(), tenantEmail.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByNameEmail(String tenantUsername, String tenantName, String tenantEmail) {
		return tenantRepository.findByTenantNameEmail(tenantUsername.toLowerCase(), tenantName.toLowerCase(), tenantEmail.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByNameVerification(String tenantName, String tenantVerified) {
		return tenantRepository.findByTenantNameVerification(tenantName.toLowerCase(), tenantVerified.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByNameVerification(String tenantUsername, String tenantName, String tenantVerified) {
		return tenantRepository.findByTenantNameVerification(tenantUsername.toLowerCase(), tenantName.toLowerCase(), tenantVerified.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByEmailVerification(String tenantEmail, String tenantVerified) {
		return tenantRepository.findByTenantEmailVerification(tenantEmail.toLowerCase(), tenantVerified.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByEmailVerification(String tenantUsername, String tenantEmail, String tenantVerified) {
		return tenantRepository.findByTenantEmailVerification(tenantUsername.toLowerCase(), tenantEmail.toLowerCase(), tenantVerified.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByNameEmailVerification(String tenantName, String tenantEmail, String tenantVerified) {
		return tenantRepository.findByTenantNameEmailVerification(tenantName.toLowerCase(), tenantEmail.toLowerCase(), tenantVerified.toLowerCase());
	}
	
	public List<TenantInfo> getTenantInfoByNameEmailVerification(String tenantUsername, String tenantName, String tenantEmail, String tenantVerified) {
		return tenantRepository.findByTenantNameEmailVerification(tenantUsername.toLowerCase(), tenantName.toLowerCase(), tenantEmail.toLowerCase(), tenantVerified.toLowerCase());
	}
}
