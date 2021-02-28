package com.myclass.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.LoginDto;
import com.myclass.dto.UserDto;
import com.myclass.dto.UserLoginResponseDto;
import com.myclass.service.AuthService;
import com.myclass.service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	private AuthService authService;
	private UserService userService;

	public AuthController(AuthService authService, UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@PostMapping("login")
	public Object post(@Valid @RequestBody LoginDto dto) {
		try {
			UserLoginResponseDto userLoginResponseDto = authService.userLogin(dto);
			// Return user information and token 			
			return new ResponseEntity<Object>(userLoginResponseDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("register")
	public Object register(@Valid @RequestBody UserDto dto) {
		try {
			userService.insert(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

}
