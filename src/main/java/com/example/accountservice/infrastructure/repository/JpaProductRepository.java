package com.example.accountservice.infrastructure.repository;

import com.example.accountservice.infrastructure.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, Long> {
}
