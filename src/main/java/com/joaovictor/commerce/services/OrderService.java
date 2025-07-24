package com.joaovictor.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaovictor.commerce.dto.OrderDTO;
import com.joaovictor.commerce.entities.Order;
import com.joaovictor.commerce.repositories.OrderRepository;
import com.joaovictor.commerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true) // Indicar que é um método apenas leitura.
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id).orElseThrow( 
				() -> new ResourceNotFoundException("Recurso não encontrado") );
		return new OrderDTO(order);
		
	}
}
