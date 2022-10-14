package com.example.applicationgateway.repository;

import com.example.applicationgateway.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
