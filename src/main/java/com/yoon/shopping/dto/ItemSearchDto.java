package com.yoon.shopping.dto;

import com.yoon.shopping.constant.ItemSellStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    @ApiModelProperty(value = "상품 날짜(all, 1d, 1w, 1m, 6m)", dataType = "String", required = true)
    private String searchDateType;

    @ApiModelProperty(value = "상품 판매 상태", dataType = "ItemSellStatus", required = true)
    private ItemSellStatus searchSellStatus;

    @ApiModelProperty(value = "상품 검색 조건(상품 이름, 판매일자)", dataType = "ItemSellStatus", required = true)
    private String searchBy;

    private String searchQuery = "";

}
