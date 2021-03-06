package com.myclass.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class AddTargetDto {
	@Pattern(regexp = "^[a-zA-Z ]+{4,50}$", message = "Title must is words and contain from 4 to 50 characters.")
	private String title;
	
	@Min(value = 1,message = "Course id must be larger than 0")
	private int courseId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public AddTargetDto() {
		super();
	}
	public AddTargetDto(String title, int courseId) {
		super();
		this.title = title;
		this.courseId = courseId;
	}
}
