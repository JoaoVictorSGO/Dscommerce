package com.joaovictor.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaovictor.commerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	

}
