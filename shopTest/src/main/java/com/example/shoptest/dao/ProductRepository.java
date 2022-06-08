package com.example.shoptest.dao;

import com.example.shoptest.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findOneByTitle(String title);
}
