package com.joaovictor.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaovictor.commerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
