package com.oinara.entity;

import com.oinara.constant.ProductSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Product {
    private int product_id; //상품id

    private String product_name; //상품명

    private int price; //상품가격

    private String description; //상품 상세 설명

    private ProductSellStatus productSellStatus; //상품 판매 상태

    private LocalDateTime regTime; //등록시간
    
    private LocalDateTime updateTime; //수정시간
}
