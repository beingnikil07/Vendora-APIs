package com.vendora.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Schema(description = "Entity representing a product review given by a user")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the review", example = "101")
    private int review_id;

    // Many reviews can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User cannot be null")
    @Schema(description = "User who submitted the review")
    private User user;

    // Many reviews can belong to one product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull(message = "Product cannot be null")
    @Schema(description = "Product being reviewed")
    private Product product;

    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    @Schema(description = "Rating given to the product", example = "4")
    private int rating;

    @Size(max = 500, message = "Comment can be up to 500 characters")
    @Schema(description = "Optional comment provided with the review", example = "Great quality and fast delivery!")
    private String comment;

    @Schema(description = "Date when the review was submitted", example = "2025-07-24T14:00:00")
    private LocalDateTime reviewDate;

    @Column(updatable = false)
    @Schema(description = "Timestamp when the review was created", example = "2025-07-24T14:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the review was last updated", example = "2025-07-24T14:00:00")
    private LocalDateTime updatedAt;

    // Auto set timestamps
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.reviewDate = this.createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters


    public int getId() {
        return review_id;
    }

    public void setId(int review_id) {
        this.review_id = review_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
