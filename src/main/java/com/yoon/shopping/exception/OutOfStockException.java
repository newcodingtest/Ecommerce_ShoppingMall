package com.yoon.shopping.exception;

public class OutOfStockException extends RuntimeException{

    //상품 주문 수량 > 상품 재고 수량 일때 발생
    public OutOfStockException(String message ){
        super(message);
    }
}
   