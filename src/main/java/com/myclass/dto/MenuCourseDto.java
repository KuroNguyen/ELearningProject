package com.myclass.dto;

public class MenuCourseDto {
	private int id;
	private String title;
	private int categoryId;
	public MenuCourseDto() {
		super();
	}
	public MenuCourseDto(int id, String title, int categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.categoryId = categoryId;
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
