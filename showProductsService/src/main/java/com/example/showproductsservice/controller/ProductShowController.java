package com.example.showproductsservice.controller;

import com.example.showproductsservice.entity.Product;
import com.example.showproductsservice.service.ProductShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductShowController {
    private final ProductShowService productShowService;

    @Autowired
    public ProductShowController(ProductShowService productShowService) {
        this.productShowService = productShowService;
    }

    @GetMapping("/get_list")
    public List<Product> showProducts() {
        return productShowService.getProductList();
    }
}
