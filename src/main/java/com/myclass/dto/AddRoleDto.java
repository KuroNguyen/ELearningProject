package com.myclass.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 9, 2021	
 */
@Data
public class AddRoleDto {
	@Pattern(regexp = "^(ROLE_)[A-Z]{4,15}", message = "Role name must be uppercase, contain from 4 to 15 characters and start with ROLE_")
	private String name;
	@NotEmpty
	@Length(min = 4, max = 250, message = "Description must contain from 4 to 250 characters")
	private String description;
}
