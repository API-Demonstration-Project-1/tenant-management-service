package com.toystore.ecomm.tenants.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.toystore.ecomm.tenants.model.TenantInfo;

@Repository
public interface TenantRepository extends CrudRepository<TenantInfo, Integer>, TenantRepositoryCustom {
	TenantInfo findByTenantId(Integer tenantId);
	
	List<TenantInfo> findByTenantName(String tenantName);
	List<TenantInfo> findByTenantUsername(String tenantUsername);
	List<TenantInfo> findByTenantEmail(String tenantEmail);
	List<TenantInfo> findByTenantVerified(String tenantVerified);
	List<TenantInfo> findByTenantVerificationCode(String tenantVerificationCode);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantName) = :tenantName AND LOWER(t.tenantVerified) = :tenantVerified")
	List<TenantInfo> findByTenantVerified(@Param("tenantName") String tenantName,  @Param("tenantVerified") String tenantVerified);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantName) LIKE %:tenantName%")
	List<TenantInfo> modifiedFindByTenantName(@Param("tenantName") String tenantName);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantEmail) LIKE %:tenantEmail%")
	List<TenantInfo> modifiedFindByTenantEmail(@Param("tenantEmail") String tenantEmail);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantName) = :tenantName AND LOWER(t.tenantEmail) LIKE %:tenantEmail%")
	List<TenantInfo> modifiedFindByTenantEmail(@Param("tenantName") String tenantName, @Param("tenantEmail") String tenantEmail);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantName) LIKE %:tenantName% AND LOWER(t.tenantEmail) LIKE %:tenantEmail%")
	List<TenantInfo> findByTenantNameEmail(@Param("tenantName") String tenantName, @Param("tenantEmail") String tenantEmail);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantUsername) = :tenantUsername AND LOWER(t.tenantName) LIKE %:tenantName% AND LOWER(t.tenantEmail) LIKE %:tenantEmail%")
	List<TenantInfo> findByTenantNameEmail(@Param("tenantUsername") String tenantUsername, @Param("tenantName") String tenantName, @Param("tenantEmail") String tenantEmail);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantName) LIKE %:tenantName% AND LOWER(t.tenantVerified) = :tenantVerified")
	List<TenantInfo> findByTenantNameVerification(@Param("tenantName") String tenantName, @Param("tenantVerified") String tenantVerified);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantUsername) = :tenantUsername AND LOWER(t.tenantName) LIKE %:tenantName% AND LOWER(t.tenantVerified) = :tenantVerified")
	List<TenantInfo> findByTenantNameVerification(@Param("tenantUsername") String tenantUsername, @Param("tenantName") String tenantName, @Param("tenantVerified") String tenantVerified);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantEmail) LIKE %:tenantEmail% AND LOWER(t.tenantVerified) = :tenantVerified")
	List<TenantInfo> findByTenantEmailVerification(@Param("tenantEmail") String tenantEmail, @Param("tenantVerified") String tenantVerified);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantUsername) = :tenantUsername AND LOWER(t.tenantEmail) LIKE %:tenantEmail% AND LOWER(t.tenantVerified) = :tenantVerified")
	List<TenantInfo> findByTenantEmailVerification(@Param("tenantUsername") String tenantUsername, @Param("tenantEmail") String tenantEmail, @Param("tenantVerified") String tenantVerified);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantName) LIKE %:tenantName% AND LOWER(t.tenantEmail) LIKE %:tenantEmail% AND LOWER(t.tenantVerified) = :tenantVerified")
	List<TenantInfo> findByTenantNameEmailVerification(@Param("tenantName") String tenantName, @Param("tenantEmail") String tenantEmail, @Param("tenantVerified") String tenantVerified);
	
	@Query("SELECT t FROM TenantInfo t WHERE LOWER(t.tenantUsername) = :tenantUsername AND LOWER(t.tenantName) LIKE %:tenantName% AND LOWER(t.tenantEmail) LIKE %:tenantEmail% AND LOWER(t.tenantVerified) = :tenantVerified")
	List<TenantInfo> findByTenantNameEmailVerification(@Param("tenantUsername") String tenantUsername, @Param("tenantName") String tenantName, @Param("tenantEmail") String tenantEmail, @Param("tenantVerified") String tenantVerified);
}
