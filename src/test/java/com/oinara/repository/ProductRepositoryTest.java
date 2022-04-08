package com.oinara.repository;

import com.oinara.constant.ProductSellStatus;
import com.oinara.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.sound.sampled.Port;
import java.time.LocalDateTime;
import java.util.List;

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

    public void createProductList() {
        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setProductName("테스트 상품" + i);
            product.setPrice(10000 + i);
            product.setDescription("테스트 상품 상세 설명" + i);
            product.setProductSellStatus(ProductSellStatus.SELL);
            product.setUpdateTime(LocalDateTime.now());
            Product savedProduct = productRepository.save(product);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByProductNameTest() {
        this.createProductList();
        List<Product> productList = productRepository.findByProductName("테스트 상품1");
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByProductNameOrDescriptionTest() {
        this.createProductList();
        List<Product> productList = productRepository.findByProductNameOrDescription("테스트 상품1", "테스트 상품 상세 설명5");
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createProductList();
        List<Product> productList = productRepository.findByPriceLessThan(10005);
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc() {
        this.createProductList();
        List<Product> productList = productRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }
}