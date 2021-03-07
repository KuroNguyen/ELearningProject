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
	private boolean kiemTraEmail;
	
	public UserDto(int id, String email, String fullname, String password, String avatar, String phone, String address,
			int roleId) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.roleId = roleId;
	}
	
	
	public UserDto(int id, String email, String fullname, String password,
			int roleId) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.roleId = roleId;
	}

	public UserDto(String email) {
		super();
		this.email = email;
	}
	
	
	
	
}
