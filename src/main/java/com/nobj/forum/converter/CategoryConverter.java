package com.nobj.forum.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nobj.forum.dto.common.CategoryDto;
import com.nobj.forum.model.Category;

@Component
public class CategoryConverter {

	public Category toEntity(CategoryDto dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		return entity;
	}

	public CategoryDto toDto(Category entity) {
		CategoryDto dto = new CategoryDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}

	public List<CategoryDto> toDto(List<Category> entities) {
		return entities.stream().map(x -> toDto(x)).collect(Collectors.toList());
	}

	public List<Category> toEntity(List<CategoryDto> dtos) {
		return dtos.stream().map(x -> toEntity(x)).collect(Collectors.toList());
	}

}
