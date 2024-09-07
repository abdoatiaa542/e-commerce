package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.OrderDto;
import com.example.e_commerce.models.dto.PaymentMethodDto;
import com.example.e_commerce.models.entity.Order;
import com.example.e_commerce.models.entity.PaymentMethod;
import com.example.e_commerce.models.mappers.OrderMapper;
import com.example.e_commerce.service.utils.CartService;
import com.example.e_commerce.service.utils.OrderService;
import com.example.e_commerce.service.utils.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private PaymentMethodService paymentMethodService;


    // get all orders
    @GetMapping("/get_all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.toOrderDtoList(orders));
    }


    @PostMapping("/add")
    public ResponseEntity<OrderDto> placeOrder(@RequestParam int userId, @RequestBody PaymentMethodDto paymentMethodDto) {

        PaymentMethod paymentMethodEntity = paymentMethodService.getPaymentMethodByName(paymentMethodDto.getMethodName());

        Order newOrder = orderService.placeOrder(userId, paymentMethodEntity);

        OrderDto orderDto = orderMapper.toDto(newOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }


    @DeleteMapping("/cancelOrder")
    public ResponseEntity<Void> cancelOrder(@RequestParam int orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/updateStatus")
    public ResponseEntity<OrderDto> updateStatus(@RequestParam int orderId, @RequestParam String status) {
        Order order = orderService.updateStatus(orderId, status);
        return ResponseEntity.ok(orderMapper.toDto(order));
    }


}
