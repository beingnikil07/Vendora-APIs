package com.vendora.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


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
    private Order order; // Use 'order' here, not 'order_id'

    // Many order items refer to one product
    @ManyToOne
    @JoinColumn(name ="product_id", referencedColumnName = "product_id")
    private Product product;

    private Integer quantity;
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