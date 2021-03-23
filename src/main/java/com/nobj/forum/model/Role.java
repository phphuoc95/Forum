package com.nobj.forum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.nobj.forum.common.ERole;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends AbstractModel{
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	
}
