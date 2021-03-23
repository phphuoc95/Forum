package com.nobj.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nobj.forum.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findByCategoryId(Long categoryId);
	
	List<Post> findAll();
	
	@Query(value = "select category_id from category c join "
			+ "post p on c.id = p.category_id where p.id = ?1", 
			  nativeQuery = true)
	Long getCategoryId(Long id);
	
}
