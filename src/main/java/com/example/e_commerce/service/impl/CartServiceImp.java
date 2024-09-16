package com.example.e_commerce.service.impl;

import com.example.e_commerce.exceptions.ResourceNotFoundException;
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

        User user = getUserById(userId);
        Product product = getProductById(productId);
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(user));  // لو مفيش كارت، بنعمل كارت جديد للمستخدم

        CartItem cartItem = cart.getItems().stream()        // 4. البحث عن المنتج في الكارت
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(new CartItem(cart, product));  // لو المنتج مش موجود، بنعمل عنصر جديد

        cartItem.setQuantity(cartItem.getQuantity() + quantity);        // 5. تحديث كمية المنتج في الكارت
        cart.addItem(cartItem);        // 6. إضافة أو تحديث العنصر في الكارت
        return cartRepository.save(cart);        // 7. حفظ الكارت بعد التحديثات

    }


    @Override
    public Cart getCart(Integer userId) {
        User user = getUserById(userId);
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }


    @Override
    public Cart removeItemFromCart(int userId, int productId) {
        Cart cart = getCart(userId);
        System.out.println(cart.getItems());
        System.out.println(cart.getUser());
        CartItem cartItem = cart.getItems().stream()        // 3. البحث عن العنصر المطلوب إزالته
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));

        cart.getItems().remove(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeAllFromCart(int userId) {
        User user = getUserById(userId);
        // 2. البحث عن كارت المستخدم
        Cart cart = getCartByUserId(userId);
        // 3. تفريغ الكارت
        cart.getItems().clear();
        // 4. حفظ الكارت بعد التحديثات
        return cartRepository.save(cart);
    }


    public Cart getCartByUserId(int userId) {
        return cartRepository.findByUserId(userId).orElse(null);
    }


    private User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

}
