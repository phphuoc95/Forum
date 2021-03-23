package com.nobj.forum.dto.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDto extends AuditDto{
	
	@NotNull(message = "Category name must not be null")
	@Size(min = 2, max = 32, message = "Category name must be between 2 and 32 characters long")
	private String name;
	
}
