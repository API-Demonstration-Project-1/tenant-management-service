/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.14).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.toystore.ecomm.tenants.controller;

import com.toystore.ecomm.tenants.model.Error;
import com.toystore.ecomm.tenants.model.Registration;
import com.toystore.ecomm.tenants.model.Registrationresponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-25T11:20:36.509Z")

@Api(value = "registration", description = "the registration API")
@RequestMapping(value = "/ptms")
public interface RegistrationApi {

    @ApiOperation(value = "RegistrationByTenantId_DELETE", nickname = "registrationByTenantIdDELETE", notes = "Delete a Registration by registrationId", tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = ""),
        @ApiResponse(code = 404, message = "TODO: Add error message", response = Error.class) })
    @RequestMapping(value = "/registration/{tenantId}",
        produces = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Registrationresponse> registrationByTenantIdDELETE(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId);


    @ApiOperation(value = "RegistrationByTenantId_GET", nickname = "registrationByTenantIdGET", notes = "Get a Registration by registrationId", response = Registration.class, tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = Registration.class),
        @ApiResponse(code = 404, message = "TODO: Add error message", response = Error.class) })
    @RequestMapping(value = "/registration/{tenantId}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Registration> registrationByTenantIdGET(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId);


	/*
	 * @ApiOperation(value = "RegistrationByTenantId_POST", nickname =
	 * "registrationByTenantIdPOST", notes =
	 * "Registration using Username & Password", response = Registration.class,
	 * tags={ "registration", })
	 * 
	 * @ApiResponses(value = {
	 * 
	 * @ApiResponse(code = 200, message = "", response = Registration.class),
	 * 
	 * @ApiResponse(code = 400, message = "TODO: Add error message", response =
	 * Error.class) })
	 * 
	 * @RequestMapping(value = "/registration/{tenantId}", produces = {
	 * "application/json" }, consumes = { "application/json" }, method =
	 * RequestMethod.POST) ResponseEntity<Registration>
	 * registrationByTenantIdPOST(@ApiParam(value =
	 * "",required=true) @PathVariable("tenantId") String tenantId);
	 */


    @ApiOperation(value = "RegistrationByTenantId_PUT", nickname = "registrationByTenantIdPUT", notes = "Update a Registration by registrationId", response = Registration.class, tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = Registration.class),
        @ApiResponse(code = 404, message = "TODO: Add error message", response = Error.class) })
    @RequestMapping(value = "/registration/{tenantId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Registrationresponse> registrationByTenantIdPUT(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Registration body);


    @ApiOperation(value = "RegistrationEmailverificationByTenantId_GET", nickname = "registrationEmailverificationByTenantIdGET", notes = "", response = Registrationresponse.class, tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = Registrationresponse.class) })
    @RequestMapping(value = "/registration/{tenantId}/emailverification",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Registrationresponse> registrationEmailverificationByTenantIdGET(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId,@NotNull @ApiParam(value = "This is the Verification Code for this tenant", required = true) @Valid @RequestParam(value = "code", required = true) String code);


    @ApiOperation(value = "Registration_GET", nickname = "registrationGET", notes = "Get a list of Registration", response = Registration.class, responseContainer = "List", tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = Registration.class, responseContainer = "List") })
    @RequestMapping(value = "/registration",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Registration>> registrationGET(@ApiParam(value = "Get List of Tenant Info based on a given Tenant Name") @Valid @RequestParam(value = "tenantName", required = false) String tenantName,@ApiParam(value = "Get List of Tenant Info based on a given Tenant Email") @Valid @RequestParam(value = "tenantEmail", required = false) String tenantEmail,@ApiParam(value = "Get List of Tenant Info based on Verified or Not Verified") @Valid @RequestParam(value = "tenantVerified", required = false) String tenantVerified);


    @ApiOperation(value = "Registration_POST", nickname = "registrationPOST", notes = "Add a new Registration", response = Registrationresponse.class, tags={ "registration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "", response = Registrationresponse.class) })
    @RequestMapping(value = "/registration",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Registrationresponse> registrationPOST(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Registration body);

}
