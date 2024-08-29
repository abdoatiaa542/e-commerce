package com.example.e_commerce.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_roles", schema = "groceries")
public class UserRole {
    @Id
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "role", nullable = false, length = 10)
    private String role;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new LinkedHashSet<>();

}