package com.vendora.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
@Table(name = "products")
public class Product {
    @Id
    @NotNull(message = "Product id cannot be null")
    private Integer product_id;

    @NotNull(message = "Product name cannot be null")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "price must be greater than zero")
    private Double price;

    @NotNull(message = "stock quantity cannot be null")
    @Positive(message = "stock quantity must be greater than 0")
    private Integer stock_quantity;

    //Many products can have one category
    @ManyToOne
    @JoinColumn(name = "category_id" ,referencedColumnName ="category_id")
    @NotNull(message = "Product category is required")
    private Category category_id;

    private String image_url;

    private LocalDateTime created_at;


    @PrePersist
    public void onCreate() {
        this.created_at = LocalDateTime.now();
    }


    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public Integer getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

}
