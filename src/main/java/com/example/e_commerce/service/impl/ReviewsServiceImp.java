package com.example.e_commerce.service.impl;

import com.example.e_commerce.exceptions.ResourceNotFoundException;
import com.example.e_commerce.models.entity.Product;
import com.example.e_commerce.models.entity.Review;
import com.example.e_commerce.models.entity.ReviewId;
import com.example.e_commerce.models.entity.User;
import com.example.e_commerce.reposatory.ProductRepository;
import com.example.e_commerce.reposatory.ReviewRepository;
import com.example.e_commerce.reposatory.UserRepository;
import com.example.e_commerce.service.utils.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class ReviewsServiceImp implements ReviewsService {

    @Autowired
    private ReviewRepository reviewsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Review getReviewById(int userId, int productId) {
        return reviewsRepository.findById(new ReviewId(userId, productId)).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
    }


    @Override
    @Transactional
    public Review addReview(Review review) {

        Product product = productRepository.findById(review.getId().getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        User user = userRepository.findById(review.getId().getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        review.setProduct(product);
        review.setUser(user);
        review.setReviewDate(Instant.now());

        Review savedReview = reviewsRepository.save(review);
        Double averageRating = reviewsRepository.getProductRate(review.getId().getProductId());
        product.setRate(BigDecimal.valueOf(averageRating));

        return savedReview;
    }

        @Override
        @Transactional
        public Review updateReview(ReviewId id, Review review) {// ??  i want to edit it to make not all attribute should be updated

            Product product = productRepository.findById(id.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            reviewsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

            review.setProduct(product);
            review.setId(id);
            review.setReviewDate(Instant.now());

            Review savedReview = reviewsRepository.save(review);

            Double averageRating = reviewsRepository.getProductRate(review.getId().getProductId());
            product.setRate(BigDecimal.valueOf(averageRating));

            return savedReview;
        }


    @Override
    @Transactional
    public Review deleteReview(ReviewId id) {
        Product product = productRepository.findById(id.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Review review = reviewsRepository.findById(new ReviewId(id.getUserId(), id.getProductId())).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        reviewsRepository.deleteById(id);

        Double averageRating = reviewsRepository.getProductRate(review.getId().getProductId());
        product.setRate(BigDecimal.valueOf(averageRating));

        return review;
    }
}
