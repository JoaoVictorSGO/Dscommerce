package com.devsuperior.aula_m4.dto;

import com.devsuperior.aula_m4.entities.Person;

public class PersonDTO {
	private Long id;
	private String name;
	private Double salary;
	private Long departmentId;
	public PersonDTO(Long id, String name, Double salary, Long departmentId) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.departmentId = departmentId;
	}
	public PersonDTO() {
	}
	
	public PersonDTO(Person entity) {
		id = entity.getId();
		name = entity.getName();
		salary = entity.getSalary();
		departmentId = entity.getDepartment().getId();
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Double getSalary() {
		return salary;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	
	
	
}
