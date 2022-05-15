package com.oinara.entity;

import com.oinara.constant.ProductSellStatus;
import com.oinara.repository.OrderRepository;
import com.oinara.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @PersistenceContext
    EntityManager em;

    public Product createProduct() {
        Product product = new Product();
        product.setProductName("테스트 상품");
        product.setPrice(10000);
        product.setDescription("상세설명");
        product.setProductSellStatus(ProductSellStatus.SELL);
        product.setRegTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        return product;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest() {

        Order order = new Order();

        for (int i = 0; i < 3; i++) {
            Product product = this.createProduct();
            productRepository.save(product);
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setCount(10);
            orderProduct.setOrderPrice(1000);
            orderProduct.setOrder(order);
            order.getOrderProducts().add(orderProduct);
        }

        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);
        assertEquals(3, savedOrder.getOrderProducts().size());
    }
}
