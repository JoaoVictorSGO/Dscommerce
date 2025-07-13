package com.devsuperior.aula_m4.dto;

import com.devsuperior.aula_m4.entities.Department;

public class DepartmentDTO {
	private Long id;
	private String name;
	public DepartmentDTO() {}
	public DepartmentDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public DepartmentDTO(Department entity) {
		id  = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
}
