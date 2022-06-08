package com.example.showproductsservice.controller;

import com.example.shoptest.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductShowController {
    private final ProductShowServiceController productShowServiceController;

    @Autowired
    public ProductShowController(ProductShowServiceController productShowServiceController) {
        this.productShowServiceController = productShowServiceController;
    }

    @RequestMapping("/get_product_list")
    public String getProductList(Model model) {
        List<ProductDto> products = productShowServiceController.getList();
        model.addAttribute(products);
        return "product_list_view";
    }
}
