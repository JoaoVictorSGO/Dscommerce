package com.joaovictor.commerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaovictor.commerce.dto.ProductDTO;
import com.joaovictor.commerce.dto.ProductMinDTO;
import com.joaovictor.commerce.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class CategoryController {

	@Autowired
	private ProductService service;
	//@PathVariable -> parametro de rota(Obrigatorio!) URL
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
		
	}
	//@RequestParam -> parametro de consulta(Opcional!) ?name= ""
	
	@GetMapping 
	public ResponseEntity<Page<ProductMinDTO>> findAll(
			@RequestParam(name = "name", defaultValue = "") String name,Pageable pageable) {
		Page<ProductMinDTO> dto = service.findAll(name,pageable);
		return ResponseEntity.ok(dto);
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping // Anotação @Valid serve para ter uma validação
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto); // O retorno da URI vem no header
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id,@Valid  @RequestBody ProductDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build(); // Como não tem corpo, o código é 204
	}
	

}
