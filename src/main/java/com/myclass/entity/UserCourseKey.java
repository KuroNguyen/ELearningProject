package com.myclass.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UserCourseKey implements Serializable{

	

	@Column(name = "user_id")
	int userId;
	
	@Column(name = "course_id")
	int courseId;

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

	public UserCourseKey() {
		super();
	}

	public UserCourseKey(int userId, int courseId) {
		super();
		this.userId = userId;
		this.courseId = courseId;
	}
	
}
