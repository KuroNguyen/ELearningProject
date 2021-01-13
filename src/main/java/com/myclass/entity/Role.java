package com.myclass.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.myclass.core.BaseEntity;

@Entity
public class Role extends BaseEntity{
	
		@Id
		private int roleId;
		

}
