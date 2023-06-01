package com.ddd.ddd_practice.order.command.domain;

import com.ddd.ddd_practice.catalog.command.domain.product.ProductNo;
import com.ddd.ddd_practice.common.jpa.MoneyConverter;
import com.ddd.ddd_practice.common.model.Money;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문 상품
 */
@Entity
@Table(name = "order_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    @Embedded
    private ProductNo productNo;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "price")
    private Money price;    //제품 가격

    @Column(name = "quantity")
    private int quantity;   //제품 수량

    @Convert(converter = MoneyConverter.class)
    @Column(name = "amounts")
    private Money amounts;  //총 금액(가격*수량)

    public OrderProduct(ProductNo productNo, Money price, int quantity) {
        this.productNo = productNo;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() {
        return price.multiply(quantity);    //총 금액 = 가격 * 수량
    }
}
