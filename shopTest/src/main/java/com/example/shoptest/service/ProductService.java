package com.example.shoptest.service;

import com.example.shoptest.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    void addToUserBucket(Long productId, String username);

    Product getProductById(Long id);

    boolean isProductWithTitleExists(String productTitle);

    void saveProduct(Product product);
}
