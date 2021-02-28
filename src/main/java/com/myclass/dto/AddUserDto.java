package com.myclass.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class AddUserDto {
	@NotEmpty(message = "Email cannot empty!")
	@Email(message = "Email is not valid!")
	private String email;
	@Pattern(regexp = "^[a-zA-Z ]+{4,50}$", message = "Fullname must be words and contain from 4 to 50 characters.")
	private String fullname;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must be contains at least 8 characters, a digit must occur at least once, a lower case letter must occur at least once, an upper case letter must occur at least once, a special character must occur at least once and no whitespace allowed in the entire string")
	private String password;
	@Pattern(regexp = "^[0-9]+{9,11}$", message = "Phone must be number and from 9 to 11 digits.")
	private String phone;

	@Length(min = 5, max = 150, message = "Address must be from 5 to 150 characters.")
	private String address;

	@Min(value = 1, message = "Role id must be larger than 0.")
	private int roleId;

	public AddUserDto() {
		super();
	}

	public AddUserDto(String email, String fullname, String password, String phone, String address, int roleId) {
		super();
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.roleId = roleId;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
