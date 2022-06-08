package com.example.shoptest.controller;

import com.example.shoptest.dto.BucketDto;
import com.example.shoptest.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/bucket")
public class BucketController {
    private final BucketService bucketService;

    @Autowired
    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping
    public String aboutBucket(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("bucket", new BucketDto());
        } else {
            BucketDto bucketDto = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDto);
        }
        return "bucket";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/bucket";
        }

        bucketService.deleteFromUserBucket(id, principal.getName());
        return "redirect:/bucket";
    }
}
