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

import io.jsonwebtoken.ExpiredJwtException;
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
		
//		if(request.getServletPath().startsWith("/api/admin/user")) {
//			chain.doFilter(request, response);
//			return;
//		}
		
		if (request.getServletPath().startsWith("/api/course")) {
			chain.doFilter(request, response);
			return;
		}
		
		if (request.getServletPath().startsWith("/api/category")) {
			chain.doFilter(request, response);
			return;
		}
		
		if (request.getServletPath().startsWith("/api/auth/register")) {
			chain.doFilter(request, response);
			return;
		}
		
		// Get token from request
		String tokenHeader = request.getHeader("Authorization");
		if (tokenHeader == null || tokenHeader.isEmpty() || !tokenHeader.startsWith("Bearer ")) {
			response.sendError(401, "Chưa đăng nhập");
			
			return;
		}
		// Decrypt token to get email
		String token = tokenHeader.replace("Bearer ", "");
		String email = null;
		try {
			email = Jwts.parser()
					.setSigningKey("ABC_EGH")
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
		} catch (Exception e) {
			if (e instanceof ExpiredJwtException) {
				response.sendError(401, "Hết hạn token");
			}
			return;
		}
		
		// Get user information (UserDetailDto)
		UserDetails userDetails;
		try {
			userDetails = userDetailsService.loadUserByUsername(email);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			response.sendError(401, e.getMessage());
			return;
		}
		
		Authentication authentication =
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		// Add user information to context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(request, response);
	}	
}
