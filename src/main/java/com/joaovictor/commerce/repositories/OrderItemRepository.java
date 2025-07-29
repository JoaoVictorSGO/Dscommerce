package com.joaovictor.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaovictor.commerce.dto.OrderItemDTO;
import com.joaovictor.commerce.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemDTO> {

}
