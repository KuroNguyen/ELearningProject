package com.myclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 12, 2021	
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
	private int id;
	private String title;
	private String url;
	private int timeCount;
	private int courseId;
	private String courseTitle;
}
