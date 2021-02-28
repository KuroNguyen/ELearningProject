package com.myclass.entity;
<<<<<<< HEAD

=======
import java.io.Serializable;

import javax.persistence.CascadeType;
>>>>>>> 268f890b983c47dab7035bf8426985b9668a65df
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
<<<<<<< HEAD
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
=======
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.myclass.core.BaseEntity;

@Entity
@Table(name = "user_courses")
@Where(clause = "active=true")
@SQLDelete(sql = "UPDATE user_courses SET active = false WHERE id = ?")
public class UserCourses extends BaseEntity<String> implements Serializable {
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	private Course course;

	@Column(name = "role_id", nullable = false)
	private int roleId;
>>>>>>> 268f890b983c47dab7035bf8426985b9668a65df

	public UserCourses(UserCourseKey id, User user, Course course, int roleId) {
		super();
		this.id = id;
		this.user = user;
		this.course = course;
		this.roleId = roleId;
	}
	
}
