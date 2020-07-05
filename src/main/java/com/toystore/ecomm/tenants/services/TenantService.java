package com.toystore.ecomm.tenants.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.toystore.ecomm.tenants.repository.TenantRepository;
import com.toystore.ecomm.tenants.util.DBSchemaCreator;
import com.toystore.ecomm.tenants.util.RandomStringGenerator;
import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.model.TenantDBInfo;
import com.toystore.ecomm.tenants.model.TenantInfo;

@Service
public class TenantService {

	@Autowired
	private TenantRepository tenantRepository;
	
	@Value("${spring.database.driver-class-name}")
	private String driverName;
		
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
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
		
		return tenantRepository.save(tenantInfo);
	}

	public void updateTenantInfo(TenantInfo existingTenantInfo) {
		existingTenantInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
		existingTenantInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
		
		tenantRepository.save(existingTenantInfo);
	}
	
	public TenantInfo updateTenantInfoPostVerification(Integer tenantId) throws Exception {
		TenantInfo existingTenantInfo = tenantRepository.findByTenantId(tenantId);
		
		existingTenantInfo.setTenantVerified(PTMSConstants.YES_VALUE);
		existingTenantInfo.setTenantVerificationCode(null);
		existingTenantInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
		existingTenantInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
		
		DBSchemaCreator.createDbSchemaTable(driverName, dbUrl, dbUsername, dbPassword, existingTenantInfo.getTenantUsername());
		
		TenantDBInfo tenantDBInfo = new TenantDBInfo();
		tenantDBInfo.withId((new Random()).nextInt(1000));
		tenantDBInfo.setTenantId(tenantId);
		tenantDBInfo.setTenantDBUrl(dbUrlPartial + existingTenantInfo.getTenantUsername() + "?useSSL=false");
		tenantDBInfo.setTenantDBName(existingTenantInfo.getTenantUsername());
		tenantDBInfo.setTenantDBUsername(dbUsername);
		tenantDBInfo.setTenantDBPassword(dbPassword);
		
		tenantDBInfo.setTenantInfo(existingTenantInfo);
		
		existingTenantInfo.setTenantDBInfo(tenantDBInfo);
		
		return tenantRepository.save(existingTenantInfo);
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
	
	public void removeTenantInfo(Integer tenantId) {
		tenantRepository.deleteById(tenantId);
	}
	
	public TenantInfo getTenantInfoByTenantId(Integer tenantId) {
		return tenantRepository.findByTenantId(tenantId);
	}
	
	public List<TenantInfo> getAllTenantInfo() {
		List<TenantInfo> fetchedTenantList = new ArrayList<TenantInfo>();
		
		(tenantRepository.findAll()).forEach(fetchedTenantList::add);
		
		return fetchedTenantList;
	}
}
