package com.myclass.core;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {

	@Column(name = "creatAt")
	@CreationTimestamp
	private LocalTime creatAt;
	@Column(name = "updateAt")
	@UpdateTimestamp
	private LocalTime updateAt;
	@Column(name = "active")
	private boolean active;
	
}
