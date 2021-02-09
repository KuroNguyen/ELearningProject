package com.myclass.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class AddCourseDto {
	
	//title phải là chữ và từ 4 tới 50 ký tự
	@Pattern(regexp = "^[a-zA-Z ]+{4,50}$", message = "Title must is words and contain from 4 to 50 characters.")
	private String title;
	private int leturesCount;
	private float price;
	private int discount;
	private float promotionPrice;
	
	//mô tả phải từ 4 tới 250 ký tự
	@NotEmpty
	@Length(min = 4, max = 250, message = "Description must be contain from 4 to 250 characters.")
	private String description;
	
	//nội dung phải từ 4 tới 250 ký tự
	@NotEmpty
	@Length(min = 4, max = 250, message = "Content must be contain from 4 to 250 characters.")
	private String content;
	
	// categoryId phải bắt đầu từ 1
	@Min(value = 1,message = "Category id must be larger than 0")
	private int categoryId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLecturesCount() {
		return leturesCount;
	}
	public void setLecturesCount(int leturesCount) {
		this.leturesCount = leturesCount;
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
