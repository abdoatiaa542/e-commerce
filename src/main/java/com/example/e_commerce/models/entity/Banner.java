package com.example.e_commerce.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "banners", schema = "groceries")
public class Banner {

    @Id
    @Column(name = "banner_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image_url", nullable = false)
    private String image;

    @Column(name = "start_time", nullable = false)
    private LocalDate startTime;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

}