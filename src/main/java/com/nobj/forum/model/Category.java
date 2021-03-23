package com.nobj.forum.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Setter
@Getter
public class Category extends AbstractModel {
	
	@Column(name = "name")
	private String name;
	
	@OneToMany (mappedBy = "category")
	private List<Post> postEntities = new ArrayList<>();

}
