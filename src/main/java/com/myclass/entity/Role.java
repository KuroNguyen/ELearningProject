	package com.myclass.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.myclass.core.BaseEntity;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@Column(name = "name")
		private String name;
		
		private String description;
		
		@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
		private List<User> users;
		


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		/**
		 * @param id
		 * @param name
		 * @param description
		 */
		public Role(int id, String name, String description) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
		}

		/**
		 * 
		 */
		public Role() {
			super();
		}
		
		
		
}
