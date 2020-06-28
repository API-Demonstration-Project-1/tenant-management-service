package com.toystore.ecomm.tenants.services;

import com.toystore.ecomm.tenants.model.TenantInfo;
import com.toystore.ecomm.tenants.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
	@Autowired
	private TenantRepository tenantRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
    	TenantInfo user = tenantRepository.findByTenantUsername(username).get(0);
    	
        if (user == null) { 
        	throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        //for (Role role : user.getRoles()){
        //grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        //}

        return new org.springframework.security.core.userdetails.User(user.getTenantUsername(), user.getTenantPassword(), grantedAuthorities);
    }
}
