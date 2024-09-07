package com.example.e_commerce.service.impl;

import com.example.e_commerce.models.entity.Cart;
import com.example.e_commerce.models.entity.CartItem;
import com.example.e_commerce.models.entity.User;
import com.example.e_commerce.models.entity.Product;
import com.example.e_commerce.reposatory.CartRepository;
import com.example.e_commerce.reposatory.ProductRepository;
import com.example.e_commerce.reposatory.UserRepository;
import com.example.e_commerce.service.utils.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Cart addItemToCart(int userId, int productId, int quantity) {
        // 1. التأكد من وجود المستخدم في النظام
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. التأكد من وجود المنتج في النظام
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 3. البحث عن كارت المستخدم
        Cart cart = cartRepository.findByUserId(userId)
                .orElse(new Cart(user));  // لو مفيش كارت، بنعمل كارت جديد للمستخدم

        // 4. البحث عن المنتج في الكارت
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(new CartItem(cart, product));  // لو المنتج مش موجود، بنعمل عنصر جديد
//        System.out.println(cartItem.getQuantity());
//        System.out.println(cartItem.getQuantity() + quantity);
        // 5. تحديث كمية المنتج في الكارت
        cartItem.setQuantity(cartItem.getQuantity() + quantity);

        // 6. إضافة أو تحديث العنصر في الكارت
        cart.addItem(cartItem);

        // 7. حفظ الكارت بعد التحديثات
        return cartRepository.save(cart);
    }


    @Override
    public Set<CartItem> getAllItemsInCart(Integer userId) {

        // 1. التأكد من وجود المستخدم في النظام
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<CartItem> cartItem = cartRepository.findByUserId(userId)  // ??
                .orElseThrow(() -> new RuntimeException("Cart not found")).getItems();


        return cartItem;

    }


    @Override
    public Cart removeItemFromCart(int userId, int productId) {

        // 1. التأكد من وجود المستخدم في النظام
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. البحث عن كارت المستخدم
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // 3. البحث عن العنصر المطلوب إزالته
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        // 4. إزالة العنصر من الكارت
        cart.getItems().remove(cartItem);

        // 5. حفظ الكارت بعد التحديثات
        return cartRepository.save(cart);

    }


    public Cart getCartByUserId(int userId) {
        return cartRepository.findByUserId(userId).orElse(null);
    }


    @Override
    public Cart removeAllFromCart(int userId) {
        // 1. التأكد من وجود المستخدم في النظام
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. البحث عن كارت المستخدم
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // 3. تفريغ الكارت
        cart.getItems().clear();

        // 4. حفظ الكارت بعد التحديثات
        return cartRepository.save(cart);
    }


}
