package com.vendora.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @NotNull(message = "User must not be null")
    @JsonIgnoreProperties({"email","name","password","address","created_at","phone_no"})
    private User user;

    private LocalDateTime order_date;

    @NotNull(message = "Total amount must not be null")
    @Positive(message = "Total amount must be a positive value")
    private Double total_amount;

    @NotBlank(message = "Order status must not be blank")
    @Size(max = 20, message = "Status can be at most 20 characters")
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @NotEmpty(message = "Order must contain at least one item")
    @JsonIgnore
    private List<@Valid OrderItems> orderItems;

    @PrePersist
    public void prePersist() {
        order_date = LocalDateTime.now();
    }

    // getters and setters...

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
        if (orderItems != null) {
            for (OrderItems item : orderItems) {
                item.setOrder(this);
            }
        }
    }
}
