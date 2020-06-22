package com.toystore.ecomm.tenants.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toystore.ecomm.tenants.repository.TenantRepository;
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

	public void saveTenantInfo(TenantInfo tenantInfo) {
		tenantInfo.withId((new Random()).nextInt(1000));
		tenantRepository.save(tenantInfo);
	}

	public TenantInfo updateTenantInfo(TenantInfo tenantInfo) {
		TenantInfo existingTenantInfo = tenantRepository.findByTenantId(tenantInfo.getTenantId()).get(0);
		
		existingTenantInfo.setTenantVerified("Y");
		return tenantRepository.save(existingTenantInfo);
	}
}
