package com.vendora.services;

import com.vendora.models.Review;
import java.util.List;

public interface ReviewServices {
    public Review  addReview(Integer userId, Integer productId,Review comment);
    public List<Review> getReviews(Integer productId);
    public String deleteReview(Integer userId, Integer productId);

}
