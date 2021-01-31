package com.myclass.core;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Data
public abstract class BaseEntity<U>{

	@Column(name = "createdBy")
	@CreatedBy
	protected U createdBy;
	@Column(name = "createdDate")
	@CreatedDate
	protected Timestamp createdDate;
	
	@Column(name = "updatedBy")
	@LastModifiedBy
	protected U updatedBy;
	@Column(name = "updatedDate")
	@LastModifiedDate
	protected Timestamp updatedDate;
	
	@Column(name = "active")
	protected boolean active = true;

}
