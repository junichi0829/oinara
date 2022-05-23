package com.oinara.dto;

import com.oinara.constant.ProductSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductSearchDto {

    private String searchDataType;

    private ProductSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";

}
