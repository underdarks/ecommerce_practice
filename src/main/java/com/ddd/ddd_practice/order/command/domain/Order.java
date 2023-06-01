package com.ddd.ddd_practice.order.command.domain;

import com.ddd.ddd_practice.common.jpa.MoneyConverter;
import com.ddd.ddd_practice.common.model.Money;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

@Entity
@Getter
@Table(name = "order")
@Access(AccessType.FIELD)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    /**
     * 복합키가 PK일 경우 사용
     */
    @EmbeddedId
    private OrderNo orderNo;

    /**
     * 엔티티 버전 관리를 통해 동시성 제어
     */
    @Version
    private Long version;

    @Embedded
    private Orderer orderer;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_product", joinColumns = @JoinColumn(name = "order_number"))
    @OrderColumn(name = "op_no")
    private List<OrderProduct> orderProducts;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "total_amounts")
    private Money totalAmounts;

    @Embedded
    private ShippingInfo shippingInfo;

    @Column(name = "state", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderState state;

    @Column(name = "order_date")
    private LocalDateTime orderDate;


    public Order(OrderNo orderNo, Orderer orderer, List<OrderProduct> orderProducts,
        Money totalAmounts, ShippingInfo shippingInfo, OrderState state, LocalDateTime orderDate) {
        setNumber(orderNo);
        setOrderer(orderer);
        setOrderProducts(orderProducts);
        setShippingInfo(shippingInfo);
        this.state = state;
        this.orderDate = LocalDateTime.now();
    }

    private void setNumber(OrderNo orderNo) {
        if (orderNo == null) {
            throw new IllegalArgumentException("주문 번호가 없습니다!");
        }
        this.orderNo = orderNo;
    }

    private void setOrderer(Orderer orderer) {
        if (orderer == null) {
            throw new IllegalArgumentException("주문자가 없습니다!");
        }
        this.orderer = orderer;
    }

    private void setOrderProducts(List<OrderProduct> orderProducts) {
        verifyAtLeastOneOrMoreOrderProducts(orderProducts);
        this.orderProducts = orderProducts;
        calculateTotalAmounts();
    }

    /**
     * 주문 상품이 최소 한개인지 확인
     */
    private void verifyAtLeastOneOrMoreOrderProducts(List<OrderProduct> orderProducts) {
        if (orderProducts == null || orderProducts.isEmpty()) {
            throw new IllegalArgumentException("주문 상품이 없습니다.");
        }
    }

    /**
     * 주문 건에 해당하는 총 금액 계산
     */
    private void calculateTotalAmounts() {
        int sum = orderProducts.stream()
            .mapToInt(x -> x.getAmounts().getValue())
            .sum();

        this.totalAmounts=new Money(sum);
    }

    private void setShippingInfo(ShippingInfo shippingInfo){
        if(shippingInfo == null) throw new IllegalArgumentException("주문정보가 없습니다.");
        this.shippingInfo=shippingInfo;
    }
}
