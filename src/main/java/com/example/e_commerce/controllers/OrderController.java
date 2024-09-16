package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.ConfirmPaymentRequestDto;
import com.example.e_commerce.models.dto.OrderDto;
import com.example.e_commerce.models.dto.PaymentMethodDto;
import com.example.e_commerce.models.dto.PlaceOrderResponseDto;
import com.example.e_commerce.models.entity.Order;
import com.example.e_commerce.models.entity.PaymentMethod;
import com.example.e_commerce.models.entity.StatusOfOrder;
import com.example.e_commerce.models.mappers.OrderMapper;
import com.example.e_commerce.security.JwtUtil;
import com.example.e_commerce.service.utils.OrderService;
import com.example.e_commerce.service.utils.PaymentMethodService;
import com.example.e_commerce.service.utils.PaymentService;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PaymentMethodService paymentMethodService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("")
    public ResponseEntity<List<OrderDto>> getAllOrders(HttpServletRequest request) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        List<Order> orders = orderService.getOrderByUserId(userId);
        return ResponseEntity.ok(orderMapper.toOrderDtoList(orders));
    }


    @PostMapping("/add")
    public ResponseEntity<PlaceOrderResponseDto> placeOrder(HttpServletRequest request, @RequestBody PaymentMethodDto paymentMethodDto) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        PaymentMethod paymentMethodEntity = paymentMethodService.getPaymentMethodByName(paymentMethodDto.getMethodName());
        Order newOrder = orderService.placeOrder(userId, paymentMethodEntity);
        PlaceOrderResponseDto dto = new PlaceOrderResponseDto();
        dto.setOrder(orderMapper.toDto(newOrder));

        if (paymentMethodDto.getMethodName().equalsIgnoreCase("paymob")) {  // ok
            String paymentLink = paymentService.createPaymentLink(newOrder.getTotalPrice().doubleValue());
            dto.setPaymentLink(paymentLink);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }


    @PostMapping("/confirmPayment")
    public ResponseEntity confirmPayment(@RequestBody ConfirmPaymentRequestDto confirmPaymentRequest) {
        paymentService.isPaymentDone(confirmPaymentRequest.getPaymentLink());
        orderService.confirmOrderPayment(confirmPaymentRequest.getOrderId());
        Map<String, String> body = new HashMap<>();
        body.put("message", "Payment Confirmed");
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/cancelOrder/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable int orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateStatus(@PathVariable int orderId, @RequestBody JsonNode body) {
        Order order = orderService.updateStatus(orderId, body.get("status").asText());
        return ResponseEntity.ok(orderMapper.toDto(order));
    }


}
