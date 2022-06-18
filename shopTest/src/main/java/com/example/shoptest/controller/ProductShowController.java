package com.example.shoptest.controller;

import com.example.shoptest.entities.Product;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ProductShowController {

    @GetMapping("/get_product_list")
    List<Product> getProductList();
}
