package com.ddd.ddd_practice.common.model;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 불변 객체
 */
@Getter
@AllArgsConstructor
public class Money {

    private int value;

    public Money multiply(int mul){
        return new Money(this.value*mul);   //불변성을 위해 새로운 인스턴스 생성
    }


    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
