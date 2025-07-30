package com.joaovictor.commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaovictor.commerce.dto.CategoryDTO;
import com.joaovictor.commerce.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class ProductController {

	@Autowired
	private CategoryService service;
	@GetMapping 
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<CategoryDTO> dto = service.findAll();
		return ResponseEntity.ok(dto);
	}
	
	

}
