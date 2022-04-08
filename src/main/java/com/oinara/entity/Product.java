package com.oinara.entity;

import com.oinara.constant.ProductSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int product_id; //상품id

    @Column(name = "product_name", nullable = false, length = 50)
    private String product_name; //상품명

    @Column(name = "price", nullable = false)
    private int price; //상품가격

    @Lob
    @Column(name = "description", nullable = false)
    private String description; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus; //상품 판매 상태

    private LocalDateTime regTime; //등록시간
    
    private LocalDateTime updateTime; //수정시간
}
