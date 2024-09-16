//package com.example.e_commerce.models;
//
//import com.example.e_commerce.models.entity.Cart;
//import com.example.e_commerce.models.entity.Product;
//import jakarta.persistence.PostRemove;
//import jakarta.persistence.PrePersist;
//
//public class CartListener {
//
//
//    @PrePersist  // هينفذ قبل إضافة أي كارت جديد
//    public void reduceStock(Cart cart) {
//        Product product = cart.getProduct();
//        if (product.getStockQuantity() >= cart.getQuantity()) {
//            product.setStockQuantity(product.getStockQuantity() - cart.getQuantity());
//        } else {
//            throw new RuntimeException("Not enough stock available");
//        }
//    }
//
//    @PostRemove  // هينفذ بعد ما الأوردر يتم إلغاءه
//    public void increaseStock(Cart cart) {
//        Product product = cart.getProduct();
//        product.setStockQuantity(product.getStockQuantity() + cart.getQuantity());
//    }
//
//}
