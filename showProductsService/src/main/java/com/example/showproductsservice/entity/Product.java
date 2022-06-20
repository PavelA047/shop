package com.example.showproductsservice.entity;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private static final String SEQ_NAME = "product_seq";

    private Long id;

    private String title;

    private BigDecimal price;

    private List<Category> categories;

    private List<ProductImage> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public Product(Long id, String title, BigDecimal price, List<Category> categories, List<ProductImage> images) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categories = categories;
        this.images = images;
    }

    public Product() {
    }
}