package com.myclass.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.myclass.core.BaseEntity;

@Entity(name = "categories")
public class Category extends BaseEntity{

		@Id
		private int id;
		private String title;
		private String icon;
		
		@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
		private List<Courses> courses;

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

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public List<Courses> getCourses() {
			return courses;
		}

		public void setCourses(List<Courses> courses) {
			this.courses = courses;
		}

		/**
		 * @param id
		 * @param title
		 * @param icon
		 */
		public Category(int id, String title, String icon) {
			super();
			this.id = id;
			this.title = title;
			this.icon = icon;
		}

		/**
		 * 
		 */
		public Category() {
			super();
		}
		
		
}
