package com.yoon.shopping.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CartItemDto {

    @ApiModelProperty(value = "상품 아이디", dataType = "Long", required = true)
    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long itemId;
    
    @ApiModelProperty(value = "상품 개수", dataType = "int", required = true)
    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int count;
}
