package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.myclass.core.BaseEntity;

@Entity(name = "videos")
public class Video extends BaseEntity {
	
	@Id
	private int id;
	
	private String title;
	private String url;
	
	@Column(name = "time_count")
	private int timeCount;
	
	@Column(name = "courses_id")
	private int coursesId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courses_id",insertable = false,updatable = false)
	private Courses courses;


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


	public String getUrl() {
		return url;
	}


	public Courses getCourses() {
		return courses;
	}


	public void setCourses(Courses courses) {
		this.courses = courses;
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


	public int getCoursesId() {
		return coursesId;
	}


	public void setCoursesId(int coursesId) {
		this.coursesId = coursesId;
	}


	/**
	 * @param id
	 * @param title
	 * @param url
	 * @param timeCount
	 * @param coursesId
	 */
	public Video(int id, String title, String url, int timeCount, int coursesId) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.timeCount = timeCount;
		this.coursesId = coursesId;
	}


	/**
	 * 
	 */
	public Video() {
		super();
	}
	
	
	
	

}
