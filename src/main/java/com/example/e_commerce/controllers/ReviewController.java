package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.ReviewDto;
import com.example.e_commerce.models.entity.Review;
import com.example.e_commerce.models.entity.ReviewId;
import com.example.e_commerce.models.mappers.ReviewMapper;
import com.example.e_commerce.service.utils.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewsService reviewsService;
    @Autowired
    private ReviewMapper reviewMapper;


    @GetMapping("/{userId}/{productId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable int userId, @PathVariable int productId) {
        System.out.println("userId = " + userId);
        Review review = reviewsService.getReviewById(new ReviewId(userId, productId));
        return ResponseEntity.ok(reviewMapper.toDto(review));
    }

    @PostMapping("/add")
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto reviewsDto) {
        Review review = reviewMapper.toEntity(reviewsDto);
        Review newReview = reviewsService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewMapper.toDto(newReview));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable int userId, @PathVariable int productId, @RequestBody ReviewDto reviewsDto) {
        Review review = reviewMapper.toEntity(reviewsDto);
        Review updatedReview = reviewsService.updateReview(new ReviewId(userId, productId), review);
        return ResponseEntity.ok(reviewMapper.toDto(updatedReview));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int userId, @PathVariable int productId) {
        reviewsService.deleteReview(new ReviewId(userId, productId));
        return ResponseEntity.noContent().build();
    }

}
