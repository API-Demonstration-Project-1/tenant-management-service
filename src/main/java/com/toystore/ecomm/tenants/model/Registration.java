package com.toystore.ecomm.tenants.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.toystore.ecomm.tenants.factory.UIModelFactory;

import io.swagger.annotations.ApiModelProperty;

/**
 * Registration
 */
@Component

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-30T07:36:17.215Z")

public class Registration   {

  static {
	  UIModelFactory.register("REGISTRATION", Registration.class);
  }
	 
  @JsonView(Views.Internal.class)
  @JsonProperty("tenantId")
  private String tenantId = null;
  
  @JsonView(Views.Public.class)
  @JsonProperty("tenantName")
  private String tenantName = null;

  @JsonView(Views.Public.class)
  @JsonProperty("tenantEmail")
  private String tenantEmail = null;

  @JsonView(Views.Public.class)
  @JsonProperty("tenantUsername")
  private String tenantUsername = null;

  @JsonView(Views.Internal.class)
  @JsonProperty("tenantPassword")
  private String tenantPassword = null;

  @JsonView(Views.Public.class)
  @JsonProperty("tenantVerified")
  private String tenantVerified = null;

  public String getTenantId() {
	return tenantId;
}

  	@ApiModelProperty(required = false, value = "This is the ID of the Tenant")
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public Registration tenantName(String tenantName) {
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

  public Registration tenantEmail(String tenantEmail) {
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

  public Registration tenantUsername(String tenantUsername) {
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

  public Registration tenantPassword(String tenantPassword) {
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

  public Registration tenantVerified(String tenantVerified) {
    this.tenantVerified = tenantVerified;
    return this;
  }

  /**
   * This indicates whether Tenant Registration is verified or not
   * @return tenantVerified
  **/
  @ApiModelProperty(required = false, value = "This indicates whether Tenant Registration is verified or not")


  public String getTenantVerified() {
    return tenantVerified;
  }

  public void setTenantVerified(String tenantVerified) {
    this.tenantVerified = tenantVerified;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Registration registration = (Registration) o;
    return Objects.equals(this.tenantId, registration.tenantId) &&
    	Objects.equals(this.tenantName, registration.tenantName) &&
        Objects.equals(this.tenantEmail, registration.tenantEmail) &&
        Objects.equals(this.tenantUsername, registration.tenantUsername) &&
        Objects.equals(this.tenantPassword, registration.tenantPassword) &&
        Objects.equals(this.tenantVerified, registration.tenantVerified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tenantName, tenantEmail, tenantUsername, tenantPassword, tenantVerified);
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

