package com.oinara.service;

import com.oinara.constant.ProductSellStatus;
import com.oinara.dto.LikeProductDto;
import com.oinara.entity.LikeProduct;
import com.oinara.entity.Product;
import com.oinara.entity.User;
import com.oinara.repository.LikeProductRepository;
import com.oinara.repository.ProductRepository;
import com.oinara.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductLikeServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductLikeService productLikeService;

    @Autowired
    LikeProductRepository likeProductRepository;

    public Product saveProduct() {
        Product product = new Product();
        product.setProductName("테스트 상품");
        product.setPrice(10000);
        product.setDescription("테스트 상품 상세 설명");
        product.setProductSellStatus(ProductSellStatus.SELL);
        return productRepository.save(product);
    }

    public User saveUser() {
        User user = new User();
        user.setAccount("lee");
        return userRepository.save(user);
    }

    @Test
    @DisplayName("찜하기 기능 테스트")
    public void addLike() {
        Product product = saveProduct();
        User user = saveUser();

        LikeProductDto likeProductDto = new LikeProductDto();
        likeProductDto.setCount(5);
        likeProductDto.setProductId(product.getId());

        Long likeProductId = productLikeService.addProductLike(likeProductDto, user.getAccount());

        LikeProduct likeProduct = likeProductRepository.findById(likeProductId).orElseThrow(EntityNotFoundException::new);

        assertEquals(product.getId(), likeProduct.getProduct().getId());
        assertEquals(likeProductDto.getCount(), likeProduct.getCount());
    }
}
