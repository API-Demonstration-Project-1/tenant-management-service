package com.toystore.ecomm.tenants.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toystore.ecomm.tenants.factory.UIModelFactory;

import io.swagger.annotations.ApiModelProperty;

/**
 * Data2
 */
@Component

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-30T07:36:17.215Z")

public class Data2   {
  
  static {
	  UIModelFactory.register("DATA2", Data2.class);
  }
	
  @JsonProperty("jwttoken")
  private String jwttoken = null;

  public Data2 jwttoken(String jwttoken) {
    this.jwttoken = jwttoken;
    return this;
  }

  /**
   * Get jwttoken
   * @return jwttoken
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getJwttoken() {
    return jwttoken;
  }

  public void setJwttoken(String jwttoken) {
    this.jwttoken = jwttoken;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Data2 data2 = (Data2) o;
    return Objects.equals(this.jwttoken, data2.jwttoken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jwttoken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Data2 {\n");
    
    sb.append("    jwttoken: ").append(toIndentedString(jwttoken)).append("\n");
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

