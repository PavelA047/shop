package com.example.shoptest.controller;

import com.example.shoptest.dto.ProductDto;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface ProductServiceController {
    @RequestMapping("/get-products")
    List<ProductDto> getList();
}
