package com.myclass.service.impl;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.myclass.dto.LoginDto;
import com.myclass.dto.UserLoginResponseDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
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
	UserRepository userRepository;
	
	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
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

	@Override
	public UserLoginResponseDto userLogin(LoginDto loginDto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		
		// Create token
		Date now = new Date();
		String token = Jwts.builder()
				.setSubject(loginDto.getEmail())
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + 86400000L))
				.signWith(SignatureAlgorithm.HS512, "ABC_EGH")
				.compact();
		
		// Get user information
		User entity = userRepository.findByEmail(loginDto.getEmail());
		// Convert entity to dto
		UserLoginResponseDto dto = new UserLoginResponseDto();
		dto.setToken(token);
		dto.setUserId(entity.getId());
		dto.setUserName(entity.getFullname());
		
		return dto;
	}

}
