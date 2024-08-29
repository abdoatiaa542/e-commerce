package com.example.e_commerce.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CartItemId implements Serializable {
    private static final long serialVersionUID = -5135266691918675326L;
    @Column(name = "cart_id", nullable = false)
    private Integer cartId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CartItemId entity = (CartItemId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.cartId, entity.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, cartId);
    }

}