package com.example.showproductsservice.service;

import com.example.showproductsservice.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "shop", contextId = "product-show-service")
public interface ProductShowService {

    @GetMapping("/get_product_list")
    List<Product> getProductList();
}
