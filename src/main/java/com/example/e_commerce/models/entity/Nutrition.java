package com.example.e_commerce.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nutritions", schema = "groceries")
public class Nutrition {
    @Id
    @Column(name = "nutrition_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "calories", columnDefinition = "smallint UNSIGNED not null")
    private Integer calories;

    @Column(name = "protein", columnDefinition = "smallint UNSIGNED not null")
    private Integer protein;

    @Column(name = "carbohydrates", columnDefinition = "smallint UNSIGNED not null")
    private Integer carbohydrates;

}