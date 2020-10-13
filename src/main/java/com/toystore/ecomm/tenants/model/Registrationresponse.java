package com.toystore.ecomm.tenants.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toystore.ecomm.tenants.factory.UIModelFactory;

import io.swagger.annotations.ApiModelProperty;

/**
 * Registrationresponse
 */
@Component

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-30T07:36:17.215Z")

public class Registrationresponse   {
	
  static {
	  UIModelFactory.register("REGISTRATIONRESP", Registrationresponse.class);
  }
	
  @JsonProperty("success")
  private Boolean success = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("data")
  private Data data = null;
  
  @JsonProperty("resp_data")
  private Data3 data3 = null;

  @JsonProperty("error_code")
  private Integer errorCode = null;

  public Registrationresponse success(Boolean success) {
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

  public Registrationresponse message(String message) {
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

  public Registrationresponse data(Data data) {
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
  
  public Registrationresponse data3(Data3 data3) {
	    this.data3 = data3;
	    return this;
  }


  @ApiModelProperty(required = false, value = "")
  @Valid
  public Data3 getData3() {
	return data3;
  }

  public void setData3(Data3 data3) {
	this.data3 = data3;
  }

  public Registrationresponse errorCode(Integer errorCode) {
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
    Registrationresponse registrationresponse = (Registrationresponse) o;
    return Objects.equals(this.success, registrationresponse.success) &&
        Objects.equals(this.message, registrationresponse.message) &&
        Objects.equals(this.data, registrationresponse.data) &&
        Objects.equals(this.data3, registrationresponse.data3) &&
        Objects.equals(this.errorCode, registrationresponse.errorCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, data, errorCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Registrationresponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    data3: ").append(toIndentedString(data3)).append("\n");
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

