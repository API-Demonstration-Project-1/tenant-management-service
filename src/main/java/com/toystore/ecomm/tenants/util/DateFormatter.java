package com.toystore.ecomm.tenants.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.toystore.ecomm.tenants.constants.PTMSConstants;

public class DateFormatter {

	public static Date format(Long unixTimestamp) throws ParseException {
	   //convert seconds to milliseconds
	   Date date = new Date(unixTimestamp * 1000L); 
	   // format of the date
	   SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
	   jdf.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
	   
	   return jdf.parse(jdf.format(date));
	}
	
	public static Date format(String timeStamp) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(PTMSConstants.SUBS_TS_PATTERN);
		
		return dateFormat.parse(timeStamp);
	}
}
