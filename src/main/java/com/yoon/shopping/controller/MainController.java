package com.yoon.shopping.controller;

import com.yoon.shopping.dto.ItemSearchDto;
import com.yoon.shopping.dto.MainItemDto;
import com.yoon.shopping.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Api(tags = {"메인화면 Controller"})
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @ApiOperation(value = "메인 화면 페이지")
    @GetMapping(value="/")
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page,
                       Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);
        Page<MainItemDto> items =
                itemService.getMainItemPage(itemSearchDto,pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);
        return "main";
    }

}
