package com.toystore.ecomm.tenants.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Registration
 */
@Validated

@Entity
@Table(name = "TENANT_DB")
public class TenantDBInfo   {
  @Id
  @Column(name = "TENANT_DB_ID", nullable = false)
  private Integer tenantDBId = null;
  
  @Column(name = "TENANT_ID", nullable = false)
  private Integer tenantId = null;

  @Column(name = "TENANT_DB_URL", nullable = false)
  private String tenantDBUrl = null;
  
  @Column(name = "TENANT_DB_NAME", nullable = false)
  private String tenantDBName = null;


  @Column(name = "TENANT_DB_USERNAME", nullable = false)
  private String tenantDBUsername = null;

  @Column(name = "TENANT_DB_PASSWORD", nullable = false)
  private String tenantDBPassword = null;
  
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  //@MapsId
  @JoinColumn(name = "TENANT_ID", nullable = false, insertable = false, updatable = false)
  private TenantInfo tenantInfo;

  public Integer getTenantDBId() {
	return tenantDBId;
}

public void setTenantDBId(Integer tenantDBId) {
	this.tenantDBId = tenantDBId;
}

public void setTenantInfo(TenantInfo tenantInfo) {
	this.tenantInfo = tenantInfo;
}

	/*
	 * @Column(name = "CREATED_TS", nullable = false) private Timestamp createdTS =
	 * null;
	 * 
	 * @Column(name = "LAST_UPDATED_TS", nullable = false) private Timestamp
	 * lastUpdatedTS = null;
	 * 
	 * @Column(name = "CREATED_BY", nullable = false) private String createdBy =
	 * null;
	 */

public String getTenantDBName() {
	return tenantDBName;
}

public void setTenantDBName(String tenantDBName) {
	this.tenantDBName = tenantDBName;
}

	/*
	 * public TenantInfo getTenantInfo() { return tenantInfo; }
	 * 
	 * public void setTenantInfo(TenantInfo tenantInfo) { this.tenantInfo =
	 * tenantInfo; }
	 */

	/*
	 * public void setTenantId(Integer tenanId) { this.tenantId = tenanId; }
	 */
public String getTenantDBUrl() {
	return tenantDBUrl;
}

public void setTenantDBUrl(String tenantDBUrl) {
	this.tenantDBUrl = tenantDBUrl;
}

public String getTenantDBUsername() {
	return tenantDBUsername;
}

public void setTenantDBUsername(String tenantDBUsername) {
	this.tenantDBUsername = tenantDBUsername;
}

public String getTenantDBPassword() {
	return tenantDBPassword;
}

public void setTenantDBPassword(String tenantDBPassword) {
	this.tenantDBPassword = tenantDBPassword;
}

	/*
	 * public Integer getTenantId() { return tenantId; }
	 */

	/*
	 * public Timestamp getCreatedTS() { return createdTS; }
	 * 
	 * public void setCreatedTS(Timestamp createdTS) { this.createdTS = createdTS; }
	 * 
	 * public Timestamp getLastUpdatedTS() { return lastUpdatedTS; }
	 * 
	 * public void setLastUpdatedTS(Timestamp lastUpdatedTS) { this.lastUpdatedTS =
	 * lastUpdatedTS; }
	 * 
	 * public String getCreatedBy() { return createdBy; }
	 * 
	 * public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
	 */
	
	public Integer getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	
	public TenantDBInfo withId(Integer id){
	    this.setTenantDBId(id);;
	    return this;
	}
	
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TenantDBInfo tenantDBInfo = (TenantDBInfo) o;
    return
    	Objects.equals(this.tenantDBId, tenantDBInfo.tenantDBId) &&
    	Objects.equals(this.tenantDBUrl, tenantDBInfo.tenantDBUrl) &&
    	Objects.equals(this.tenantDBName, tenantDBInfo.tenantDBName) &&
        Objects.equals(this.tenantDBUsername, tenantDBInfo.tenantDBUsername) &&
        Objects.equals(this.tenantDBPassword, tenantDBInfo.tenantDBPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tenantDBId, tenantDBUrl, tenantDBUsername, tenantDBPassword);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    //sb.append("class Registration {\n");
    
    sb.append("    tenantDBId: ").append(toIndentedString(tenantDBId)).append("\n");
    sb.append("    tenantDBUrl: ").append(toIndentedString(tenantDBUrl)).append("\n");
    sb.append("    tenantDBName: ").append(toIndentedString(tenantDBName)).append("\n");
    sb.append("    tenantDBUsername: ").append(toIndentedString(tenantDBUsername)).append("\n");
    sb.append("    tenantDBPassword: ").append(toIndentedString(tenantDBPassword)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

