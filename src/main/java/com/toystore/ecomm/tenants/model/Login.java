package com.toystore.ecomm.tenants.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toystore.ecomm.tenants.factory.POJOFactory;

import io.swagger.annotations.ApiModelProperty;

/**
 * Login
 */
@Component

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-30T07:36:17.215Z")

public class Login   {

	  static {
		  POJOFactory.register("LOGIN", Login.class);
	  }
  
  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("userPassword")
  private String userPassword = null;

  public Login userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * This is the Login User Name
   * @return userName
  **/
  @ApiModelProperty(required = true, value = "This is the Login User Name")
  @NotNull


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Login userPassword(String userPassword) {
    this.userPassword = userPassword;
    return this;
  }

  /**
   * This is the Login User Password
   * @return userPassword
  **/
  @ApiModelProperty(required = true, value = "This is the Login User Password")
  @NotNull


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Login login = (Login) o;
    return Objects.equals(this.userName, login.userName) &&
        Objects.equals(this.userPassword, login.userPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, userPassword);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Login {\n");
    
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    userPassword: ").append(toIndentedString(userPassword)).append("\n");
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

