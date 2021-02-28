package com.myclass.service.impl;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.myclass.dto.LoginDto;
import com.myclass.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Author: Nguyen Chanh Truc
 * Created: Jan 28, 2021	
 */
@Service
public class AuthServiceImpl implements AuthService {
	
	AuthenticationManager authenticationManager;
	
	public AuthServiceImpl(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public String login(LoginDto dto) {
		
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
		
		// Create token
		Date now = new Date();
		String token = Jwts.builder()
				.setSubject(dto.getEmail())
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + 86400000L))
				.signWith(SignatureAlgorithm.HS512, "ABC_EGH")
				.compact();
		
		return token;
	}

}
