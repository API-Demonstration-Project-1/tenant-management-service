package com.toystore.ecomm.tenants.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toystore.ecomm.tenants.repository.TenantRepository;
import com.toystore.ecomm.tenants.util.RandomStringGenerator;
import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.model.TenantDBInfo;
import com.toystore.ecomm.tenants.model.TenantInfo;

@Service
public class TenantService {

	@Autowired
	private TenantRepository tenantRepository;

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

	public TenantInfo updateTenantInfo(Integer tenantId) {
		TenantInfo existingTenantInfo = tenantRepository.findByTenantId(tenantId).get(0);
		
		existingTenantInfo.setTenantVerified(PTMSConstants.YES_VALUE);
		existingTenantInfo.setTenantVerificationCode(null);
		existingTenantInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
		existingTenantInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
		

		TenantDBInfo tenantDBInfo = new TenantDBInfo();
		tenantDBInfo.withId((new Random()).nextInt(1000));
		tenantDBInfo.setTenantId(tenantId);
		tenantDBInfo.setTenantDBUrl("jdbc:mysql://ec2-3-6-82-226.ap-south-1.compute.amazonaws.com:3306/rapidminer_server?useSSL=false");
		tenantDBInfo.setTenantDBName("rapidminer_server");
		tenantDBInfo.setTenantDBUsername("authuser");
		tenantDBInfo.setTenantDBPassword("authuser");
		
		tenantDBInfo.setTenantInfo(existingTenantInfo);
		
		existingTenantInfo.setTenantDBInfo(tenantDBInfo);
		
		return tenantRepository.save(existingTenantInfo);
	}
	
	public boolean isTenantUsernameUnique(String tenantUsername) {
		return ((tenantRepository.findByTenantUsername(tenantUsername).size()) > 0) ? false : true;
	}
	
	public boolean isTenantEmailUnique(String tenantEmail) {
		return ((tenantRepository.findByTenantEmail(tenantEmail).size()) > 0) ? false : true;
	}
	
	public boolean isTenantRegistered(Integer tenantId, String code) {
		
		if (tenantRepository.findByTenantVerificationCode(code).size() > 0) {
			return (((TenantInfo)(tenantRepository.findByTenantVerificationCode(code).get(0))).getTenantId()).equals(tenantId) ? true : false;
		} else {
			return false;
		}
	}
	
	public boolean isTenantVerified(Integer tenantId) {
		return (((tenantRepository.findByTenantId(tenantId)).get(0)).getTenantVerified()).equals(PTMSConstants.YES_VALUE) ? true : false;
	}
	
	public TenantInfo getTenantInfoByUsername(String tenantUsername) {
		List<TenantInfo> tenantInfoList = tenantRepository.findByTenantUsername(tenantUsername);
		
		return tenantInfoList.size() == 0 ? null : tenantInfoList.get(0);
	}
}
