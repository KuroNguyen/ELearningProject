package com.myclass.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class LoginDto {
	@NotEmpty(message = "Email cannot empty!")
	@Email(message = "Email is not valid!")
	private String email;
	
//	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+=])(?=\\S+$).{8,}$", message = "Password must be contains at least 8 characters, a digit must occur at least once, a lower case letter must occur at least once, an upper case letter must occur at least once, a special character must occur at least once and no whitespace allowed in the entire string")
	private String password;

	public LoginDto() {
		super();
	}

	public LoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}