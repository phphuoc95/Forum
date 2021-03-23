package com.nobj.forum.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nobj.forum.dto.common.PostDto;
import com.nobj.forum.dto.response.DeatailsResponse;
import com.nobj.forum.exception.CustomApiRequestException;
import com.nobj.forum.service.PostService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/posts")
	public ResponseEntity<?> findAllPost(@Valid @RequestParam(value = "categoryId", defaultValue = "") Long categoryId) {
		List<PostDto> data = null;
		
		if (categoryId != null) {
			data = postService.findByCategoryId(categoryId);
			
		} else {
			data = postService.findAll();
		}
		
		if(data.isEmpty()) {
			throw new CustomApiRequestException("The data is empty");
		}
		
		return ResponseEntity.ok(new DeatailsResponse(data));
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<?> findPostById(@Valid @PathVariable("id") Long id) {
		Object data = postService.findById(id);
		return ResponseEntity.ok(new DeatailsResponse(data));
	}
	
	@RolesAllowed({"ROLE_ADMIN"})
	@PostMapping("/posts")
	public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto) {
		Object data = postService.save(postDto);
		return ResponseEntity.ok(new DeatailsResponse(201, "Post's created!", data));
	}
	
	@RolesAllowed({"ROLE_ADMIN", "ROLE_MODERATOR"})
	@PutMapping("/posts")
	public ResponseEntity<?> UpdatePost(@Valid @RequestBody PostDto postDto) {
		Object	data = postService.update(postDto);
		return ResponseEntity.ok(data);
	}
	
	@RolesAllowed({"ROLE_ADMIN", "ROLE_MODERATOR"})
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<?> deletePost(@PathVariable Long id) {
		postService.delete(id);
		return ResponseEntity.ok(new DeatailsResponse(200, "Post delected", "Id: " + id));
	}

}
