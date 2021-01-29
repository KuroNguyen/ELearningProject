package com.myclass.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	
	@NotEmpty(message = "Vui lòng nhập email!")
	@Email(message = "Email không đúng định dạng!")
	private String email;
	
	@NotEmpty(message = "Vui lòng nhập mật khẩu!")
	@Length(min = 6, message = "Mật khẩu ít nhất 6 ký tự!")
	private String password;
}
