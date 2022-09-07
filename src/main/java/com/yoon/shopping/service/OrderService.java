package com.yoon.shopping.service;

import com.yoon.shopping.constant.OrderStatus;
import com.yoon.shopping.dto.OrderDto;
import com.yoon.shopping.dto.OrderHisDto;
import com.yoon.shopping.dto.OrderItemDto;
import com.yoon.shopping.entity.*;
import com.yoon.shopping.repository.ItemImgRepository;
import com.yoon.shopping.repository.ItemRepository;
import com.yoon.shopping.repository.MemberRepository;
import com.yoon.shopping.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    private final ItemImgRepository itemImgRepository;

    /**
     * 로그인한 사용자가 주문을 한다.
     * */
    public Long order(OrderDto orderDto, String email){
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem =
                OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 로그인한 사용자의 주문이력 확인한다.
     * */
    @Transactional(readOnly = true)
    public Page<OrderHisDto> getOrderList(String email, Pageable pageable){
        List<Order>orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrder(email);

        List<OrderHisDto> orderHisDtos = new ArrayList<>();

        for (Order order : orders){
            OrderHisDto orderHisDto = new OrderHisDto(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems){
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(),"Y");
                OrderItemDto orderItemDto =
                        new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHisDto.addOrderItemDto(orderItemDto);
            }
            orderHisDtos.add(orderHisDto);
        }
        return new PageImpl<OrderHisDto>(orderHisDtos, pageable, totalCount);
    }

    /**
     * 로그인한 사용자, 주문한 사용자와의 검증
     * */
    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email){
        Member curMember = memberRepository.findByEmail(email);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = order.getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }

        return true;
    }

    /**
     * 주문 취소
     * */
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    }

}
