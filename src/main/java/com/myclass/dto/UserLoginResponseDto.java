package com.myclass.dto;
/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 28, 2021	
 */

import lombok.Data;

@Data
public class UserLoginResponseDto {
	private String token;
	private int userId;
	private String userName; 
}
