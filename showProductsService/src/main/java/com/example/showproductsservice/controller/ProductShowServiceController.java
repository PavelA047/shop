package com.example.showproductsservice.controller;

import com.example.shoptest.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("shop")
public interface ProductShowServiceController {

    @RequestMapping("/get-products")
    List<ProductDto> getList();
}
