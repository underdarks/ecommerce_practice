package com.ddd.ddd_practice.common.jpa;

import com.ddd.ddd_practice.common.model.Money;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.criteria.CriteriaBuilder.In;

public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money money) {
        return money == null ? null: money.getValue();
    }

    @Override
    public Money convertToEntityAttribute(Integer value) {
        return value == null ? null: new Money(value);
    }
}
