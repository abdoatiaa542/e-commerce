package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.CartDto;
import com.example.e_commerce.models.dto.CartRequest;
import com.example.e_commerce.models.entity.Cart;
import com.example.e_commerce.models.entity.CartItem;
import com.example.e_commerce.models.mappers.CartItemMapper;
import com.example.e_commerce.models.mappers.CartMapper;
import com.example.e_commerce.security.JwtUtil;
import com.example.e_commerce.service.utils.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public CartController(CartService cartService, CartMapper cartMapper, JwtUtil jwtUtil) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/add")
    public ResponseEntity<CartDto> addItemToCart(@RequestBody CartRequest cartRequestDto, HttpServletRequest request) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        Cart cart = cartService.addItemToCart(userId, cartRequestDto.productId, cartRequestDto.quantity);
        return ResponseEntity.ok(cartMapper.toDto(cart));
    }


    @GetMapping("")
    public ResponseEntity<CartDto> getAllItemsInCart(HttpServletRequest request) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        Cart cart = cartService.getCart(userId);
        return ResponseEntity.ok(cartMapper.toDto(cart));
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<CartDto> removeItemFromCart(HttpServletRequest request, @PathVariable int productId) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        Cart cart = cartService.removeItemFromCart(userId, productId);
        return ResponseEntity.ok(cartMapper.toDto(cart));
    }


}
