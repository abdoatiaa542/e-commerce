package com.example.e_commerce.service.impl;

import com.example.e_commerce.models.entity.Review;
import com.example.e_commerce.models.entity.ReviewId;
import com.example.e_commerce.reposatory.ReviewRepository;
import com.example.e_commerce.service.utils.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsServiceImp implements ReviewsService {

    @Autowired
    private ReviewRepository reviewsRepository;


    @Override
    public Review getReviewById(ReviewId id) {
        Review review = reviewsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return review;
    }

    @Override
    public Review addReview(Review review) {
        Review savedReview = reviewsRepository.save(review);
        return savedReview;
    }

    public Review updateReview(ReviewId id, Review review) {
        Review existingReview = reviewsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return reviewsRepository.save(review);

    }

    @Override
    public void deleteReview(ReviewId id) {
        reviewsRepository.deleteById(id);
    }
}
