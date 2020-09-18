package com.toystore.ecomm.tenants.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.factory.POJOFactory;
import com.toystore.ecomm.tenants.model.Data;
import com.toystore.ecomm.tenants.model.Data2;
import com.toystore.ecomm.tenants.model.Data3;
import com.toystore.ecomm.tenants.model.Data4;
import com.toystore.ecomm.tenants.model.Loginresponse;
import com.toystore.ecomm.tenants.model.Registration;
import com.toystore.ecomm.tenants.model.Registrationresponse;
import com.toystore.ecomm.tenants.model.Subscription;
import com.toystore.ecomm.tenants.model.SubscriptionInfo;
import com.toystore.ecomm.tenants.model.Subscriptionresponse;
import com.toystore.ecomm.tenants.model.TenantInfo;

@Component
public class ResponsePreparator {
	
	static {
		mapper = new ObjectMapper();
	}
	
	private static ObjectMapper mapper;
	
	private static boolean toExcludeNull;
	
	@Value("${config.response.format.exclude.null}")
	public void setToExcludeNull(boolean value) {
		toExcludeNull = value;
	}
	
	public static String prepareLoginResponse(Object dataInfo, String msg, boolean isSuccess, Integer errorCode) throws IllegalAccessException, InstantiationException {
		Data2 data = null;
		
		if (dataInfo != null) {
			data = (Data2)POJOFactory.getInstance("DATA2");
			data.setJwttoken(dataInfo.toString());
		}
		
		Loginresponse loginResponse = (Loginresponse)POJOFactory.getInstance("LOGINRESP");
		loginResponse.setData(data);
		loginResponse.setErrorCode(errorCode);
		loginResponse.setMessage(msg);
		loginResponse.setSuccess(isSuccess);

		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        if (toExcludeNull)
        	mapper.setSerializationInclusion(Include.NON_NULL);
        else
        	mapper.setSerializationInclusion(Include.ALWAYS);
        
        String loginRespStr = null;
        try {
        	loginRespStr = mapper.writeValueAsString(loginResponse);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return loginRespStr;
	}
	
	public static String prepareRegistrationResponse(Object dataInfo, String msg, boolean isSuccess, Integer errorCode) throws IllegalAccessException, InstantiationException {
		
		Data data = null;
		Data3 data3 = null;
		
		if (dataInfo != null) {
			
			if (dataInfo instanceof List) {
				@SuppressWarnings("unchecked")
				ListIterator<TenantInfo> tenantInfoListIter = ((List<TenantInfo>)dataInfo).listIterator();
				List<Registration> regList = new ArrayList<Registration>();
				
				while(tenantInfoListIter.hasNext()) {
					TenantInfo currentItem = tenantInfoListIter.next();
					
					Registration regObj = (Registration)POJOFactory.getInstance("REGISTRATION");
					regObj.setTenantId(currentItem.getTenantId().toString());
					regObj.setTenantName(currentItem.getTenantName());
					regObj.setTenantUsername(currentItem.getTenantUsername());
					regObj.setTenantPassword(PTMSConstants.PASSWORD_FIELD_VALUE);
					regObj.setTenantEmail(currentItem.getTenantEmail());
					regObj.setTenantVerified(currentItem.getTenantVerified());
					
					regList.add(regObj);
				}
				
				data3 = (Data3)POJOFactory.getInstance("DATA3");
				data3.setResponse(regList);
				
			} else {
				data = new Data();
				data.setId((Integer)dataInfo);
			}
		}
		
		Registrationresponse registrationResponse = (Registrationresponse)POJOFactory.getInstance("REGISTRATIONRESP");
		registrationResponse.setData(data);
		registrationResponse.setData3(data3);
		registrationResponse.setErrorCode(errorCode);
		registrationResponse.setMessage(msg);
		registrationResponse.setSuccess(isSuccess);
		
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        if (toExcludeNull)
        	mapper.setSerializationInclusion(Include.NON_NULL);
        else
        	mapper.setSerializationInclusion(Include.ALWAYS);
        
        String regRespStr = null;
        try {
        	regRespStr = mapper.writeValueAsString(registrationResponse);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
		return regRespStr;
	}
	
	public static String prepareSubscriptionResponse(Object dataInfo, String msg, boolean isSuccess, Integer errorCode) throws IllegalAccessException, InstantiationException {
		
		Data data = null;
		Data4 data4 = null;
		
		if (dataInfo != null) {
			
			if (dataInfo instanceof List) {
				@SuppressWarnings("unchecked")
				ListIterator<SubscriptionInfo> subscriptionListIter = ((List<SubscriptionInfo>)dataInfo).listIterator();
				List<Subscription> subscriptionList = new ArrayList<Subscription>();
				
				while(subscriptionListIter.hasNext()) {
					SubscriptionInfo currentItem = (SubscriptionInfo)subscriptionListIter.next();
					
					//Subscription subscriptionObj = new Subscription();
					Subscription subscriptionObj = (Subscription)POJOFactory.getInstance("SUBSCRIPTION");
					subscriptionObj.setPlanName(currentItem.getPlanType().getPlanName());
					subscriptionObj.setRenewalType(currentItem.getRenewalType().getRenewalName());
					subscriptionObj.setTenantName(currentItem.getTenant().getTenantName());
					subscriptionObj.setIsValid(currentItem.getIsValid());
					subscriptionObj.setStartDate(currentItem.getStartDate().toString());
					subscriptionObj.setEndDate(currentItem.getEndDate().toString());
					
					subscriptionList.add(subscriptionObj);
				}
				
				data4 = (Data4)POJOFactory.getInstance("DATA4");
				data4.setResponse(subscriptionList);
				
			} else {
				data = (Data)POJOFactory.getInstance("DATA");
				data.setId((Integer)dataInfo);
			}
		}
		
		Subscriptionresponse subscriptionresponse = (Subscriptionresponse)POJOFactory.getInstance("SUBSCRIPTIONRESP");
		
		subscriptionresponse.setData(data);
		subscriptionresponse.setData4(data4);
		subscriptionresponse.setErrorCode(errorCode);
		subscriptionresponse.setMessage(msg);
		subscriptionresponse.setSuccess(isSuccess);
		
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        if (toExcludeNull)
        	mapper.setSerializationInclusion(Include.NON_NULL);
        else
        	mapper.setSerializationInclusion(Include.ALWAYS);
        
        String subsRespStr = null;
        try {
        	subsRespStr = mapper.writeValueAsString(subscriptionresponse);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
		return subsRespStr;
	}
	
	
	// NOT USED
	/*
	 * public static List<Registration>
	 * prepareGETRegistrationResponse(List<TenantInfo> tenantInfoList) {
	 * ListIterator<TenantInfo> tenantInfoListIter = tenantInfoList.listIterator();
	 * List<Registration> regList = new ArrayList<Registration>();
	 * 
	 * while(tenantInfoListIter.hasNext()) { TenantInfo currentItem =
	 * tenantInfoListIter.next();
	 * 
	 * Registration regObj = new Registration();
	 * regObj.setTenantId(currentItem.getTenantId().toString());
	 * regObj.setTenantName(currentItem.getTenantName());
	 * regObj.setTenantUsername(currentItem.getTenantUsername());
	 * regObj.setTenantPassword(PTMSConstants.PASSWORD_FIELD_VALUE);
	 * regObj.setTenantEmail(currentItem.getTenantEmail());
	 * regObj.setTenantVerified(currentItem.getTenantVerified());
	 * 
	 * regList.add(regObj); }
	 * 
	 * return regList; }
	 */
}
