/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.14).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.toystore.ecomm.tenants.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toystore.ecomm.tenants.model.Error;
import com.toystore.ecomm.tenants.model.Subscription;
import com.toystore.ecomm.tenants.model.Subscriptionresponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-16T20:08:56.623Z")

@Api(value = "subscription", description = "the subscription API")
@RequestMapping(value = "/ptms")
public interface SubscriptionApi {

    @ApiOperation(value = "SubscriptionBySubscriptionId_DELETE", nickname = "subscriptionBySubscriptionIdDELETE", notes = "Delete a Subscription by subscriptionId", tags={ "subscription", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = ""),
        @ApiResponse(code = 404, message = "TODO: Add error message", response = Error.class) })
    @RequestMapping(value = "/subscription/{subscriptionId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> subscriptionBySubscriptionIdDELETE(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId);


    @ApiOperation(value = "SubscriptionBySubscriptionId_GET", nickname = "subscriptionBySubscriptionIdGET", notes = "Get a Subscription by subscriptionId", response = Subscription.class, tags={ "subscription", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = Subscription.class),
        @ApiResponse(code = 404, message = "TODO: Add error message", response = Error.class) })
    @RequestMapping(value = "/subscription/{subscriptionId}",
        produces = { "application/json" }, 
        //consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Subscription> subscriptionBySubscriptionIdGET(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId);


    @ApiOperation(value = "SubscriptionBySubscriptionId_POST", nickname = "subscriptionBySubscriptionIdPOST", notes = "Subscription using Username & Password", response = Subscription.class, tags={ "subscription", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = Subscription.class),
        @ApiResponse(code = 400, message = "TODO: Add error message", response = Error.class) })
    @RequestMapping(value = "/subscription/{subscriptionId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Subscription> subscriptionBySubscriptionIdPOST(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId);


    @ApiOperation(value = "SubscriptionBySubscriptionId_PUT", nickname = "subscriptionBySubscriptionIdPUT", notes = "Update a Subscription by subscriptionId", response = Subscription.class, tags={ "subscription", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = Subscription.class),
        @ApiResponse(code = 404, message = "TODO: Add error message", response = Error.class) })
    @RequestMapping(value = "/subscription/{subscriptionId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Subscription> subscriptionBySubscriptionIdPUT(@ApiParam(value = "",required=true) @PathVariable("subscriptionId") String subscriptionId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Subscription body);


    @ApiOperation(value = "Subscription_GET", nickname = "subscriptionGET", notes = "Get a list of Subscription", response = Subscription.class, responseContainer = "List", tags={ "subscription", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = Subscription.class, responseContainer = "List") })
    @RequestMapping(value = "/subscription",
        produces = { "application/json" }, 
        //consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Subscription>> subscriptionGET(@ApiParam(value = "Get Subscriptions for a given Tenant Name") @Valid @RequestParam(value = "tenantName", required = false) String tenantName,@ApiParam(value = "Get Subscriptions for a given Plan Name") @Valid @RequestParam(value = "planName", required = false) String planName,@ApiParam(value = "Get all Valid or Invalid Subscriptions") @Valid @RequestParam(value = "isValid", required = false) String isValid);


    @ApiOperation(value = "Subscription_POST", nickname = "subscriptionPOST", notes = "Add a new Subscription", response = Subscriptionresponse.class, tags={ "subscription", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "", response = Subscriptionresponse.class) })
    @RequestMapping(value = "/subscription",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Subscriptionresponse> subscriptionPOST(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Subscription body);

}
