package com.myclass.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "user_courses")
@Where(clause = "active=true")
@SQLDelete(sql = "UPDATE user_courses SET active = false WHERE id = ?")
public class UserCourses {
	
	@EmbeddedId
	UserCourseKey id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @MapsId("courseId")
    @JoinColumn(name = "course_id",insertable = false, updatable = false)
    Course course;
	
	@Column(name = "role_id",insertable = false, updatable = false)
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
