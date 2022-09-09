package com.yoon.shopping.dto;

import com.yoon.shopping.constant.OrderStatus;
import com.yoon.shopping.entity.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 이력
 * */
@Getter @Setter
public class OrderHisDto {

    @ApiModelProperty(value = "주문 아이디", dataType = "Long", required = true)
    private Long orderId;

    @ApiModelProperty(value = "주문 날짜", dataType = "String", required = true)
    private String orderDate;

    @ApiModelProperty(value = "주문 상태", dataType = "OrderStatus", required = true)
    private OrderStatus orderStatus;

    @ApiModelProperty(value = "주문 상품 리스트", dataType = "List", required = true)
    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    public OrderHisDto(Order order){
        this.orderId = order.getId();
        this.orderDate =
                order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }


    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }


}
