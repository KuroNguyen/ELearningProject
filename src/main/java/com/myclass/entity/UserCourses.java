package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class UserCourses {
	
	@EmbeddedId
	UserCourseKey id;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	User user;
	
	@ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;
	
	@Column(name = "role_id")
	int roleId;

	public UserCourseKey getId() {
		return id;
	}

	public void setId(UserCourseKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public UserCourses() {
		super();
	}

	public UserCourses(UserCourseKey id, User user, Course course, int roleId) {
		super();
		this.id = id;
		this.user = user;
		this.course = course;
		this.roleId = roleId;
	}
	
}
