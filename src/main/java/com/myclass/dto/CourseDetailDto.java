package com.myclass.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Author: Nguyen Chanh Truc
 * Created: Mar 1, 2021	
 */
@Data
public class CourseDetailDto {
	private int id;
	private String title;
	private String image;
	private int lecturesCount;
	private int hourCount;
	private int viewCount;
	private double price;
	private int discount;
	private double promotionPrice;
	private String description;
	private String content;
	private int categoryId;
	private String categoryName;
	private Date lastUpdate;
	private List<TargetDto> targets;
	private List<VideoDto> videos;
}
