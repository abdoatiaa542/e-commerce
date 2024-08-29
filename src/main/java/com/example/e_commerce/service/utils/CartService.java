package com.example.e_commerce.service.utils;


import com.example.e_commerce.models.entity.Cart;
import com.example.e_commerce.models.entity.CartItem;

import java.util.Set;

public interface CartService {

    public Cart addItemToCart(int userId, int productId, int quantity);

    public Set<CartItem> getAllItemsInCart(Integer userId);

    public Cart removeItemFromCart(int userId, int productId);

    public Cart getCartByUserId(int userId);

    public Cart removeAllFromCart(int userId);


}
