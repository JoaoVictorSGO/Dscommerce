package com.devsuperior.aula_m4.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.aula_m4.dto.PersonDTO;
import com.devsuperior.aula_m4.dto.PersonDepartmentDTO;
import com.devsuperior.aula_m4.services.PersonService;

@RestController
@RequestMapping(value = "/people")
public class PersonController {
	
	private PersonService service;
	
	
	
	public PersonController(PersonService service) {
		super();
		this.service = service;
	}



	//@PostMapping 
	public ResponseEntity<PersonDepartmentDTO> insert( @RequestBody PersonDepartmentDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto); // O retorno da URI vem no header
	}
	
	@PostMapping 
	public ResponseEntity<PersonDTO> insert( @RequestBody PersonDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto); // O retorno da URI vem no header
	}
}
