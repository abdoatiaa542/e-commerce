package com.example.e_commerce.service.utils;


import com.example.e_commerce.models.entity.Review;
import com.example.e_commerce.models.entity.ReviewId;

public interface ReviewsService {

    Review getReviewById(ReviewId id);

    Review addReview(Review review);

    Review updateReview(ReviewId id, Review review);

    void deleteReview(ReviewId id);
}
