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
@Table(name = "nutritions", schema = "groceries")
public class Nutrition {
    @Id
    @Column(name = "nutrition_id", nullable = false)
    private Integer id;

    @Column(name = "calories", columnDefinition = "smallint UNSIGNED not null")
    private Integer calories;

    @Column(name = "protein", columnDefinition = "smallint UNSIGNED not null")
    private Integer protein;

    @Column(name = "carbohydrates", columnDefinition = "smallint UNSIGNED not null")
    private Integer carbohydrates;

}