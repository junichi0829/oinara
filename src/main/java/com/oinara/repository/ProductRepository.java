package com.oinara.repository;

import com.oinara.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductName(String productName);

    List<Product> findByProductNameOrDescription(String productName, String description);

}
