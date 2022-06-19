package com.oinara.dto;

public class ProductLikeDetailDto {

    private Long productLikeId; // 찜하기 상품 아이디

    private String productName; // 상품명

    private int price; // 상품가격

    private String imgUrl; // 상품 이미지 경로

    public ProductLikeDetailDto(Long productLikeId, String productName, int price, String imgUrl) {
        this.productLikeId = productLikeId;
        this.productName = productName;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
