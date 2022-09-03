package com.yoon.shopping.dto;

import com.yoon.shopping.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    //all, 1d, 1w, 1m, 6m
    private String searchDateType;

    private ItemSellStatus searchSellStatus;

    //itemNm, createBy
    private String searchBy;

    private String searchQuery = "";

}
