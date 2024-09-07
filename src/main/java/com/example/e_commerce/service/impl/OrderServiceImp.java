package com.example.e_commerce.service.impl;

import com.example.e_commerce.models.entity.*;
import com.example.e_commerce.reposatory.OrderItemRepository;
import com.example.e_commerce.reposatory.OrderRepository;
import com.example.e_commerce.service.utils.CartService;
import com.example.e_commerce.service.utils.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderItemRepository orderItemsRepository;


    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order placeOrder(int userId, PaymentMethod paymentMethod) {

        Cart userCart = cartService.getCartByUserId(userId);
        validateCart(userCart);


        double totalPrice = calculateTotalPrice(userCart);
        Order newOrder = createOrder(userCart.getUser(), paymentMethod, totalPrice);

        Order savedOrder = orderRepository.save(newOrder);


        Set<OrderItem> orderItems = createOrderItems(savedOrder, userCart);
        orderItemsRepository.saveAll(orderItems);
        newOrder.setOrderItems(orderItems);

        clearCart(userId);


        return newOrder;
    }

    @Override
    public void cancelOrder(int orderId) {
        // 1. التحقق من وجود الأوردر
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // 2. التحقق من حالة الأوردر، إذا كان بالفعل ملغي أو مكتمل
        if (order.getStatus().getName().equalsIgnoreCase("Cancelled") ||
                order.getStatus().getName().equalsIgnoreCase("Delivered")) {
            throw new RuntimeException("Cannot cancel this order");
        }

        // 3. تغيير حالة الأوردر إلى ملغي
        order.setStatus(new OrderStatus(3, "Cancelled")); // Assuming 3 represents 'Cancelled'

        // 4. (اختياري) إرجاع المنتجات للسلة
//        returnItemsToCart(order);

        // 5. حفظ الأوردر المعدل في قاعدة البيانات
        orderRepository.save(order);
    }


    @Override
    public Order updateStatus(int orderId, String newStatus) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(new OrderStatus(orderId, newStatus));

        return orderRepository.save(order);
    }


    private void validateCart(Cart cart) {
        if (cart == null || cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty or not found");
        }
    }

    private double calculateTotalPrice(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getUnitPrice().doubleValue() * item.getQuantity())
                .sum();
    }

    private Order createOrder(User user, PaymentMethod paymentMethod, double totalPrice) {
        Order order = new Order();
        order.setUser(user);  // 1
        order.setPaymentMethod(paymentMethod);  // 2
        order.setTotalPrice(BigDecimal.valueOf(totalPrice));  // 3
        order.setOrderDate(Instant.now());  // 4
        order.setStatus(new OrderStatus(1, "Pending")); // 5
        return order;
    }

    private Set<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getItems().stream()
                .map(item -> new OrderItem(order, item.getProduct(), item.getQuantity().intValue()))
                .collect(Collectors.toSet());
    }


    private void clearCart(int userId) {
        cartService.removeAllFromCart(userId);  // حفظ السلة بعد تفريغها
    }

}
