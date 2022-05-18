package com.oinara.dto;

import com.oinara.constant.ProductSellStatus;
import com.oinara.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ProductFormDto {

    private Long id;

    @NotBlank(message = "상품명 입력은 필수입니다.")
    private String ProductNm;

    @NotNull(message = "가격 입력은 필수입니다.")
    private Integer price;

    @NotBlank(message = "이릅 입력은 필수입니다.")
    private String productDetail;

    private ProductSellStatus productSellStatus;

    private List<ProductImgDto> productImgDtoList = new ArrayList<>();

    private List<Long> productImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Product createProduct() {
        return modelMapper.map(this, Product.class);
    }

    public static ProductFormDto of(Product product) {
        return modelMapper.map(product, ProductFormDto.class);
    }
}