package com.myclass.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
	@Min(value = 1, message = "id must be larger than 1")
	private int id;
	private String title;
	private String image;
	private int lecturesCount;
	private int hourCount;
	private int viewCount;
	private double price;
	private int discount;
	private double promotionPrice;
	
	//mô tả phải từ 4 tới 250 ký tự
	@Length(min = 4, max = 250, message = "Description must be contain from 4 to 250 characters.")
	private String description;
	private String content;
	private int categoryId;
	private String categoryName;
	private Date lastUpdate;
}
