package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.myclass.core.BaseEntity;

@Entity(name = "targets")
public class Targets extends BaseEntity{

		@Id
		private int id;
		
		private String title;
		
		@Column(name = "courses_id")
		private int coursesId;
	
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "courses_id",insertable = false,updatable = false)
		private Courses courses;


		/**
		 * @param id
		 * @param title
		 * @param coursesid
		 */
		public Targets(int id, String title, int coursesId) {
			super();
			this.id = id;
			this.title = title;
			this.coursesId = coursesId;
		}


		/**
		 * 
		 */
		public Targets() {
			super();
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public int getCoursesid() {
			return coursesId;
		}


		public void setCoursesid(int coursesId) {
			this.coursesId = coursesId;
		}


		public int getCoursesId() {
			return coursesId;
		}


		public void setCoursesId(int coursesId) {
			this.coursesId = coursesId;
		}


		public Courses getCourses() {
			return courses;
		}


		public void setCourses(Courses courses) {
			this.courses = courses;
		}


		
		
}
