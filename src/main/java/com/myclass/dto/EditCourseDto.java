package com.myclass.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class EditCourseDto {
	// id phải bắt đầu từ 1
	@Min(value = 1,message = "Course id must be larger than 0")
	private int id;

	// title phải là chữ và từ 4 tới 50 ký tự
	@Pattern(regexp = "^[a-zA-Z ]+{4,50}$", message = "Title must is words and contain from 4 to 50 characters.")
	private String title;
	private int leturesCount;
	private float price;
	private int discount;
	private float promotionPrice;
	
	// mô tả phải từ 4 tới 250 ký tự
	@NotEmpty
	@Length(min = 4, max = 250, message = "Description must be contain from 4 to 250 characters.")
	private String description;

	// nội dung phải từ 4 tới 250 ký tự
	@NotEmpty
	@Length(min = 4, max = 250, message = "Content must be contain from 4 to 250 characters.")
	private String content;
	
	// categoryId phải bắt đầu từ 1
	@Min(value = 1,message = "Category id must be larger than 0")
	private int categoryId;

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

	public int getLecturesCount() {
		return leturesCount;
	}

	public void setLeturesCount(int leturesCount) {
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

	public EditCourseDto() {
		super();
	}

	public EditCourseDto(int id, String title, int leturesCount, float price, int discount, float promotionPrice,
			String description, String content, int categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.leturesCount = leturesCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.categoryId = categoryId;
	}

	public EditCourseDto(int id2, String title2, int leturesCount2, double price2, int discount2,
			double promotionPrice2, String description2, String content2, int categoryId2) {
		// TODO Auto-generated constructor stub
	}
}

