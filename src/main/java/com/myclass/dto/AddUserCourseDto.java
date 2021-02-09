package com.myclass.dto;

import javax.validation.constraints.Min;

public class AddUserCourseDto {
	
	@Min(value = 1,message = "User id must be larger than 0")
	private int userId;
	
	@Min(value = 1,message = "Course id must be larger than 0")
	private int courseId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public AddUserCourseDto() {
		super();
	}

	public AddUserCourseDto(int userId, int courseId) {
		super();
		this.userId = userId;
		this.courseId = courseId;
	}
	
	
}
