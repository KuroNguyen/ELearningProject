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
}
