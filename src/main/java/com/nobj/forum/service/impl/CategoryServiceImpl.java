package com.nobj.forum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nobj.forum.converter.CategoryConverter;
import com.nobj.forum.dto.common.CategoryDto;
import com.nobj.forum.model.Category;
import com.nobj.forum.repository.CategoryRepository;
import com.nobj.forum.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryConverter categoryConverter;
	
	@Autowired
	private CategoryRepository categoryRespository;

	@Override
	public CategoryDto save(CategoryDto dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		categoryRespository.save(entity);
		return categoryConverter.toDto(entity);
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CategoryDto> save(List<CategoryDto> categoriesDTO) {
		List<Category> entities = categoryConverter.toEntity(categoriesDTO);
		categoryRespository.saveAll(entities);
		return categoryConverter.toDto(entities);
	}

	@Override
	public List<CategoryDto> findAll() {
		return categoryConverter.toDto(categoryRespository.findAll());
	}

	@Override
	public CategoryDto findById(Long id) {
		return categoryConverter.toDto(categoryRespository.getOne(id));
	}
	
}
