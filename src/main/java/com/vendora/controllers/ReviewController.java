package com.vendora.controllers;


import com.vendora.models.Product;
import com.vendora.models.Review;
import com.vendora.models.User;
import com.vendora.services.ReviewServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@Tag(name = "Reviews Controller",description ="Used to handle product reviews")
public class ReviewController {

    @Autowired
    private ReviewServicesImpl  reviewServices;

    @PostMapping(value = "/add",produces = "application/json")
    @Operation(summary = "Add a review for a product by a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User or Product not found")
    })
    public ResponseEntity<Review>  addReview(@RequestParam Integer userId,
                                                @RequestParam Integer productId,
                                                @RequestBody Review review_input)
    {
        Review review=reviewServices.addReview(userId,productId,review_input);
        return ResponseEntity.ok(review);
    }


    //get all reviews of a product
    @Operation(summary = "Get all reviews of a specific product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/getReviews")
    public ResponseEntity<List<Review>> getReviews(@RequestParam Integer productId){
        List<Review> reviews=reviewServices.getReviews(productId);
        return ResponseEntity.ok(reviews);
    }

    //delete a review
    @Operation(summary = "Delete a user's review for a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Review not found for given user and product")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReview(@RequestParam Integer userId, @RequestParam Integer productId){
        String review=reviewServices.deleteReview(userId,productId);
        return ResponseEntity.ok(review);
    }


}
