package com.joaovictor.commerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaovictor.commerce.dto.OrderDTO;
import com.joaovictor.commerce.dto.OrderItemDTO;
import com.joaovictor.commerce.entities.Order;
import com.joaovictor.commerce.entities.OrderItem;
import com.joaovictor.commerce.entities.OrderStatus;
import com.joaovictor.commerce.entities.Product;
import com.joaovictor.commerce.repositories.OrderItemRepository;
import com.joaovictor.commerce.repositories.OrderRepository;
import com.joaovictor.commerce.repositories.ProductRepository;
import com.joaovictor.commerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired 
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true) // Indicar que é um método apenas leitura.
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id).orElseThrow( 
				() -> new ResourceNotFoundException("Recurso não encontrado") );
		return new OrderDTO(order);
		
	}
	
	@Transactional
	public  OrderDTO insert( OrderDTO dto) {
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		order.setClient(userService.authenticated());
		
		for(OrderItemDTO itemDto: dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice() );
			order.getItems().add(item);
		}
		
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		return new OrderDTO(order);
	}
}
