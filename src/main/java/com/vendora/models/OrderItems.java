package com.vendora.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name ="order_items")
@Schema(name = "OrderItems", description = "Details of each item in an order including product, quantity, and price")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the order item",example="1001")
    private Integer order_item_id;

    // Many order items belong to one Order
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @NotNull(message = "Order must not be null")
    @Schema(description = "Associated order for this item")
    private Order order; // Use 'order' here, not 'order_id'

    // Many order items refer to one product
    @ManyToOne
    @JoinColumn(name ="product_id", referencedColumnName = "product_id")
    @NotNull(message = "Product must not be null")
    @Schema(description = "Product associated with the order item")
    private Product product;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    @Schema(description = "Quantity of the product ordered", example = "2")
    private Integer quantity;

    @NotNull(message = "Price per item is required")
    @Positive(message = "Price per item must be greater than 0")
    @Schema(description = "Price of a single unit of the product", example = "499.50")
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