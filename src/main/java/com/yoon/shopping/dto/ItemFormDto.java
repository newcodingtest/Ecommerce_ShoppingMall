package com.yoon.shopping.dto;

import com.yoon.shopping.constant.ItemSellStatus;
import com.yoon.shopping.entity.Item;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemFormDto {

    @ApiModelProperty(value = "상품아이디", dataType = "Long", required = true)
    private Long id;

    @ApiModelProperty(value = "상품명", dataType = "string", required = true)
    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @ApiModelProperty(value = "상품가격", dataType = "Integer", required = true)
    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @ApiModelProperty(value = "상품이름", dataType = "string", required = true)
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String itemDetail;

    @ApiModelProperty(value = "상품재고", dataType = "Integer", required = true)
    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    @ApiModelProperty(value = "상품상태", dataType = "ItemSellStatus", required = true)
    private ItemSellStatus itemSellStatus;

    @ApiModelProperty(value = "상품이미지", dataType = "ItemImgDto", required = true)
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item){
        return modelMapper.map(item, ItemFormDto.class);
    }
}
