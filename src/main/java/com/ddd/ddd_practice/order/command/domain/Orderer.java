package com.ddd.ddd_practice.order.command.domain;

import com.ddd.ddd_practice.member.command.domain.MemberId;
import jakarta.persistence.Embeddable;
import lombok.Getter;

/**
 * 주문자 값타입
 */
@Getter
@Embeddable
public class Orderer {

    private MemberId memberId;
}
