package com.example.shoptest.controller;

import com.example.shoptest.dto.ProductDto;
import com.example.shoptest.mapper.ProductMapper;
import com.example.shoptest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductServiceControllerImpl implements ProductServiceController {
    private final ProductService productService;

    @Autowired
    public ProductServiceControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductDto> getList() {
        return ProductMapper.MAPPER.fromProductList(productService.getAll());
    }
}
