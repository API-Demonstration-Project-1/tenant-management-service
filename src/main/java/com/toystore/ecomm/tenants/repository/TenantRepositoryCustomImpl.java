package com.toystore.ecomm.tenants.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.toystore.ecomm.tenants.exception.DatabaseCreationException;

public class TenantRepositoryCustomImpl implements TenantRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void createDbSchemaNTables(String newDbName) {
		try {
				// Create DB Schema
				Query query = em.createNativeQuery("CREATE DATABASE IF NOT EXISTS " + newDbName);
				int res = query.executeUpdate();
				if (res < 0) {
				    throw new DatabaseCreationException("Unable to create DB");
				}
				
				// Create DB Tables such as 'USER' & 'ADDRESS'
				query = em.createNativeQuery("CREATE TABLE IF NOT EXISTS " + newDbName + ".USER (USER_ID int(10) NOT NULL, FIRST_NAME varchar(45) DEFAULT NULL, LAST_NAME varchar(45) DEFAULT NULL, MIDDLE_NAME varchar(45) DEFAULT NULL, EMAIL_ID varchar(45) DEFAULT NULL, PRIMARY_CONTACT varchar(10) DEFAULT NULL, PRIMARY KEY (USER_ID))");
				res = query.executeUpdate();
				if (res < 0) {
				    throw new DatabaseCreationException("Unable to create table 'USER'");
				}
				
				query = em.createNativeQuery("CREATE TABLE IF NOT EXISTS " + newDbName + ".ADDRESS (ADDRESS_ID int(11) NOT NULL AUTO_INCREMENT, ADDRESS_TYPE char(1) DEFAULT NULL, USER_ID int(11) DEFAULT NULL, LINE1 varchar(45) DEFAULT NULL, LINE2 varchar(45) DEFAULT NULL, LINE3 varchar(45) DEFAULT NULL, DISTRICT varchar(45) DEFAULT NULL, STATE varchar(45) DEFAULT NULL, COUNTRY varchar(45) DEFAULT NULL, ZIP_CODE varchar(10) DEFAULT NULL, PRIMARY KEY (ADDRESS_ID), UNIQUE KEY ADDRESS_ID_UNIQUE (ADDRESS_ID), KEY USER_ID_idx (USER_ID), CONSTRAINT USER_ID FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID) ON DELETE NO ACTION ON UPDATE NO ACTION);");
				res = query.executeUpdate();
				if (res < 0) {
				    throw new DatabaseCreationException("Unable to create table 'ADDRESS'");
				}
		} finally {
			em.clear();
			em.close();
		}
	}
	
	@Transactional
	public void dropDbSchema(String existingDbName) {
		try {
				Query query = em.createNativeQuery("DROP DATABASE IF EXISTS " + existingDbName);
				int res = query.executeUpdate();
				
				if (res < 0) {
				    throw new DatabaseCreationException("Unable to drop DB: " + existingDbName);
				}
		} finally {
			em.clear();
			em.close();
		}
	}
}
