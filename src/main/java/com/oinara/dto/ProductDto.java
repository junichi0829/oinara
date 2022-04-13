package com.oinara.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDto {

    private Long id;

    private String productName;

    private Integer price;

    private String description;

    private String sellStatCd;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}
