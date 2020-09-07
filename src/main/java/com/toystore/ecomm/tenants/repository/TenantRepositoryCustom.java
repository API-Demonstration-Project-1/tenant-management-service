package com.toystore.ecomm.tenants.repository;

public interface TenantRepositoryCustom {
	public void createDbSchemaNTables(String newDbName);
	public void dropDbSchema(String existingDbName);
}
