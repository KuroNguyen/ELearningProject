package com.myclass.core;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Data
public class BaseEntity<T extends BaseEntity> {

	@Column(name = "createAt")
	@CreationTimestamp
	private LocalTime createAt;
	@Column(name = "updateAt")
	@UpdateTimestamp
	private LocalTime updateAt;
	@Column(name = "active")
	private boolean active = true;

}
