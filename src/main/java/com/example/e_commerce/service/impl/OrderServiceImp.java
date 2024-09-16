package com.example.e_commerce.service.impl;

import com.example.e_commerce.exceptions.OrderCancellationException;
import com.example.e_commerce.exceptions.ResourceNotFoundException;
import com.example.e_commerce.models.entity.*;
import com.example.e_commerce.reposatory.OrderItemRepository;
import com.example.e_commerce.reposatory.OrderRepository;
import com.example.e_commerce.reposatory.OrderStatusRepository;
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
    @Autowired
    private OrderStatusRepository orderStatusRepository;


    @Override
    public List<Order> getOrderByUserId(int userId) {
        return orderRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

    }

    @Override
    public Order placeOrder(int userId, PaymentMethod paymentMethod) {
        Cart userCart = cartService.getCartByUserId(userId);
        validateCart(userCart);
        double totalPrice = calculateTotalPrice(userCart);
        // هنقرب لاقرب رقمين عشريين
        totalPrice = Math.round(totalPrice * 100.0) / 100.0;
        Order newOrder = createOrder(userCart.getUser(), paymentMethod, totalPrice);
        Order savedOrder = orderRepository.save(newOrder);
        Set<OrderItem> orderItems = createOrderItems(savedOrder, userCart);
        orderItemsRepository.saveAll(orderItems);
        newOrder.setOrderItems(orderItems);
//        clearCart(userId);
        return newOrder;
    }


    @Override
    public void cancelOrder(int orderId) {
        Order order = orderRepository.findById(orderId)        // 1. التحقق من وجود الأوردر
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getStatus().getName().equalsIgnoreCase("Cancelled") ||             // 2. التحقق من حالة الأوردر، إذا كان بالفعل ملغي أو مكتمل
                order.getStatus().getName().equalsIgnoreCase("Delivered")) {
            throw new OrderCancellationException("Cannot cancel this order");
        }


        order.setStatus(new OrderStatus(4, "Cancelled"));         // 3. تغيير حالة الأوردر إلى ملغي
        orderRepository.save(order);     // 4. حفظ الأوردر بعد التعديل
    }


    @Override
    public Order updateStatus(int orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        StatusOfOrder.valueOf(newStatus.toUpperCase());
        OrderStatus status = orderStatusRepository.findByNameIgnoreCase(newStatus);
        order.setStatus(status);
        return orderRepository.save(order);
    }


    @Override
    public Order confirmOrderPayment(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setPaymentStatus("Paid");
        return orderRepository.save(order);
    }


    private void validateCart(Cart cart) {
        if (cart == null || cart.getItems().isEmpty()) {
            throw new ResourceNotFoundException("Cart is empty or not found");
        }
    }

    private double calculateTotalPrice(Cart cart) {
        return cart.getItems().stream().mapToDouble(item -> item.getProduct().getUnitPrice().doubleValue() * item.getQuantity()).sum();
    }

    private Order createOrder(User user, PaymentMethod paymentMethod, double totalPrice) {
        Order order = new Order();
        order.setUser(user);  // 1
        order.setPaymentMethod(paymentMethod);  // 2
        order.setTotalPrice(BigDecimal.valueOf(totalPrice));  // 3
        order.setOrderDate(Instant.now());  // 4
        order.setStatus(new OrderStatus(1, "Pending")); // 5
        order.setPaymentStatus("Pinding");  // 6
        return order;
    }

    private Set<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getItems().stream().map(item -> new OrderItem(order, item.getProduct(), item.getQuantity().intValue())).collect(Collectors.toSet());
    }


    private void clearCart(int userId) {
        cartService.removeAllFromCart(userId);  // حفظ السلة بعد تفريغها
    }

}
