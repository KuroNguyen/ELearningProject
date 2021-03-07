package com.myclass.dto;
/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 28, 2021	
 */

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLoginResponseDto {
	private String token;
	private int userId;
	private String userName; 
}
