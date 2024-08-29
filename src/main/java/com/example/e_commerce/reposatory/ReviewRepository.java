package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.Review;
import com.example.e_commerce.models.entity.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
}