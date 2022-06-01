package com.oinara.repository;

import com.oinara.entity.LikeProduct;
import com.oinara.entity.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeProductRepository extends JpaRepository<LikeProduct, Long> {



}
