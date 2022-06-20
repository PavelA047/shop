package com.example.shoptest.controller;

import com.example.shoptest.entities.Product;
import com.example.shoptest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductShowControllerImpl implements ProductShowController {
    private ProductService productService;

    @Autowired
    public ProductShowControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> getProductList() {
        return productService.getAll();
    }
}