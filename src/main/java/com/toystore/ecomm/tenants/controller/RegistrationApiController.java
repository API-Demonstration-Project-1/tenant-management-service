package com.toystore.ecomm.tenants.controller;

import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.model.Registration;
import com.toystore.ecomm.tenants.model.Registrationresponse;
import com.toystore.ecomm.tenants.model.TenantInfo;
import com.toystore.ecomm.tenants.services.TenantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-16T20:08:56.623Z")

@Controller
public class RegistrationApiController implements RegistrationApi {

    private static final Logger log = LoggerFactory.getLogger(RegistrationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    TenantService tenantService;
    
    @org.springframework.beans.factory.annotation.Autowired
    public RegistrationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> registrationByTenantIdDELETE(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Registration> registrationByTenantIdGET(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Registration>(objectMapper.readValue("{  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"}", Registration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Registration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Registration>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Registration> registrationByTenantIdPOST(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Registration>(objectMapper.readValue("{  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"}", Registration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Registration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Registration>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Registration> registrationByTenantIdPUT(@ApiParam(value = "",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Registration body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Registration>(objectMapper.readValue("{  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"}", Registration.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Registration>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Registration>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Registration>> registrationGET() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Registration>>(objectMapper.readValue("[ {  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"}, {  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Registration>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Registration>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Registrationresponse> registrationPOST(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Registration body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	TenantInfo tenantInfo = new TenantInfo();
            	tenantInfo.setTenantName(body.getTenantName());
            	tenantInfo.setTenantEmail(body.getTenantEmail());
            	tenantInfo.setTenantUsername(body.getTenantUsername());
            	tenantInfo.setTenantPassword(body.getTenantPassword());
            	tenantInfo.setTenantVerified("N");
            	tenantInfo.setCreatedTS(new Timestamp((new Date()).getTime()));
            	tenantInfo.setLastUpdatedTS(new Timestamp((new Date()).getTime()));
            	tenantInfo.setCreatedBy(PTMSConstants.SERVICE_NAME);
            	
            	tenantService.saveTenantInfo(tenantInfo);
            	
                return new ResponseEntity<Registrationresponse>(objectMapper.readValue("{  \"message\" : \"The Registration has been created successfully\"}", Registrationresponse.class), HttpStatus.CREATED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Registrationresponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Registrationresponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Registration>> registrationGET(@ApiParam(value = "Get List of Tenant Info based on a given Tenant Name") @Valid @RequestParam(value = "tenantName", required = false) String tenantName,@ApiParam(value = "Get List of Tenant Info based on a given Tenant Email") @Valid @RequestParam(value = "tenantEmail", required = false) String tenantEmail,@ApiParam(value = "Get List of Tenant Info based on Verified or Not Verified") @Valid @RequestParam(value = "tenantVerified", required = false) String tenantVerified) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Registration>>(objectMapper.readValue("[ {  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"}, {  \"tenantEmail\" : \"tenantEmail\",  \"tenantUsername\" : \"tenantUsername\",  \"tenantName\" : \"tenantName\",  \"tenantPassword\" : \"tenantPassword\",  \"tenantVerified\" : \"tenantVerified\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Registration>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Registration>>(HttpStatus.NOT_IMPLEMENTED);
    }
}
