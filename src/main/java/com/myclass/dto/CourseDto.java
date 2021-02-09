package com.myclass.dto;

import java.util.Date;

public class CourseDto {
	private int id;
	private String title;
	private String image;
	private int lecturesCount;
	private int hourCount;
	private int viewCount;
	private float price;
	private int discount;
	private float promotionPrice;
	private String description;
	private String content;
	private String category;
	private Date lastUpdate;
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
	public int getLecturesCount() {
		return lecturesCount;
	}
	public void setLecturesCount(int lecturesCount) {
		this.lecturesCount = lecturesCount;
	}
	public int getHourCount() {
		return hourCount;
	}
	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public float getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public CourseDto() {
		super();
	}
	public CourseDto(int id, String title, String image, int lecturesCount, int hourCount, int viewCount,
			float price, int discount, float promotionPrice, String description, String content,
			String category, Date lastUpdate) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.lecturesCount = lecturesCount;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.category = category;
		this.lastUpdate = lastUpdate;
	}
	
}
