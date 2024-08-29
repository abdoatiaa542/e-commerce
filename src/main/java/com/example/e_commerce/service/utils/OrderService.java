package com.example.e_commerce.service.utils;


import com.example.e_commerce.models.entity.Order;
import com.example.e_commerce.models.entity.PaymentMethod;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order placeOrder(int userId, PaymentMethod paymentMethod);

}