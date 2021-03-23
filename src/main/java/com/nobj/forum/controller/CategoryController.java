package com.nobj.forum.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nobj.forum.dto.common.CategoryDto;
import com.nobj.forum.dto.response.DeatailsResponse;
import com.nobj.forum.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@RolesAllowed({"ROLE_ADMIN", "ROLE_MODERATOR"})
	@PostMapping("/categories")
	public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return ResponseEntity.ok(
				new DeatailsResponse(categoryService.save(categoryDto)));
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Object> findAllCategories(){
		return ResponseEntity.ok(
				new DeatailsResponse(categoryService.findAll()));
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<Object> findCategoryById(@Valid @PathVariable("id") Long id){
		return ResponseEntity.ok(
				new DeatailsResponse(categoryService.findById(id)));
	}
	
}
