package com.oinara.repository;

import com.oinara.dto.MainProductDto;
import com.oinara.dto.ProductSearchDto;
import com.oinara.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> getAdminProductPage(ProductSearchDto productSearchDto, Pageable pageable);

    Page<MainProductDto> getMainProductPage(ProductSearchDto productSearchDto, Pageable pageable);

}
