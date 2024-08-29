package com.example.e_commerce.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment_methods", schema = "groceries")
public class PaymentMethod {
    @Id
    @Column(name = "payment_method_id", nullable = false)
    private Integer id;

    @Column(name = "method_name", nullable = false, length = 45)
    private String methodName;

}