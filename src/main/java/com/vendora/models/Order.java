package com.vendora.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "orders")
@Schema(name = "Order", description = "Details of a customer order including user, items, total amount, and status")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "unique identifier for the order",example ="145",required = true)
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @NotNull(message = "User must not be null")
    @JsonIgnoreProperties({"email","name","password","address","created_at","phone_no"})
    @Schema(description = "User placing the order")
    private User user;

    @Column(name = "order_date",updatable = false)
    @Schema(description = "Date and time when the order was placed", example = "2025-07-16T14:32:00")
    private LocalDateTime order_date;

    @NotNull(message = "Total amount must not be null")
    @Positive(message = "Total amount must be a positive value")
    @Schema(description = "Total amount of the order", example = "499.99")
    private Double total_amount;

    @NotBlank(message = "Order status must not be blank")
    @Size(max = 20, message = "Status can be at most 20 characters")
    @Schema(description = "Current status of the order", example = "shipped")
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @NotEmpty(message = "Order must contain at least one item")
    @JsonIgnore
    @Schema(description = "List of items in the order")
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
