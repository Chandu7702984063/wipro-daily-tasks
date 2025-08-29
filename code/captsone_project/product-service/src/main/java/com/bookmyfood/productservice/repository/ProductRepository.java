package com.bookmyfood.productservice.repository;

import com.bookmyfood.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
