package com.yoon.shopping.controller;

import com.yoon.shopping.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafExController {

    //th:text
    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model){
        ItemDto itemDto = new ItemDto();
        itemDto.setItemDetail("상품 상세 설명");
        itemDto.setItemNm("테스트 상품1");
        itemDto.setPrice(10000);
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto" , itemDto);
        return "thymeleafEx/thymeleafEx02";
    }

    //th:each
    @GetMapping(value = "/ex03")
    public String thymeleafExample03(Model model){

        List<ItemDto> itemDtoList = new ArrayList<>();

        for(int i=1; i<=10; i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+i);
            itemDto.setItemNm("테스트 상품1"+i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList" , itemDtoList);
        return "thymeleafEx/thymeleafEx03";
    }

    //th:if, th:unless
    @GetMapping(value = "/ex04")
    public String thymeleafExample04(Model model){

        List<ItemDto> itemDtoList = new ArrayList<>();

        for(int i=1; i<=10; i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+i);
            itemDto.setItemNm("테스트 상품1"+i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList" , itemDtoList);
        return "thymeleafEx/thymeleafEx04";
    }

    //th:switch, th:case
    @GetMapping(value = "/ex05")
    public String thymeleafExample05(Model model){

        List<ItemDto> itemDtoList = new ArrayList<>();

        for(int i=1; i<=10; i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+i);
            itemDto.setItemNm("테스트 상품1"+i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList" , itemDtoList);
        return "thymeleafEx/thymeleafEx04";
    }

    //th:href
    @GetMapping(value = "/ex06")
    public String thymeleafExample06(Model model){
        return "thymeleafEx/thymeleafEx06";
    }

    //layout
    @GetMapping(value = "/ex07")
    public String thymeleafExample07(Model model){
        return "thymeleafEx/thymeleafEx07";
    }
}
