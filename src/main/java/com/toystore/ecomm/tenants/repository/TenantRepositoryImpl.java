package com.toystore.ecomm.tenants.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toystore.ecomm.tenants.model.TenantInfo;

@Repository
@Transactional(readOnly = true)
public class TenantRepositoryImpl implements TenantRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;
	
	@Override
	public TenantInfo findByTenantUsernameAndPassword(String tenantUsername, String tenantPassword) {
		
		
		// TODO Auto-generated method stub
		return null;
	}

}
