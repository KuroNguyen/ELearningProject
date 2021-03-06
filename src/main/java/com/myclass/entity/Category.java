package com.myclass.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.myclass.core.BaseEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "categories")
@Data
@ToString

@Where(clause = "active=true")
@SQLDelete(sql = "UPDATE categories SET active = false WHERE id = ?")
public class Category extends BaseEntity<String> {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@Column(nullable = false)
		private String title;
		@Column(nullable = false)
		private String icon;
		
		@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
		private List<Course> courses;
		
//		@ManyToMany(cascade = {
//				CascadeType.PERSIST,
//				CascadeType.MERGE
//		})
//		@JoinTable(name = "course_category", joinColumns = @JoinColumn(name = "category_id"),
//		inverseJoinColumns = @JoinColumn(name = "course_id"))
//		private List<Course> courses;
		public Category() {
			super();
		}
		public Category(int id, String title, String icon) {
			super();
			this.id = id;
			this.title = title;
			this.icon = icon;
		}
}
