package com.ddd.ddd_practice.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.apache.kafka.common.protocol.types.Field.Str;

/**
 * 주소 값 타입
 */
@Embeddable
public class Address {

    @Column(name = "si")
    private String si;

    @Column(name = "gu")
    private String gu;

    @Column(name = "dong")
    private String dong;
}
