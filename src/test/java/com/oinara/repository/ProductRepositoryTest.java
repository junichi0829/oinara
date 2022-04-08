package com.oinara.repository;

import com.oinara.constant.ProductSellStatus;
import com.oinara.entity.Product;
import com.oinara.entity.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sound.sampled.Port;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductRepositoryTest {

    @PersistenceContext
    EntityManager em;

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

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByDescriptionTest() {
        this.createProductList();
        List<Product> productList = productRepository.findByDescription("테스트 상품 상세 설명");
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    public void findByDescriptionByNative() {
        this.createProductList();
        List<Product> productList = productRepository.findByDescriptionByNative("테스트 상품 상세 설명");
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("Querydsl 조회 테스트1")
    public void queryDslTest() {
        this.createProductList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QProduct qProduct = QProduct.product;
        JPAQuery<Product> query = queryFactory.selectFrom(qProduct)
                .where(qProduct.productSellStatus.eq(ProductSellStatus.SELL))
                .where(qProduct.description.like("%" + "테스트 상품 상세 설명" + "%"))
                .orderBy(qProduct.price.desc());

        List<Product> productList = query.fetch();

        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    public void createProductList2() {
        for (int i = 1; i <= 5; i++) {
            Product product = new Product();
            product.setProductName("테스트 상품" + i);
            product.setPrice(10000 + i);
            product.setDescription("테스트 상품 상세 설명" + i);
            product.setProductSellStatus(ProductSellStatus.SELL);
            product.setRegTime(LocalDateTime.now());
            product.setUpdateTime(LocalDateTime.now());
            productRepository.save(product);
        }

        for (int i = 6; i <= 10; i++) {
            Product product = new Product();
            product.setProductName("테스트 상품" + i);
            product.setPrice(10000 + i);
            product.setDescription("테스트 상품 상세 설명" + i);
            product.setProductSellStatus(ProductSellStatus.SOLD_OUT);
            product.setRegTime(LocalDateTime.now());
            product.setUpdateTime(LocalDateTime.now());
            productRepository.save(product);
        }
    }

    @Test
    @DisplayName("상품 Querydsl 조회 테스트2")
    public void queryDslTest2() {
        this.createProductList2();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QProduct product = QProduct.product;
        String description = "테스트 상품 상세 설명";
        int price = 10003;
        String productSellStat = "SELL";

        booleanBuilder.and(product.description.like("%" + description + "%"));
        booleanBuilder.and(product.price.gt(price));

        if (StringUtils.equals(productSellStat, ProductSellStatus.SELL)) {
            booleanBuilder.and(product.productSellStatus.eq(ProductSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 5);
        Page<Product> productPagingResult = productRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + productPagingResult.getTotalElements());

        List<Product> resultProductList = productPagingResult.getContent();
        for (Product resultProduct : resultProductList) {
            System.out.println(resultProduct.toString());
        }
    }
}