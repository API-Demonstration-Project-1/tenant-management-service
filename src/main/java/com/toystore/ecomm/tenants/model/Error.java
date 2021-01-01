package com.toystore.ecomm.tenants.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toystore.ecomm.tenants.factory.UIModelFactory;

import io.swagger.annotations.ApiModelProperty;

/**
 * Error
 */
@Component

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-30T07:36:17.215Z")

public class Error   {
  static {
	  UIModelFactory.register("ERROR", Error.class);
  }
	
  @JsonProperty("message")
  private String message = null;

  @JsonProperty("success")
  private Boolean success = null;

  @JsonProperty("data")
  private Object data = null;

  @JsonProperty("errorCode")
  private Integer errorCode = null;

  public Error message(String message) {
    this.message = message;
    return this;
  }

  /**
   * This is the Verification-related message
   * @return message
  **/
  @ApiModelProperty(required = true, value = "This is the Verification-related message")
  @NotNull


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Error success(Boolean success) {
    this.success = success;
    return this;
  }

  /**
   * True or False
   * @return success
  **/
  @ApiModelProperty(required = true, value = "True or False")
  @NotNull


  public Boolean isSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Error data(Object data) {
    this.data = data;
    return this;
  }

  /**
   * This is any data
   * @return data
  **/
  @ApiModelProperty(value = "This is any data")


  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Error errorCode(Integer errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * This is gives error code info
   * @return errorCode
  **/
  @ApiModelProperty(value = "This is gives error code info")


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
    Error error = (Error) o;
    return Objects.equals(this.message, error.message) &&
        Objects.equals(this.success, error.success) &&
        Objects.equals(this.data, error.data) &&
        Objects.equals(this.errorCode, error.errorCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, success, data, errorCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

