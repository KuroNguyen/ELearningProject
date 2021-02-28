package com.myclass.dto;

public class UserLoginDto {
	private int id;
	private String email;
	private String fullname;
	private String avatar;
	private String phone;
	private String address;
	private String role;
	private String token;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRoleName(String role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserLoginDto() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserLoginDto(int id, String email, String fullname, String avatar, String phone, String address,
			String role) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}
	
}
