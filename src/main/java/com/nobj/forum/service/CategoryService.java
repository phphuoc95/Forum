package com.nobj.forum.service;

import java.util.List;

import com.nobj.forum.dto.common.CategoryDto;

public interface CategoryService {
	
	CategoryDto save(CategoryDto categoryDto);
	
	CategoryDto update(CategoryDto categoryDto);
	
	String delete(CategoryDto categoryDto);
	
	CategoryDto findById(Long id);
	
	List<CategoryDto> findAll();
	
	List<CategoryDto> save(List<CategoryDto> categoriesDTO);
	
}
