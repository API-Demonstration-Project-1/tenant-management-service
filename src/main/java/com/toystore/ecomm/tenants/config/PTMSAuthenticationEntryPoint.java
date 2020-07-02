package com.toystore.ecomm.tenants.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class PTMSAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
    	httpServletResponse.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
    	httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	
    	PrintWriter writer = httpServletResponse.getWriter();
    	writer.println("HTTP Status 401 - " + e.getMessage());
    	
        //httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
    
    @Override
    public void afterPropertiesSet() {
    	setRealmName("PTMSService");
    	super.afterPropertiesSet();
    }
}
