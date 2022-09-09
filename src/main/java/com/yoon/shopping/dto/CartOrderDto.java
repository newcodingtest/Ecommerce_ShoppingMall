package com.yoon.shopping.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CartOrderDto {

    @ApiModelProperty(value = "장바구니 상품 아이디", dataType = "Long", required = true)
    private Long cartItemId;

    @ApiModelProperty(value = "장바구니 상품 아이디 리스트", dataType = "List<CartOrderDto>", required = true)
    private List<CartOrderDto> cartOrderDtoList;
}
