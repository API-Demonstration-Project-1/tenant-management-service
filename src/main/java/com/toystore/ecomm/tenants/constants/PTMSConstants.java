package com.toystore.ecomm.tenants.constants;

public class PTMSConstants {
	
	public static final String YES_VALUE = "Y";
	public static final String NO_VALUE = "N";
	public static final String BLANK_STRING = "";
	public static final String COLON_SEPARATOR = ":";
	public static final String SINGLE_SPACE = " ";
	
	public static final String SERVICE_NAME = "ptms-service";
	
	public static final String ACCESS_TOKEN_FIELD = "access_token";
	public static final String ERROR_DESC_FIELD = "error_description";
	
	public static final String PASSWORD_FIELD_VALUE = "[PROTECTED]";
	
	public static final int FREE_SUBSCRIPTION_TYPE = 1;
	
	public static final int MONTHLY_RENEWAL_TYPE = 1;
	public static final int NO_RENEWAL_TYPE = 8;
	
	public static final String TENANT_ADMIN_ROLE_NAME = "TENANT_ADMIN";
	public static final String TENANT_USER_ROLE_NAME = "TENANT_USER";
	public static final String PTMS_ADMIN_ROLE_NAME = "PTMS_ADMIN";
	public static final String PTMS_USER_ROLE_NAME = "PTMS_USER";
	public static final String TENANT_ADMIN_ROLE_DESC = "Tenant Admin";
	public static final String TENANT_USER_ROLE_DESC = "Tenant User";
	
	public static final int TENANT_ADMIN_ROLE_TYPE_CODE = 2;
	public static final int TENANT_USER_ROLE_TYPE_CODE = 3;

	public static final String UNICODE_ENCODING_TYPE = "UTF-8";
	
	// For Email Templates
	public static final String WELCOME_TEMPLATE_KEY = "welcome-template";
	public static final String POSTVERIFICATION_TEMPLATE_KEY = "postverification-template";
	
	// Subscription Plan/Renewal Types
	public static final String SUBS_MONTHLY = "Monthly";
	public static final String SUBS_QUATERLY = "Quaterly";
	public static final String SUBS_HALFYEARLY = "HalfYearly";
	public static final String SUBS_YEARLY = "Yearly";
	public static final String SUBS_TWOYEARS = "2Years";
	public static final String SUBS_THREEYEARS = "3Years";
	public static final String SUBS_FIVEYEARS = "5Years";
	
	public static final String SUBS_TRIAL = "Trial";
	
	public static final int TRIAL_SUBSCRIPTION_DAYS = 15;
	
	public static final String SUBS_DATE_PATTERN = "dd-MM-yyyy";
	
	public static final String SUBS_TS_PATTERN = "EEE MMM dd HH:mm:ss Z yyyy";
}
