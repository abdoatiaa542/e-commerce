package com.example.e_commerce.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_items", schema = "groceries")
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @EmbeddedId
    private CartItemId id;

    @MapsId("cartId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", columnDefinition = "int UNSIGNED not null")
    private Long quantity = 0L;;



    // Constructor to initialize CartItem with Cart and Product
    public CartItem(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
        this.id = new CartItemId(cart.getId(), product.getId());

    }



}