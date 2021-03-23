package com.nobj.forum.service;

import java.util.List;

import com.nobj.forum.dto.common.PostDto;

public interface PostService {
	
	PostDto save(PostDto postDto);
	
	PostDto update(PostDto postDto);
	
	void delete(Long id);
	
	List<PostDto> findAll();
	
	PostDto findById(Long id);
	
	List<PostDto> findByCategoryId(Long categoryId);
	
}
