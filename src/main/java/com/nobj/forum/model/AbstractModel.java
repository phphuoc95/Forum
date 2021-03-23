package com.nobj.forum.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Setter
@Getter
public abstract class AbstractModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "created_at")
	@CreationTimestamp
	protected Date createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	protected Date updatedAt;
	
	public Long getId() {
		return this.id;
	}
	
}
