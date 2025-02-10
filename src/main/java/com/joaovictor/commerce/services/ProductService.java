package com.joaovictor.commerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaovictor.commerce.dto.ProductDTO;
import com.joaovictor.commerce.entities.Product;
import com.joaovictor.commerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	
	@Transactional(readOnly = true) // Indicar que é um método apenas leitura.
	public ProductDTO findById(Long id) {
		Optional<Product> result = repository.findById(id);  // findById retorna um tipo "Optional".
		Product product = result.orElse(new Product());// Caso retorne um 'Optional.empty()'. ele por padrão vai retornar um objeto vazio.
		ProductDTO dto = new ProductDTO(product);
		return dto;
	}
}
