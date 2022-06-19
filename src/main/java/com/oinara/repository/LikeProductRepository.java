package com.oinara.repository;

import com.oinara.dto.ProductLikeDetailDto;
import com.oinara.entity.LikeProduct;
import com.oinara.entity.Product;
import com.oinara.entity.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeProductRepository extends JpaRepository<LikeProduct, Long> {

    LikeProduct findByProductLikeAndProduct(ProductLike productLike, Product product);

    @Query("select new com.oinara.dto.ProductLikeDetailDto(lp.id, p.productName, p.price, pm.imgUrl)" +
            "from LikeProduct lp, ProductImg pm " +
            "join lp.product p " +
            "where lp.productLike.id = :likeId " +
            "and pm.product.id = lp.product.id " +
            "and pm.repImgYn = 'Y' " +
            "order by lp.regTime desc")
    List<ProductLikeDetailDto> findProductLikeDetailDtoList(Long likeId);

}
