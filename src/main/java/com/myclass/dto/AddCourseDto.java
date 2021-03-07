package com.myclass.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AddCourseDto {

	private String title;
	private String image;
	private int lecturesCount;
	private int hourCount;
	private float price;
	private int discount;
	private float promotionPrice;
	
	//mô tả phải từ 4 tới 250 ký tự
	@NotEmpty
	@Length(min = 4, max = 250, message = "Description must be contain from 4 to 250 characters.")
	private String description;
	
	//nội dung phải từ 4 tới 250 ký tự
	@NotEmpty
	@Length(min = 4, max = 250, message = "Content must be contain from 4 to 250 characters.")
	private String content;
	
	// categoryId phải bắt đầu từ 1
	@Min(value = 1,message = "Category id must be larger than 0")
	private int categoryId;
}
