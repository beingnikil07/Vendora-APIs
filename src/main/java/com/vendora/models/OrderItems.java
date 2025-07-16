package com.vendora.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name ="order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_item_id;

    // Many order items belong to one Order
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonIgnore
    @NotNull(message = "Order must not be null")
    private Order order; // Use 'order' here, not 'order_id'

    // Many order items refer to one product
    @ManyToOne
    @JoinColumn(name ="product_id", referencedColumnName = "product_id")
    @NotNull(message = "Product must not be null")
    private Product product;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Price per item is required")
    @Positive(message = "Price per item must be greater than 0")
    private Double price_each;

    // Getters & Setters
    public Integer getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(Integer order_item_id) {
        this.order_item_id = order_item_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice_each() {
        return price_each;
    }

    public void setPrice_each(Double price_each) {
        this.price_each = price_each;
    }
}