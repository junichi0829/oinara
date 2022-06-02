package com.oinara.repository;

import com.oinara.entity.LikeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeProductRepository extends JpaRepository<LikeProduct, Long> {

//    LikeProduct findByLikeIdAndProductId(Long likeId, Long productId);

}
