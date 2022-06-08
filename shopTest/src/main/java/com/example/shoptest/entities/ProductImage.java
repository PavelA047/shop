package com.example.shoptest.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products_images")
@Data
public class ProductImage {
    private static final String SEQ_NAME = "products_images_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "path")
    private String path;
}

