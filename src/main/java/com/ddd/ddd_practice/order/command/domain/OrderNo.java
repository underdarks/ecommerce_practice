package com.ddd.ddd_practice.order.command.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field.Str;

/**
 * 주문번호 값 타입
 */
@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderNo implements Serializable {

    @Column(name = "order_no")
    private String orderNo;


    /**
     *  값 타입은 동등성 비교를 하기 때문에 무조건 equals&hashCode 재정의 해줘야함
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderNo orderNo1 = (OrderNo) o;
        return Objects.equals(orderNo, orderNo1.orderNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNo);
    }
}
