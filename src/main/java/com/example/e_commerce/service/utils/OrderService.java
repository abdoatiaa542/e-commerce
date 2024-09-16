package com.example.e_commerce.service.utils;


import com.example.e_commerce.models.entity.Order;
import com.example.e_commerce.models.entity.PaymentMethod;

import java.util.List;


public interface OrderService {


    List<Order> getOrderByUserId(int userId);

    Order placeOrder(int userId, PaymentMethod paymentMethod);

    void cancelOrder(int orderId);

    Order updateStatus(int orderId, String status);

    Order confirmOrderPayment(int orderId);

}