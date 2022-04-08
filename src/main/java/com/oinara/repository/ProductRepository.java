package com.oinara.repository;

import com.oinara.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    List<Product> findByProductName(String productName);

    List<Product> findByProductNameOrDescription(String productName, String description);

    List<Product> findByPriceLessThan(Integer price);

    List<Product> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select p from Product p where p.description like %:description% order by p.price desc")
    List<Product> findByDescription(@Param("description") String description);

    @Query(value = "select * from product p where p.description like %:description% order by p.price desc", nativeQuery = true)
    List<Product> findByDescriptionByNative(@Param("description") String description);
}
