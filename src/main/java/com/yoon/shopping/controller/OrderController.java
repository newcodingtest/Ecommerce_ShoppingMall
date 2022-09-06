package com.yoon.shopping.controller;

import com.yoon.shopping.dto.OrderDto;
import com.yoon.shopping.dto.OrderHisDto;
import com.yoon.shopping.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Api(tags = {"주문 정보에 관한 Controller"})
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "로그인한 사용자의 주문 API")
    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order (@RequestBody @Valid OrderDto orderDto,
                                               BindingResult bindingResult, Principal principal){
        //에러 발생시 핸들링
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            //FieldError를 통해 왜 실패했는지 구체적으로 확인
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors){
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(),
            HttpStatus.BAD_REQUEST);
        }//end if

        String email = principal.getName();
        Long orderId;
        try{
           orderId = orderService.order(orderDto, email);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @ApiOperation(value = "로그인한 사용자의 주문 이력 확인 API")
    @GetMapping(value = {"/orders", "/orders/{page}"})
    public String orderHist(@PathVariable("page") Optional<Integer>page,
                            Principal principal, Model model){
        //1페이지 당 4개
        Pageable pageable = PageRequest.of(page.isPresent()?page.get():0,4);

        Page<OrderHisDto> orderHistDtoList =
                orderService.getOrderList(principal.getName(), pageable);

        model.addAttribute("orders", orderHistDtoList);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);

        return "order/orderHist";
    }

}
