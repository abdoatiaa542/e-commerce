package com.example.e_commerce.service.impl;

import com.example.e_commerce.models.entity.*;
import com.example.e_commerce.reposatory.OrderItemRepository;
import com.example.e_commerce.reposatory.OrderRepository;
import com.example.e_commerce.service.utils.CartService;
import com.example.e_commerce.service.utils.OrderService;
import jakarta.persistence.SecondaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
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

        return newOrder;
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


}













//
//    public Order placeOrder(int userId, PaymentMethod paymentMethod) {
//
//        Cart userCart = cartService.getCartByUserId(userId);
//
//        if (userCart == null || userCart.getItems().isEmpty()) {
//            throw new RuntimeException("Cart is empty");
//        }
//
//        User user = userCart.getUser();  //
//
//        double totalPrice = userCart.getItems().stream()
//                .mapToDouble(item -> item.getProduct().getUnitPrice().doubleValue() * item.getQuantity())
//                .sum();
//
//
//        Order newOrder = new Order();
//        newOrder.setUser(user);
//        newOrder.setPaymentMethod(paymentMethod);
//        newOrder.setTotalPrice(BigDecimal.valueOf(totalPrice));
//        newOrder.setOrderDate(Instant.now());
//        newOrder.setStatus(new OrderStatus(1, "Pending"));
//
//        Order savedOrder = orderRepository.save(newOrder);
//        Set<OrderItem> orderItems = userCart.getItems().stream()
//                .map(item -> new OrderItem(savedOrder, item.getProduct(), item.getQuantity().intValue())).collect(Collectors.toSet()); // <OrderItem>
//        newOrder.setOrderItems(orderItems);
//
//
////        OrderItem savedOrder2 = orderItemsRepository.save(orderItems.iterator().next());
//
//        orderItemsRepository.saveAll(orderItems);
//
//        return newOrder;
//
//    }
//}
