package com.ddd.ddd_practice.catalog.command.domain.product;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 제품 번호 값 타입
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
@Access(AccessType.FIELD)
public class ProductNo implements Serializable {

    @Column(name = "product_no")
    private String productNo;

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductNo productNo1 = (ProductNo) o;
        return Objects.equals(productNo, productNo1.productNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNo);
    }
}
