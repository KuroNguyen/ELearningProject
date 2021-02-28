package com.myclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 9, 2021	
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuCourseDto {
	private int id;
	private String title;
	private int categoryId;
}
