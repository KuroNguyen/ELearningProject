package com.myclass.service;

import com.myclass.dto.LoginDto;
import com.myclass.dto.UserLoginResponseDto;

/**
 * Author: Nguyen Chanh Truc
 * Created: Jan 28, 2021	
 */
public interface AuthService {
	String login(LoginDto dto);
	UserLoginResponseDto userLogin(LoginDto loginDto);
}
