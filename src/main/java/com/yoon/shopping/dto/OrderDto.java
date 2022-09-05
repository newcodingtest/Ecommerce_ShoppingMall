package com.yoon.shopping.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class OrderDto {

    @ApiModelProperty(value = "주문 상품아이디", dataType = "Long", required = true)
    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long itemId;

    @ApiModelProperty(value = "주문 수량", dataType = "int", required = true)
    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    @Max(value = 999, message = "최대 주문 수량은 999개 입니다.")
    private int count;
}
