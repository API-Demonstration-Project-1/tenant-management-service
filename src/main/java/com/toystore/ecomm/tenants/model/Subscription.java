package com.toystore.ecomm.tenants.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subscription
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-30T07:36:17.215Z")

public class Subscription   {
  @JsonProperty("planName")
  private String planName = null;
  
  @JsonProperty("tenantName")
  private String tenantName = null;

  @JsonProperty("renewalType")
  private String renewalType = null;

  @JsonProperty("startDate")
  private String startDate = null;

  @JsonProperty("endDate")
  private String endDate = null;

  @JsonProperty("isValid")
  private String isValid = null;

  public Subscription planName(String planName) {
    this.planName = planName;
    return this;
  }

  /**
   * This is the Plan Name
   * @return planName
  **/
  @ApiModelProperty(required = true, value = "This is the Plan Name")
  @NotNull


  public String getPlanName() {
    return planName;
  }

  public void setPlanName(String planName) {
    this.planName = planName;
  }
  
  public Subscription tenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }
  
  /**
  * This is the Tenant Name
  * @return tenantName
 **/
 @ApiModelProperty(required = true, value = "This is the Tenant Name")
 @NotNull

  public String getTenantName() {
	return tenantName;
}

public void setTenantName(String tenantName) {
	this.tenantName = tenantName;
}

  public Subscription renewalType(String renewalType) {
    this.renewalType = renewalType;
    return this;
  }

  /**
   * This is the Renewal Type
   * @return renewalType
  **/
  @ApiModelProperty(required = true, value = "This is the Renewal Type")
  @NotNull


  public String getRenewalType() {
    return renewalType;
  }

  public void setRenewalType(String renewalType) {
    this.renewalType = renewalType;
  }

  public Subscription startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * This indicates the Start Date of Subscription
   * @return startDate
  **/
  @ApiModelProperty(required = true, value = "This indicates the Start Date of Subscription")
  @NotNull


  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public Subscription endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * This indicates the End Date of Subscription
   * @return endDate
  **/
  @ApiModelProperty(required = true, value = "This indicates the End Date of Subscription")
  @NotNull


  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public Subscription isValid(String isValid) {
    this.isValid = isValid;
    return this;
  }

  /**
   * This indicates whether the Current Subscription is Valid or Not
   * @return isValid
  **/
  @ApiModelProperty(required = true, value = "This indicates whether the Current Subscription is Valid or Not")
  @NotNull


  public String getIsValid() {
    return isValid;
  }

  public void setIsValid(String isValid) {
    this.isValid = isValid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subscription subscription = (Subscription) o;
    return Objects.equals(this.planName, subscription.planName) &&
    	Objects.equals(this.tenantName, subscription.tenantName) &&
        Objects.equals(this.renewalType, subscription.renewalType) &&
        Objects.equals(this.startDate, subscription.startDate) &&
        Objects.equals(this.endDate, subscription.endDate) &&
        Objects.equals(this.isValid, subscription.isValid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(planName, tenantName, renewalType, startDate, endDate, isValid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Subscription {\n");
    
    sb.append("    tenantName: ").append(toIndentedString(tenantName)).append("\n");
    sb.append("    planName: ").append(toIndentedString(planName)).append("\n");
    sb.append("    renewalType: ").append(toIndentedString(renewalType)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
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

