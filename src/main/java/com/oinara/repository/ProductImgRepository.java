package com.oinara.repository;

import com.oinara.entity.Product;
import com.oinara.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
    //    List<ProductImg> findByProductIdOrderByIdAsc(Long productId);
    List<ProductImg> findByProductOrderByIdAsc(Product product);
}