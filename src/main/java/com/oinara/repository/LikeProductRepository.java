package com.oinara.repository;

import com.oinara.entity.LikeProduct;
import com.oinara.entity.Product;
import com.oinara.entity.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeProductRepository extends JpaRepository<LikeProduct, Long> {

    LikeProduct findByProductLikeAndProduct(ProductLike productLike, Product product);

}
