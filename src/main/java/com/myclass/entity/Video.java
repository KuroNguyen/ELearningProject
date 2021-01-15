package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.myclass.core.BaseEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
public class Video extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String url;
	@Column(name = "time_count", nullable = false)
	private int timeCount;
	@Column(name = "course_id")
	private int courseId;
	
	@ManyToOne
	@JoinColumn(name = "course_id",insertable = false,updatable = false)
	private Course course;

}
