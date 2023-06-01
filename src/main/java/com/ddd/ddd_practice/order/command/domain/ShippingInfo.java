package com.ddd.ddd_practice.order.command.domain;

import com.ddd.ddd_practice.common.model.Address;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class ShippingInfo {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "si", column = @Column(name = "shipping_si")),
        @AttributeOverride(name = "gu", column = @Column(name = "shipping_gu")),
        @AttributeOverride(name = "dong", column = @Column(name = "shipping_dong"))
    })
    private Address address;

    @Column(name = "shipping_message")
    private String message;

    @Embedded
    private Receiver receiver;
}
