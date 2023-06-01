package com.ddd.ddd_practice.order.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum OrderState {

    PAYMENT_WAITTING("결제 대기"),
    PREPARING("준비중"),
    SHIPPED("출하 완료"),
    DELIVERING("배송중"),
    DELIVERY_COMPLETED("배송 완료"),
    CANCELED("주문 취소"),

    ;

    private String desc;
}
