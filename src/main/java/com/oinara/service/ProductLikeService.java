package com.oinara.service;

import com.oinara.dto.LikeProductDto;
import com.oinara.entity.LikeProduct;
import com.oinara.entity.Product;
import com.oinara.entity.ProductLike;
import com.oinara.entity.User;
import com.oinara.repository.LikeProductRepository;
import com.oinara.repository.ProductLikeRepository;
import com.oinara.repository.ProductRepository;
import com.oinara.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductLikeService {

    /*private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductLikeRepository productLikeRepository;
    private final LikeProductRepository likeProductRepository;

    public Long addProductLike(LikeProductDto likeProductDto, String account) {
        Product product = productRepository.findById(likeProductDto.getProductId()).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findByAccount(account);

        ProductLike productLike = productLikeRepository.findByUserId(user.getId());
        if (productLike == null) {
            productLike = ProductLike.createProductLike(user);
            productLikeRepository.save(productLike);
        }

        LikeProduct savedLikeProduct = likeProductRepository.findByLikeIdAndProductId(productLike.getId(), product.getProductId());

        if (savedLikeProduct != null) {
            savedLikeProduct.addCount(likeProductDto.getCount());
            return savedLikeProduct.getId();
        } else {
            LikeProduct likeProduct = LikeProduct.createLikeProduct(productLike, product, likeProductDto.getCount());
            likeProductRepository.save(likeProduct);
            return likeProduct.getId();
        }
    }*/
}