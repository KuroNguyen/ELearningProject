package com.myclass.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class EditUserDto {
	@Min(value = 1, message = "User id must be larger than 0.")
	private int id;
	@Pattern(regexp = "^[a-zA-Z ]+{4,50}$", message = "Fullname must be words and contain from 4 to 50 characters.")
	private String fullname;
	@Pattern(regexp = "^[0-9]+{9,11}$", message = "Phone must be number and from 9 to 11 digits.")
	private String phone;
	@Length(min = 5, max = 150, message = "Address must be from 5 to 150 characters.")
	private String address;
	@Min(value = 1, message = "Role id must be larger than 0.")
	private int roleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public EditUserDto() {
		super();
	}

	public EditUserDto(int id, String fullname, String phone, String address, int roleId) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.roleId = roleId;
	}
}
