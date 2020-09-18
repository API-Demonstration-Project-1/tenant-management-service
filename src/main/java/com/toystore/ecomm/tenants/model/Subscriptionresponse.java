package com.toystore.ecomm.tenants.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toystore.ecomm.tenants.factory.POJOFactory;

import io.swagger.annotations.ApiModelProperty;

/**
 * Subscriptionresponse
 */
@Component

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-30T07:36:17.215Z")

public class Subscriptionresponse   {
  static {
	  POJOFactory.register("SUBSCRIPTIONRESP", Subscriptionresponse.class);
  }
  @JsonProperty("success")
  private Boolean success = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("data")
  private Data data = null;
  
  @JsonProperty("resp_data")
  private Data4 data4 = null;

  @JsonProperty("error_code")
  private Integer errorCode = null;

  public Subscriptionresponse success(Boolean success) {
    this.success = success;
    return this;
  }

  /**
   * Get success
   * @return success
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Boolean isSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Subscriptionresponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Subscriptionresponse data(Data data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(required = false, value = "")
  @Valid
  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  @ApiModelProperty(required = false, value = "")
  @Valid
  public Data4 getData4() {
	return data4;
  }

  public void setData4(Data4 data4) {
	  this.data4 = data4;
  }

public Subscriptionresponse errorCode(Integer errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Get errorCode
   * @return errorCode
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subscriptionresponse subscriptionresponse = (Subscriptionresponse) o;
    return Objects.equals(this.success, subscriptionresponse.success) &&
        Objects.equals(this.message, subscriptionresponse.message) &&
        Objects.equals(this.data, subscriptionresponse.data) &&
        Objects.equals(this.data4, subscriptionresponse.data4) &&
        Objects.equals(this.errorCode, subscriptionresponse.errorCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, data, data4, errorCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Subscriptionresponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    data4: ").append(toIndentedString(data4)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
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

