package com.myclass.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.myclass.core.BaseEntity;

@Entity(name = "user_courses")
public class UserCourses extends BaseEntity implements Serializable {

		@Id
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "user_id")
		private User user;
		
		@Id
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "courses_id")
		private Courses course;
		
		private int role_id;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Courses getCourse() {
			return course;
		}

		public void setCourse(Courses course) {
			this.course = course;
		}

		public int getRole_id() {
			return role_id;
		}

		public void setRole_id(int role_id) {
			this.role_id = role_id;
		}

		/**
		 * @param user
		 * @param course
		 * @param role_id
		 */
		public UserCourses(User user, Courses course, int role_id) {
			super();
			this.user = user;
			this.course = course;
			this.role_id = role_id;
		}

		/**
		 * 
		 */
		public UserCourses() {
			super();
		}
		
		
		
		
}
