package com.toystore.ecomm.tenants.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.toystore.ecomm.tenants.constants.PTMSConstants;

@Component
public class EmailTemplateLoader {
	
	private static final String WELCOME_TEMPLATE = "email-templates/welcome-template.html";
	private static final String POSTVERIFICATION_TEMPLATE = "email-templates/postverification-template.html";
	
	private static Map<String, String> templateMap;
	
	protected static Map<String, String> getTemplateMap() throws IOException {
		if (templateMap == null) {
			templateMap = new HashMap<String, String>();
			
			/*	
			 * For each file in the folder src/main/resources/email-templates, 
			 * templateMap.add(fileName, fileContents)
			 */
			
			templateMap.put(PTMSConstants.WELCOME_TEMPLATE_KEY, getHTMLContentInString(WELCOME_TEMPLATE));
			templateMap.put(PTMSConstants.POSTVERIFICATION_TEMPLATE_KEY, getHTMLContentInString(POSTVERIFICATION_TEMPLATE));
		}
		
		return templateMap;
	}
	
	public static String getTemplate(String templateName) throws IOException {
		return getTemplateMap().get(templateName);
	}
	
	private static String getHTMLContentInString(String classPath) throws IOException {
		final Resource templateResource = new ClassPathResource(classPath);
        final InputStream inputStream = templateResource.getInputStream();
        
        return IOUtils.toString(inputStream, PTMSConstants.UNICODE_ENCODING_TYPE);
	}

}
