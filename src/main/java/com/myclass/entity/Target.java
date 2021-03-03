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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.myclass.core.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "targets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Where(clause = "active=true")
@SQLDelete(sql = "UPDATE targets SET active = false WHERE id = ?")
public class Target extends BaseEntity<String>{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@Column(nullable = false)
		private String title;
		@Column(name = "course_id")
		private int courseId;
	
		@ManyToOne
		@JoinColumn(name = "course_id",insertable = false,updatable = false)
		private Course course;

}
