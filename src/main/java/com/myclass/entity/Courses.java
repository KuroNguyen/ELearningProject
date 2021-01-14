package com.myclass.entity;


import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "courses")

public class Courses {
	
		@Id
		@Column(name = "courses_id")
		private int id;
		
		private String title;
		private String image;
		
		@Column(name = "lectures_count")
		private int lecturescount;
		
		@Column(name = "hour_count")
		private int hourcount;
		
		@Column(name = "view_count")
		private int viewcount;
		
		private double price;
		private int discount;
		private double promotion_price;
		private String description;
		private String content;
		
		@Column(name = "category_id")
		private int categoryId;
		
		
		@Column(name = "last_update")
		@Temporal(TemporalType.DATE)
		private Date last_update;
		
	
		@OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
		private Set<UserCourses> userCourses;

		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "category_id",insertable = false,updatable = false)
		private Category category;
		
		@OneToMany(mappedBy = "courses",fetch = FetchType.LAZY)
		private List<Targets> targets;
		
		@OneToMany(mappedBy = "courses",fetch = FetchType.LAZY)
		private List<Video> videos;
		/**
		 * @param id
		 * @param title
		 * @param image
		 * @param lecturescount
		 * @param hourcount
		 * @param viewcount
		 * @param price
		 * @param discount
		 * @param promotion_price
		 * @param description
		 * @param content
		 * @param categoryId
		 * @param last_update
		 */
		public Courses(int id, String title, String image, int lecturescount, int hourcount, int viewcount,
				double price, int discount, double promotion_price, String description, String content, int categoryId,
				Date last_update) {
			super();
			this.id = id;
			this.title = title;
			this.image = image;
			this.lecturescount = lecturescount;
			this.hourcount = hourcount;
			this.viewcount = viewcount;
			this.price = price;
			this.discount = discount;
			this.promotion_price = promotion_price;
			this.description = description;
			this.content = content;
			this.categoryId = categoryId;
			this.last_update = last_update;
		}

		
		public Set<UserCourses> getUserCourses() {
			return userCourses;
		}


		public void setUserCourses(Set<UserCourses> userCourses) {
			this.userCourses = userCourses;
		}


		/**
		 * 
		 */
		public Courses() {
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

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public int getLecturescount() {
			return lecturescount;
		}

		public void setLecturescount(int lecturescount) {
			this.lecturescount = lecturescount;
		}

		public int getHourcount() {
			return hourcount;
		}

		public void setHourcount(int hourcount) {
			this.hourcount = hourcount;
		}

		public int getViewcount() {
			return viewcount;
		}

		public void setViewcount(int viewcount) {
			this.viewcount = viewcount;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public int getDiscount() {
			return discount;
		}

		public void setDiscount(int discount) {
			this.discount = discount;
		}

		public double getPromotion_price() {
			return promotion_price;
		}

		public void setPromotion_price(double promotion_price) {
			this.promotion_price = promotion_price;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public int getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}

		public Date getLast_update() {
			return last_update;
		}

		public void setLast_update(Date last_update) {
			this.last_update = last_update;
		}

		
		
		
		

}
