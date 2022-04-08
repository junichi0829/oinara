package com.oinara.repository;

import com.oinara.constant.ProductSellStatus;
import com.oinara.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createProductTest() {
        Product product = new Product();
        product.setProductName("테스트 상품");
        product.setPrice(10000);
        product.setDescription("테스트 상품 상세 설명");
        product.setProductSellStatus(ProductSellStatus.SELL);
        product.setRegTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        System.out.println(savedProduct.toString());
    }
}