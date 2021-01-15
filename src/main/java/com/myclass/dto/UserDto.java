package com.myclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
	private int id;
	private String email;
	private String fullname;
	private String password;
	private String avatar;
	private String phone;
	private String address;
	private int roleId;
	private String roleDesc;
}
