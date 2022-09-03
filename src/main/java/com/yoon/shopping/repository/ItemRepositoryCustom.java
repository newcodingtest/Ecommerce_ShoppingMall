package com.yoon.shopping.repository;

import com.yoon.shopping.dto.ItemSearchDto;
import com.yoon.shopping.dto.MainItemDto;
import com.yoon.shopping.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto,
                                      Pageable pageable);
}
