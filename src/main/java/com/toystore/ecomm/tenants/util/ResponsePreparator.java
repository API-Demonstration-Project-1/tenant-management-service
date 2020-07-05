package com.toystore.ecomm.tenants.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.toystore.ecomm.tenants.constants.PTMSConstants;
import com.toystore.ecomm.tenants.model.Data;
import com.toystore.ecomm.tenants.model.Data2;
import com.toystore.ecomm.tenants.model.Loginresponse;
import com.toystore.ecomm.tenants.model.Registration;
import com.toystore.ecomm.tenants.model.Registrationresponse;
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
	
	public static Registrationresponse prepareRegistrationResponse(Object dataInfo, String msg, boolean isSuccess, Integer errorCode) {
		Data data = null;
		if (dataInfo != null) {
			data = new Data();
			data.setId((Integer)dataInfo);
		}
		
		Registrationresponse registrationResponse = new Registrationresponse();
		
		registrationResponse.setData(data);
		registrationResponse.setErrorCode(errorCode);
		registrationResponse.setMessage(msg);
		registrationResponse.setSuccess(isSuccess);
		return registrationResponse;
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
