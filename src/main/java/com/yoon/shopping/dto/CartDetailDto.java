package com.yoon.shopping.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/***
 * 장바구니 조회
 */
@Getter @Setter
public class CartDetailDto {

    @ApiModelProperty(value = "장바구니 상품 아이디", dataType = "Long", required = true)
    private Long cartItemId;

    @ApiModelProperty(value = "장바구니 상품 이름", dataType = "String", required = true)
    private String itemNm;

    @ApiModelProperty(value = "장바구니 상품 가격", dataType = "int", required = true)
    private int price;

    @ApiModelProperty(value = "장바구니 상품 개수", dataType = "int", required = true)
    private int count;

    @ApiModelProperty(value = "장바구니 상품 이미지 경로", dataType = "int", required = true)
    private String imgUrl;

    public CartDetailDto(Long cartItemId, String itemNm, int price,
                         int count, String imgUrl){
        this.cartItemId = cartItemId;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
    }
}
