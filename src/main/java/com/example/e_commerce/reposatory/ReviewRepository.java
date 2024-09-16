package com.example.e_commerce.reposatory;

import com.example.e_commerce.models.entity.Review;
import com.example.e_commerce.models.entity.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, ReviewId> {


    @Query(value = "CALL get_product_rate(:productId)", nativeQuery = true)
    Double getProductRate(@Param("productId") Integer productId);

}