package com.nobj.forum.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nobj.forum.dto.common.PostDto;
import com.nobj.forum.model.Post;
import com.nobj.forum.repository.PostRepository;

@Component
public class PostConverter {
	
	@Autowired
	PostRepository postRepository;

	public Post toEntity(PostDto dto) {
		Post entity = new Post();
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		entity.setThumnail(dto.getThumnail());
		return entity;
	}

	public PostDto toDto(Post entity) {
		PostDto dto = new PostDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setShortDescription(entity.getShortDescription());
		dto.setThumnail(entity.getThumnail());
		dto.setCategoryId(postRepository.getCategoryId(entity.getId()));
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		return dto;
	}

	public List<PostDto> toDto(List<Post> entities) {
		return entities.stream().map(
				x -> toDto(x)).collect(
						Collectors.toList());
	}

	public List<Post> toEntity(List<PostDto> dtos) {
		return dtos.stream().map(
				x -> toEntity(x)).collect(
						Collectors.toList());
	}

}
