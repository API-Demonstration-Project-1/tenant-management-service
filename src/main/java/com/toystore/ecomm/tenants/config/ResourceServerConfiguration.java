package com.toystore.ecomm.tenants.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) {
//		resources.resourceId("ptms");
//	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/ptms/registration").permitAll()
			.antMatchers(HttpMethod.PUT, "/ptms/registration/**").permitAll()
			.antMatchers(HttpMethod.GET, "/ptms/registration/*/emailverification/**").permitAll()
			.antMatchers(HttpMethod.GET, "/ptms/registration/**").hasAnyRole("PTMS_ADMIN", "PTMS_USER", "TENANT_ADMIN", "TENANT_USER")
			.antMatchers(HttpMethod.DELETE, "/ptms/registration/**").hasRole("PTMS_ADMIN")
			.antMatchers(HttpMethod.POST, "/ptms/login").permitAll()
			
			.antMatchers(HttpMethod.POST, "/ptms/subscription").hasAnyRole("PTMS_ADMIN", "PTMS_USER", "TENANT_ADMIN")
			.antMatchers(HttpMethod.PUT, "/ptms/subscription/**").hasAnyRole("PTMS_ADMIN", "PTMS_USER", "TENANT_ADMIN")
			.antMatchers(HttpMethod.GET, "/ptms/subscription/**").hasAnyRole("PTMS_ADMIN", "PTMS_USER", "TENANT_ADMIN", "TENANT_USER")
			.antMatchers(HttpMethod.DELETE, "/ptms/subscription/**").hasRole("PTMS_ADMIN");
	}

}
