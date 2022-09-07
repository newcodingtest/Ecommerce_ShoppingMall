package com.yoon.shopping.dto;

import com.yoon.shopping.entity.ItemImg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemImgDto {

    @ApiModelProperty(value = "상품 이미지 번호", dataType = "Long", required = true)
    private Long id;

    @ApiModelProperty(value = "상품 이미지 이름", dataType = "String", required = true)
    private String imgName;

    @ApiModelProperty(value = "상품 이미지 원본이름", dataType = "String", required = true)
    private String oriImgName;

    @ApiModelProperty(value = "상품 이미지 Url", dataType = "String", required = true)
    private String imgUrl;

    @ApiModelProperty(value = "상품 대표 이미지 여부", dataType = "String", required = true)
    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg){
        return modelMapper.map(itemImg, ItemImgDto.class);
    }


}
