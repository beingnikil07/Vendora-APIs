package com.vendora.services;

import com.vendora.models.Product;
import com.vendora.models.Review;
import com.vendora.models.User;
import com.vendora.repository.ProductRepository;
import com.vendora.repository.ReviewRepository;
import com.vendora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServicesImpl implements ReviewServices {

    @Autowired
    private  ReviewRepository reviewRepository;

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Review addReview(Integer userId, Integer productId, Review review_input) {
        //get user and product
        User user=userRepository.findById(userId).orElseThrow();
        Product product=productRepository.findById(productId).orElseThrow();

        Review review=reviewRepository.findByUserAndProduct(user,product);
        //if user is null ,create a new user
        if(review==null) {
            review=new Review();
        }
        review.setUser(user);
        review.setProduct(product);
        review.setComment(review_input.getComment());
        review.setRating(review_input.getRating());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviews(Integer productId) {
        //find the product
        Product product=productRepository.findById(productId).orElseThrow();
        List<Review> reviews=reviewRepository.findByProduct(product);
        return reviews;
    }

    @Override
    public String deleteReview(Integer userId, Integer productId) {
        Product product=productRepository.findById(productId).orElseThrow();
        User user=userRepository.findById(userId).orElseThrow();
        Review review=reviewRepository.findByUserAndProduct(user,product);
        if(review==null) {
            return "Review Not Found ";
        }
        reviewRepository.deleteById(review.getId());
        return "Review Deleted Successfully";
    }
}
