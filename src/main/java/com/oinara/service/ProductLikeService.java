package com.oinara.service;

import com.oinara.dto.LikeProductDto;
import com.oinara.dto.ProductLikeDetailDto;
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
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductLikeService {

    private final ProductRepository productRepository;
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

        LikeProduct savedLikeProduct = likeProductRepository.findByProductLikeAndProduct(productLike, product);

        if (savedLikeProduct != null) {
            savedLikeProduct.addCount(likeProductDto.getCount());
            return savedLikeProduct.getId();
        } else {
            LikeProduct likeProduct = LikeProduct.createLikeProduct(productLike, product, likeProductDto.getCount());
            likeProductRepository.save(likeProduct);
            return likeProduct.getId();
        }


    }

    @Transactional(readOnly = true)
    public List<ProductLikeDetailDto> getProductLikeList(String account) {

        List<ProductLikeDetailDto> productLikeDetailDtoList = new ArrayList<>();

        User user = userRepository.findByAccount(account);
        ProductLike productLike = productLikeRepository.findByUserId(user.getId());
        if (productLike == null) {
            return productLikeDetailDtoList;
        }

        productLikeDetailDtoList = likeProductRepository.findProductLikeDetailDtoList(productLike.getId());

        return productLikeDetailDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateLikeProduct(Long likeProductId, String account) {

        User curUser = userRepository.findByAccount(account);
        LikeProduct likeProduct = likeProductRepository.findById(likeProductId).orElseThrow(EntityNotFoundException::new);
        User savedUser = likeProduct.getProductLike().getUser();

        if (!StringUtils.equals(curUser.getAccount(), savedUser.getAccount())) {
            return false;
        }

        return true;
    }

    public void deleteLikeProduct(Long likeProductId) {
        LikeProduct likeProduct = likeProductRepository.findById(likeProductId).orElseThrow(EntityNotFoundException::new);
        likeProductRepository.delete(likeProduct);
    }
}