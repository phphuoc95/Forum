package com.nobj.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nobj.forum.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Category findOneById(Long id);
	
}
