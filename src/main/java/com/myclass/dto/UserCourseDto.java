package com.myclass.dto;

public class UserCourseDto {
	private String email;
	private String courseTitle;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public UserCourseDto() {
		super();
	}
	public UserCourseDto(String email, String courseTitle) {
		super();
		this.email = email;
		this.courseTitle = courseTitle;
	}
}
