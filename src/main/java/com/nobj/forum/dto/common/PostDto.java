package com.nobj.forum.dto.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDto extends AuditDto {
	
	@NotNull
	@Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters in length")
	private String title;
	
	@NotNull
	@Size(min = 2, message = "Content must be more than 2 characters in length")
	private String content;
	
	@NotNull
	@Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters long")
	private String shortDescription;
	
	@NotNull(message = "Category ID can not be null")
	private Long categoryId;
	
	private String thumnail;
	
	public void setCategoryId(Long id) {
		this.categoryId = id;
	}
	
}
