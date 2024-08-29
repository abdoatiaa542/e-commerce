package com.example.e_commerce.models.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorites", schema = "groceries")
public class favorites {

    @EmbeddedId
    private favoritesId id;

}
