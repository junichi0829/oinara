package com.oinara.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product_like")
@Getter @Setter
@ToString
public class ProductLike extends BaseEntity {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static ProductLike createProductLike(User user) {
        ProductLike productLike = new ProductLike();
        productLike.setUser(user);
        return productLike;
    }
}