package com.yoon.shopping.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDto {

    @ApiModelProperty(value = "상품 번호", dataType = "long", required = true)
    private Long id;

    @ApiModelProperty(value = "상품 이름", dataType = "String", required = true)
    private String itemNm;

    @ApiModelProperty(value = "상품 가격", dataType = "int", required = true)
    private Integer price;

    @ApiModelProperty(value = "상품 상세정보", dataType = "String", required = true)
    private String itemDetail;

    @ApiModelProperty(value = "상품 상태", dataType = "String", required = true)
    private String sellStatCd;

    @ApiModelProperty(value = "상품 등록 일자", dataType = "LocalDateTime", required = true)
    private LocalDateTime regTime;

    @ApiModelProperty(value = "상품 수정 일자", dataType = "LocalDateTime", required = true)
    private LocalDateTime updateTime;
}
