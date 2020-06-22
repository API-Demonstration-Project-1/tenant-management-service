package com.toystore.ecomm.tenants.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Registration
 */
@Validated

@Entity
@Table(name = "TENANT")
public class TenantInfo   {
  @Id
  @Column(name = "TENANT_ID", nullable = false)
  private Integer tenantId = null;
  
  @Column(name = "TENANT_NAME", nullable = false)
  private String tenantName = null;

  @Column(name = "TENANT_EMAIL", nullable = false)
  private String tenantEmail = null;

  @Column(name = "TENANT_USERNAME", nullable = false)
  private String tenantUsername = null;

  @Column(name = "TENANT_PASSWORD", nullable = false)
  private String tenantPassword = null;

  @Column(name = "TENANT_VERIFIED", nullable = false)
  private String tenantVerified = null;

  @Column(name = "CREATED_TS", nullable = false)
  private Timestamp createdTS = null;
  
  @Column(name = "LAST_UPDATED_TS", nullable = false)
  private Timestamp lastUpdatedTS = null;
  
  @Column(name = "CREATED_BY", nullable = false)
  private String createdBy = null;
  
  @OneToMany(mappedBy = "tenant")
  private List<SubscriptionInfo> subscriptionInfoList;
  
  public Integer getTenantId() {
	return tenantId;
}

public void setTenantId(Integer tenanId) {
	this.tenantId = tenanId;
}

public TenantInfo tenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  /**
   * This is unique name for each tenant
   * @return tenantName
  **/
  @ApiModelProperty(required = true, value = "This is unique name for each tenant")
  @NotNull

  
  public String getTenantName() {
    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  public TenantInfo tenantEmail(String tenantEmail) {
    this.tenantEmail = tenantEmail;
    return this;
  }

  /**
   * This is the email of the tenant
   * @return tenantEmail
  **/
  @ApiModelProperty(required = true, value = "This is the email of the tenant")
  @NotNull


  public String getTenantEmail() {
    return tenantEmail;
  }

  public void setTenantEmail(String tenantEmail) {
    this.tenantEmail = tenantEmail;
  }

  public TenantInfo tenantUsername(String tenantUsername) {
    this.tenantUsername = tenantUsername;
    return this;
  }

  /**
   * This is the Username for the login
   * @return tenantUsername
  **/
  @ApiModelProperty(required = true, value = "This is the Username for the login")
  @NotNull


  public String getTenantUsername() {
    return tenantUsername;
  }

  public void setTenantUsername(String tenantUsername) {
    this.tenantUsername = tenantUsername;
  }

  public TenantInfo tenantPassword(String tenantPassword) {
    this.tenantPassword = tenantPassword;
    return this;
  }

  /**
   * This is the Password for the login
   * @return tenantPassword
  **/
  @ApiModelProperty(required = true, value = "This is the Password for the login")
  @NotNull


  public String getTenantPassword() {
    return tenantPassword;
  }

  public void setTenantPassword(String tenantPassword) {
    this.tenantPassword = tenantPassword;
  }

  public TenantInfo tenantVerified(String tenantVerified) {
    this.tenantVerified = tenantVerified;
    return this;
  }

  /**
   * This indicates whether Tenant Registration is verified or not
   * @return tenantVerified
  **/
  @ApiModelProperty(required = false, value = "This indicates whether Tenant Registration is verified or not")
  //@NotNull


  public String getTenantVerified() {
    return tenantVerified;
  }

  public void setTenantVerified(String tenantVerified) {
    this.tenantVerified = tenantVerified;
  }

  public Timestamp getCreatedTS() {
	return createdTS;
}

public void setCreatedTS(Timestamp createdTS) {
	this.createdTS = createdTS;
}

public Timestamp getLastUpdatedTS() {
	return lastUpdatedTS;
}

public void setLastUpdatedTS(Timestamp lastUpdatedTS) {
	this.lastUpdatedTS = lastUpdatedTS;
}

public String getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}

public TenantInfo withId(Integer id){
	    this.setTenantId(id);;
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
    TenantInfo registration = (TenantInfo) o;
    return
    	Objects.equals(this.tenantId, registration.tenantId) &&
    	Objects.equals(this.tenantName, registration.tenantName) &&
        Objects.equals(this.tenantEmail, registration.tenantEmail) &&
        Objects.equals(this.tenantUsername, registration.tenantUsername) &&
        Objects.equals(this.tenantPassword, registration.tenantPassword) &&
        Objects.equals(this.tenantVerified, registration.tenantVerified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tenantId, tenantName, tenantEmail, tenantUsername, tenantPassword, tenantVerified);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Registration {\n");
    
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    tenantName: ").append(toIndentedString(tenantName)).append("\n");
    sb.append("    tenantEmail: ").append(toIndentedString(tenantEmail)).append("\n");
    sb.append("    tenantUsername: ").append(toIndentedString(tenantUsername)).append("\n");
    sb.append("    tenantPassword: ").append(toIndentedString(tenantPassword)).append("\n");
    sb.append("    tenantVerified: ").append(toIndentedString(tenantVerified)).append("\n");
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

