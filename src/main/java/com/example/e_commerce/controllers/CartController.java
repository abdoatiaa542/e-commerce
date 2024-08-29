package com.example.e_commerce.controllers;


import com.example.e_commerce.models.CartRequest;
import com.example.e_commerce.models.dto.CartDto;
import com.example.e_commerce.models.dto.CartItemDto;
import com.example.e_commerce.models.entity.Cart;
import com.example.e_commerce.models.entity.CartItem;
import com.example.e_commerce.models.mappers.CartItemMapper;
import com.example.e_commerce.models.mappers.CartMapper;
import com.example.e_commerce.service.utils.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    CartMapper cartMapper;

    @PostMapping("/add")
    public ResponseEntity<Void> addItemToCart(
            @RequestBody CartRequest cartRequestDto) {
        Cart cart = cartService.addItemToCart(cartRequestDto.userId, cartRequestDto.productId, cartRequestDto.quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get_all_items_inCart/{userId}")
    public ResponseEntity<Set<CartItemDto>> getAllItemsInCart(@PathVariable int userId) {
        Set<CartItem> items = cartService.getAllItemsInCart(userId);
        return ResponseEntity.ok(cartItemMapper.toDto(items));
    }


    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<CartDto> removeItemFromCart(
            @PathVariable int userId,
            @PathVariable int productId) {
        Cart cart = cartService.removeItemFromCart(userId, productId);
        return ResponseEntity.ok(cartMapper.toDto(cart));
    }


}
