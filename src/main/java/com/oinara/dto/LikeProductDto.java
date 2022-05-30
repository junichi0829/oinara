package com.oinara.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LikeProductDto {

    @NotNull(message = "상품 아이디는 필수로 입력해야 합니다")
    private Long productId;

    @Min(value = 1, message = "최소 1개 이상 넣어주세요")
    private int count;

}
