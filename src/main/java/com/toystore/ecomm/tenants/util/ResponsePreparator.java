package com.toystore.ecomm.tenants.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.toystore.ecomm.tenants.constants.PTMSConstants;
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

public class ResponsePreparator {
	
	public static Loginresponse prepareLoginResponse(Object dataInfo, String msg, boolean isSuccess, Integer errorCode) {
		Data2 data = null;
		if (dataInfo != null) {
			data = new Data2();
			data.setJwttoken(dataInfo.toString());
		}
		
		Loginresponse loginResponse = new Loginresponse();
		
		loginResponse.setData(data);
		loginResponse.setErrorCode(errorCode);
		loginResponse.setMessage(msg);
		loginResponse.setSuccess(isSuccess);
		return loginResponse;
	}
	
	@SuppressWarnings("unchecked")
	public static Registrationresponse prepareRegistrationResponse(Object dataInfo, String msg, boolean isSuccess, Integer errorCode) {
		
		Data data = null;
		Data3 data3 = null;
		
		if (dataInfo != null) {
			
			if (dataInfo instanceof List) {
				ListIterator<TenantInfo> tenantInfoListIter = ((List<TenantInfo>)dataInfo).listIterator();
				List<Registration> regList = new ArrayList<Registration>();
				
				while(tenantInfoListIter.hasNext()) {
					TenantInfo currentItem = tenantInfoListIter.next();
					
					Registration regObj = new Registration();
					regObj.setTenantId(currentItem.getTenantId().toString());
					regObj.setTenantName(currentItem.getTenantName());
					regObj.setTenantUsername(currentItem.getTenantUsername());
					regObj.setTenantPassword(PTMSConstants.PASSWORD_FIELD_VALUE);
					regObj.setTenantEmail(currentItem.getTenantEmail());
					regObj.setTenantVerified(currentItem.getTenantVerified());
					
					regList.add(regObj);
				}
				
				data3 = new Data3();
				data3.setResponse(regList);
				
			} else {
				data = new Data();
				data.setId((Integer)dataInfo);
			}
		}
		
		Registrationresponse registrationResponse = new Registrationresponse();
		
		registrationResponse.setData(data);
		registrationResponse.setData3(data3);
		registrationResponse.setErrorCode(errorCode);
		registrationResponse.setMessage(msg);
		registrationResponse.setSuccess(isSuccess);
		
		
		return registrationResponse;
	}
	
	@SuppressWarnings("unchecked")
	public static Subscriptionresponse prepareSubscriptionResponse(Object dataInfo, String msg, boolean isSuccess, Integer errorCode) {
		
		Data data = null;
		Data4 data4 = null;
		
		if (dataInfo != null) {
			
			if (dataInfo instanceof List) {
				ListIterator<SubscriptionInfo> subscriptionListIter = ((List<SubscriptionInfo>)dataInfo).listIterator();
				List<Subscription> subscriptionList = new ArrayList<Subscription>();
				
				while(subscriptionListIter.hasNext()) {
					SubscriptionInfo currentItem = (SubscriptionInfo)subscriptionListIter.next();
					
					Subscription subscriptionObj = new Subscription();
					subscriptionObj.setPlanName(currentItem.getPlanType().getPlanName());
					subscriptionObj.setRenewalType(currentItem.getRenewalType().getRenewalName());
					subscriptionObj.setTenantName(currentItem.getTenant().getTenantName());
					subscriptionObj.setIsValid(currentItem.getIsValid());
					subscriptionObj.setStartDate(currentItem.getStartDate().toString());
					subscriptionObj.setEndDate(currentItem.getEndDate().toString());
					
					subscriptionList.add(subscriptionObj);
				}
				
				data4 = new Data4();
				data4.setResponse(subscriptionList);
				
			} else {
				data = new Data();
				data.setId((Integer)dataInfo);
			}
		}
		
		Subscriptionresponse subscriptionresponse = new Subscriptionresponse();
		
		subscriptionresponse.setData(data);
		subscriptionresponse.setData4(data4);
		subscriptionresponse.setErrorCode(errorCode);
		subscriptionresponse.setMessage(msg);
		subscriptionresponse.setSuccess(isSuccess);
		
		
		return subscriptionresponse;
	}

	public static List<Registration> prepareGETRegistrationResponse(List<TenantInfo> tenantInfoList) {
		ListIterator<TenantInfo> tenantInfoListIter = tenantInfoList.listIterator();
		List<Registration> regList = new ArrayList<Registration>();
		
		while(tenantInfoListIter.hasNext()) {
			TenantInfo currentItem = tenantInfoListIter.next();
			
			Registration regObj = new Registration();
			regObj.setTenantId(currentItem.getTenantId().toString());
			regObj.setTenantName(currentItem.getTenantName());
			regObj.setTenantUsername(currentItem.getTenantUsername());
			regObj.setTenantPassword(PTMSConstants.PASSWORD_FIELD_VALUE);
			regObj.setTenantEmail(currentItem.getTenantEmail());
			regObj.setTenantVerified(currentItem.getTenantVerified());
			
			regList.add(regObj);
		}
		
		return regList;
	}
}
