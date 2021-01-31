package com.myclass.core;

import java.security.Principal;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Author: Nguyen Chanh Truc
 * Created: Jan 31, 2021	
 */
public class SpringSecurityAuditorAware implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
        .map(SecurityContext::getAuthentication)
        .filter(Authentication::isAuthenticated)
        .map(Authentication::getPrincipal)
        .map(UserDetails.class::cast)
        .map(UserDetails::getUsername);
		
//		return Optional.ofNullable("Kindson").filter(s -> !s.isEmpty());
	}



}
