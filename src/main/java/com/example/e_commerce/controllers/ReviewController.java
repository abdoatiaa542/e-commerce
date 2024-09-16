package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.ReviewRequestDto;
import com.example.e_commerce.models.dto.ReviewRequestUpdateDto;
import com.example.e_commerce.models.dto.ReviewResponseDto;
import com.example.e_commerce.models.entity.Review;
import com.example.e_commerce.models.entity.ReviewId;
import com.example.e_commerce.models.mappers.RevieRequestMapper;
import com.example.e_commerce.models.mappers.ReviewRequestUpdateDtoMapper;
import com.example.e_commerce.models.mappers.ReviewResponseMapper;
import com.example.e_commerce.security.JwtUtil;
import com.example.e_commerce.service.utils.ReviewsService;
import jakarta.servlet.http.HttpServletRequest;
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
    private RevieRequestMapper revieRequestMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ReviewResponseMapper reviewResponseMapper;
    @Autowired
    private ReviewRequestUpdateDtoMapper reviewRequestUpdateDtoMapper;


    @GetMapping("/{productId}")
    public ResponseEntity<ReviewResponseDto> getReview(HttpServletRequest httpServletRequest, @PathVariable int productId) {
        int userId = jwtUtil.getUserIdFromRequest(httpServletRequest);
        Review review = reviewsService.getReviewById(userId, productId);
        return ResponseEntity.ok(reviewResponseMapper.toDto(review));
    }


    @PostMapping("/add")
    public ResponseEntity<ReviewResponseDto> addReview(HttpServletRequest httpServletRequest, @RequestBody ReviewRequestDto reviewRequestDto) {
        int userId = jwtUtil.getUserIdFromRequest(httpServletRequest);
        Review review = revieRequestMapper.toEntity(reviewRequestDto);
        review.setId(new ReviewId(userId, reviewRequestDto.getProductId()));
        Review newReview = reviewsService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponseMapper.toDto(newReview));
    }


    @PutMapping("/{productId}")
    public ResponseEntity<ReviewResponseDto> updateReview(HttpServletRequest httpServletRequest, @PathVariable int productId, @RequestBody ReviewRequestUpdateDto reviewsDto) {
        int userId = jwtUtil.getUserIdFromRequest(httpServletRequest);
        Review review = reviewRequestUpdateDtoMapper.toEntity(reviewsDto);

        Review updatedReview = reviewsService.updateReview(new ReviewId(userId, productId), review);
        return ResponseEntity.ok(reviewResponseMapper.toDto(updatedReview));
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<ReviewResponseDto> deleteReview(HttpServletRequest httpServletRequest, @PathVariable int productId) {
        int userId = jwtUtil.getUserIdFromRequest(httpServletRequest);
        Review review = reviewsService.deleteReview(new ReviewId(userId, productId));
        return ResponseEntity.ok(reviewResponseMapper.toDto(review));
    }

}
