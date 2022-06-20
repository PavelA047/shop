package com.example.shoptest.controller;

import com.example.shoptest.entities.*;
import com.example.shoptest.service.BucketService;
import com.example.shoptest.service.OrderService;
import com.example.shoptest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/{bucket_sum}")
    public String fillOrder(@PathVariable(name = "bucket_sum") Double bucket_sum, Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        Order order = new Order();
        order.setSum(new BigDecimal(bucket_sum));
        model.addAttribute("order", order);
        return "order";
    }

    @PostMapping
    public String saveOrder(Principal principal, Model model, @ModelAttribute("order") Order order) {
        User user = userService.findByName(principal.getName());
        order.setUser(user);
        order.setStatus(OrderStatus.NEW);
        List<Product> products = user.getBucket().getProducts();
        Map<Long, OrderDetails> mapByProductId = new HashMap<>();
        for (Product product : products) {
            OrderDetails detail = mapByProductId.get(product.getId());
            if (detail == null) {
                mapByProductId.put(product.getId(), new OrderDetails(order, product, new BigDecimal(1), product.getPrice()));
            } else {
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                detail.setPrice(detail.getPrice().add(product.getPrice()));
            }
        }
        order.setDetails(mapByProductId.values().stream().toList());
        orderService.save(order);
        return "redirect:/bucket";
    }
}
