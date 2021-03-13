package com.myclass.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Value("${roleId.user}")
	private int ID_ROLE_USER;
	
	private AuthService authService;
	private UserService userService;

	public AuthController(AuthService authService, UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@PostMapping("login")
	public Object post(@Valid @RequestBody LoginDto dto) {
		try {
			System.out.println(dto.toString());
			UserLoginResponseDto userLoginResponseDto = authService.userLogin(dto);
			System.out.println(userLoginResponseDto.toString());
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
				System.out.println(dto.toString());
				dto.setRoleId(ID_ROLE_USER);
				userService.insert(dto);
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping("kiemTraEmail")
	public Object kiemTraMail(@Valid @RequestBody UserDto dto) {
		try {	
		
				userService.checkMail(dto.getEmail());
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	

}
