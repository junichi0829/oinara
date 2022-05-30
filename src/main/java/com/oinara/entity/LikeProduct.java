package com.oinara.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "like_product")
public class LikeProduct extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "like_product_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "like_id")
    private ProductLike productLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int count;

    public static LikeProduct createLikeProduct(ProductLike productLike, Product product, int count) {
        LikeProduct likeProduct = new LikeProduct();
        likeProduct.setProductLike(productLike);
        likeProduct.setProduct(product);
        likeProduct.setCount(count);
        return likeProduct;
    }

    public void addCount(int count) {
        this.count += count;
    }
}