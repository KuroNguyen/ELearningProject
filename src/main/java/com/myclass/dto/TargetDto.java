package com.myclass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 5, 2021	
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TargetDto {
	private int id;
	private String title;
	private int courseId;
	private String course;
	public TargetDto(int id, String title, String course) {
		super();
		this.id = id;
		this.title = title;
		this.course = course;
	}
}
