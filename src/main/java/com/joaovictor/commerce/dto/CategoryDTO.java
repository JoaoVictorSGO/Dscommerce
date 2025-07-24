package com.joaovictor.commerce.dto;

import com.joaovictor.commerce.entities.Category;

public class CategoryDTO {
	private Long id;
	private String name;
	
	public CategoryDTO() {
	}
	public CategoryDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public CategoryDTO(Category entiry) {
		id = entiry.getId();
		name = entiry.getName();
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	
}
