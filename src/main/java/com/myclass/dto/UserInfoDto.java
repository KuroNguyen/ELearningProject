package com.myclass.dto;

public class UserInfoDto {
	private String fullname;
	private String avatar;
	private String phone;
	private String address;
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
	public UserInfoDto() {
		super();
	}
	public UserInfoDto(String fullname, String avatar, String phone, String address) {
		super();
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
	}
}
