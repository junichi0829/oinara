package com.oinara.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainProductDto {

    private long id;

    private String productName;

    private String description;

    private String imgUrl;

    private Integer price;

    @QueryProjection
    public MainProductDto(Long id, String productName, String description, String imgUrl, Integer price) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }
}
