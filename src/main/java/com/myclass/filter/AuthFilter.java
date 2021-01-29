package com.myclass.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

/**
 * Author: Nguyen Chanh Truc
 * Created: Jan 29, 2021	
 */
public class AuthFilter extends BasicAuthenticationFilter {

	private UserDetailsService userDetailsService;
	
	public AuthFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Get token from request
		String tokenHeader = request.getHeader("Authorization");
		if (tokenHeader == null || tokenHeader.isEmpty() || !tokenHeader.startsWith("Bearer ")) {
			response.sendError(401, "Chưa đăng nhập");
			return;
		}
		// Decrypt token to get email
		String token = tokenHeader.replace("Bearer ", "");
		String email = Jwts.parser()
				.setSigningKey("ABC_EGH")
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		// Get user information (UserDetailDto)
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		
		Authentication authentication =
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		// Add user information to context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(request, response);
	}	
}