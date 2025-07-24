package com.joaovictor.commerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joaovictor.commerce.dto.CategoryDTO;
import com.joaovictor.commerce.dto.ProductDTO;
import com.joaovictor.commerce.dto.ProductMinDTO;
import com.joaovictor.commerce.entities.Category;
import com.joaovictor.commerce.entities.Product;
import com.joaovictor.commerce.repositories.ProductRepository;
import com.joaovictor.commerce.services.exceptions.DatabaseException;
import com.joaovictor.commerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	
	@Transactional(readOnly = true) // Indicar que é um método apenas leitura.
	public ProductDTO findById(Long id) {
		Optional<Product> result = repository.findById(id);  // findById retorna um tipo "Optional".
		Product product = result.orElseThrow( //veifica exite, caso não haja ele laça uma exceção
				() -> new ResourceNotFoundException("Recurso não encontrado") );
		ProductDTO dto = new ProductDTO(product);
		return dto;
	}
	
	@Transactional(readOnly = true) 
	public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
		Page<Product> result = repository.searchByName(name, pageable);
		return result.map(x -> new ProductMinDTO(x));
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new  Product(); // Não monitorado Pela JPA
		copyDtoEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id); // Objeto monitorado pela JPA
			copyDtoEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			 throw new ResourceNotFoundException("Recurso não encontrado");
		}
		
		
	}

	@Transactional(propagation = Propagation.SUPPORTS) //Entra se tiver transação, senão vai sem.
	public void delete(Long id) {
		if(!repository.existsById(id)) { // Testa se existe ID.
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) { //  violação referencial
			throw new DatabaseException("Operação inválida: esse dado está relacionado com outro.");
		}
		
	}
	
	
	//copia o dto para a entidade.
	private void copyDtoEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		entity.getCategories().clear(); //limpar categoria
		for(CategoryDTO catDto : dto.getCategories()) {
			Category cat = new Category();
			cat.setId(catDto.getId());
			entity.getCategories().add(cat);
		}
	}
}
