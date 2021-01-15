package com.myclass.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
	private int id;
	private String title;
	private String icon;
}
