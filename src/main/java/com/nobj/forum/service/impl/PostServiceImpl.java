package com.nobj.forum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nobj.forum.converter.PostConverter;
import com.nobj.forum.dto.common.PostDto;
import com.nobj.forum.model.Category;
import com.nobj.forum.model.Post;
import com.nobj.forum.repository.CategoryRepository;
import com.nobj.forum.repository.PostRepository;
import com.nobj.forum.service.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private PostConverter postConverter;

	@Override
	public PostDto save(PostDto postDto) {
		Category categoryEntity = categoryRepository.findOneById(postDto.getCategoryId());
		Post postEntity = postConverter.toEntity(postDto);
		postEntity.setCategory(categoryEntity);
		postEntity = postRepository.save(postEntity);
		return postConverter.toDto(postEntity);
	}

	@Override
	public PostDto update(PostDto postDto) {

		Post oldPost = postRepository.getOne(postDto.getId());

		if (oldPost != null) {
			oldPost.setTitle(postDto.getTitle());
			oldPost.setContent(postDto.getContent());
			oldPost.setThumnail(postDto.getThumnail());

			Category category = categoryRepository.getOne(postDto.getCategoryId());
			if (category != null) {
				oldPost.setCategory(category);
				postRepository.save(oldPost);
			}
		}
		return postConverter.toDto(oldPost);
	}

	@Override
	public void delete(Long id) {
		postRepository.deleteById(id);
	}

	@Override
	public List<PostDto> findAll() {
		return postConverter.toDto(postRepository.findAll());
	}

	@Override
	public List<PostDto> findByCategoryId(Long categoryId) {
		return postConverter.toDto(postRepository.findByCategoryId(categoryId));
	}

	public PostDto findById(Long id) {
		return postConverter.toDto(postRepository.getOne(id));
	}

}
