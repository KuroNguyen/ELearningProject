package com.myclass.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.myclass.core.BaseEntity;

@Entity
@Table(name = "user_courses")
@Where(clause = "active=true")
@SQLDelete(sql = "UPDATE users SET active = false WHERE id = ?")
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

}
