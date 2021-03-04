package com.myclass.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

public class AddVideoDto {
	@Pattern(regexp = "^[a-zA-Z ]+{4,50}$", message = "Title must is words and contain from 4 to 50 characters.")
	private String title;
	@URL()
	private String url;
	@Min(value = 1, message = "Time count must be larger than 0")
	private int timeCount;
	@Min(value = 1, message = "Course id must be larger than 0")
	private int courseId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public AddVideoDto() {
		super();
	}

	public AddVideoDto(String title, String url, int timeCount, int courseId) {
		super();
		this.title = title;
		this.url = url;
		this.timeCount = timeCount;
		this.courseId = courseId;
	}
}
