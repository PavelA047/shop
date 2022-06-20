package com.example.showproductsservice.entity;

public class ProductImage {
    private static final String SEQ_NAME = "products_images_seq";

    private Long id;

    private Product product;

    private String path;

    public ProductImage(Long id, Product product, String path) {
        this.id = id;
        this.product = product;
        this.path = path;
    }

    public ProductImage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}