package com.myclass.dto;

/**
 * Author: Nguyen Chanh Truc
 * Created: Mar 10, 2021	
 */

import java.util.List;

import lombok.Data;

@Data
public class BuyCourseDto {
	private List<CourseDto> courseDto;
	private String totalPrice;
}
