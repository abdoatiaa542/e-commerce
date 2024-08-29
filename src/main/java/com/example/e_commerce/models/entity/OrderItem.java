package com.example.e_commerce.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items", schema = "groceries")
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", columnDefinition = "smallint UNSIGNED not null")
    private Integer quantity;


    public OrderItem(Order order, Product product, Integer quantity) {
        this.order = order;  // ??
        this.product = product;  // ??
        this.quantity = quantity;
        this.id = new OrderItemId(order.getId(), product.getId());
    }

}