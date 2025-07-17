package com.vendora.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
@Table(name = "products")
@Schema(description = "Details of each product including product id ,name ,price etc.")
public class Product {
    @Id
    @NotNull(message = "Product id cannot be null")
    @Schema(description = "unique identifier for the products")
    private Integer product_id;

    @NotNull(message = "Product name cannot be null")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    @Schema(description ="name of the product ",example ="Vivo V5",required = true)
    private String name;

    @Schema(description = "product description")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "price must be greater than zero")
    @Schema(description = "price of the product",example ="510",required = true)
    private Double price;

    @NotNull(message = "stock quantity cannot be null")
    @Positive(message = "stock quantity must be greater than 0")
    @Schema(description = "Stock quantity of a particular product ",example = "1000",required = true)
    private Integer stock_quantity;

    //Many products can have one category
    @ManyToOne
    @JoinColumn(name = "category_id" ,referencedColumnName ="category_id")
    @NotNull(message = "Product category is required")
    @Schema(description = "Associated category for this product")
    private Category category_id;

    private String image_url;

    @Schema(description = "Time of product creation")
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
