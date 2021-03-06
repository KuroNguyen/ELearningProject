package com.myclass.entity;


import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.myclass.core.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "courses")
@Data
@ToString
@NoArgsConstructor
@Where(clause = "active=true")
@SQLDelete(sql = "UPDATE courses SET active = false WHERE id = ?")
public class Course extends BaseEntity<String>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String image;
	@Column(name = "lectures_count", nullable = false)
	private int lecturesCount;
	@Column(name = "hour_count", nullable = false)
	private int hourCount;
	@Column(name = "view_count")
	private int viewCount;
	@Column(precision = 10, scale = 2)
	private double price;
	private int discount;
	@Column(name = "promotion_price")
	private double promotionPrice;
	@Column(nullable = false)
	private String description;
	private String content;
	
	@Column(name = "category_id")
	private int categoryId;
		
	@Column(name = "last_update")
	private Date lastUpdate;
		
	@OneToMany(mappedBy = "course")
	private List<UserCourses> userCourses;
	
	@ManyToOne
	@JoinColumn(name = "category_id",insertable = false,updatable = false)
	private Category category;
	
	@OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
	private List<Target> targets;
	
	@OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
	private List<Video> videos;

}
