package com.toystore.ecomm.tenants.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.validation.annotation.Validated;

import com.toystore.ecomm.tenants.factory.POJOFactory;

/**
 * Registration
 */
@Validated

@Entity
@Table(name = "TENANT_ROLE")
public class TenantRoleInfo   {

  static {
        POJOFactory.register("TENANTROLEINFO", TenantRoleInfo.class);
  }
  
  @Id
  @Column(name = "ROLE_ID", nullable = false)
  private Integer roleId = null;
  
  @Column(name = "ROLE_NAME", nullable = false)
  private String roleName = null;

  @Column(name = "ROLE_DESC", nullable = false)
  private String roleDesc = null;
  
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


	/*
	 * public TenantInfo getTenantInfo() { return tenantInfo; }
	 * 
	 * public void setTenantInfo(TenantInfo tenantInfo) { this.tenantInfo =
	 * tenantInfo; }
	 */

	/*
	 * public void setTenantId(Integer tenanId) { this.tenantId = tenanId; }
	 */


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
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public TenantRoleInfo withId(Integer id){
	    this.setRoleId(id);;
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
    TenantRoleInfo tenantRoleInfo = (TenantRoleInfo) o;
    return
    	Objects.equals(this.roleId, tenantRoleInfo.roleId) &&
    	Objects.equals(this.roleName, tenantRoleInfo.roleName) &&
    	Objects.equals(this.roleDesc, tenantRoleInfo.roleDesc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roleId, roleName, roleDesc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    //sb.append("class Registration {\n");
    
    sb.append("    roleId: ").append(toIndentedString(roleId)).append("\n");
    sb.append("    roleName: ").append(toIndentedString(roleName)).append("\n");
    sb.append("    roleDesc: ").append(toIndentedString(roleDesc)).append("\n");
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

