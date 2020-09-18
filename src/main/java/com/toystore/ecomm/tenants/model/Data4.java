package com.toystore.ecomm.tenants.model;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toystore.ecomm.tenants.factory.POJOFactory;

import io.swagger.annotations.ApiModelProperty;

/**
 * Data2
 */
@Component

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-30T07:36:17.215Z")

public class Data4   {
  
  static {
	  POJOFactory.register("DATA4", Data4.class);
  }
	 
  @JsonProperty("response")
  private List<Subscription> response = null;

  public Data4 response(List<Subscription> response) {
    this.response = response;
    return this;
  }

  /**
   * Get response
   * @return response
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public List<Subscription> getResponse() {
    return response;
  }

  public void setResponse(List<Subscription> response) {
    this.response = response;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Data4 data3 = (Data4) o;
    return Objects.equals(this.response, data3.response);
  }

  @Override
  public int hashCode() {
    return Objects.hash(response);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Data3 {\n");
    
    sb.append("    response: ").append(toIndentedString(response)).append("\n");
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

