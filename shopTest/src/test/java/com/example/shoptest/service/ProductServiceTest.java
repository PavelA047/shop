package com.example.shoptest.service;

import com.example.shoptest.dao.ProductRepository;
import com.example.shoptest.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@SpringBootTest
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
//    private TestEntityManager entityManager;

    @Test
    void getAll() {
        Mockito.when(productRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(
                new Product(1L, "TuTu", new BigDecimal(555), null, null),
                new Product(2L, "MuMu", new BigDecimal(777), null, null),
                new Product(3L, "BuBu", new BigDecimal(888), null, null)
        )));
        Assertions.assertEquals("BuBu", productService.getAll().get(2).getTitle());
    }

    @Test
    void addToUserBucket() {
    }

    @Test
    void getProductById() {
        Product product = new Product(11111L, "TuTu", new BigDecimal(555), null, null);
        Mockito.when(productRepository.findById(12L)).thenReturn(
                Optional.of(new Product(12L, "TuTu", new BigDecimal(555), null, null))
        );
        Assertions.assertEquals(product.getTitle(), productRepository.findById(12L).get().getTitle());
    }

    @Test
    void isProductWithTitleExists() {
    }

    @Test
    void saveProduct() {
    }
}