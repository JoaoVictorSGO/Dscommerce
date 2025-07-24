package com.joaovictor.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaovictor.commerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
