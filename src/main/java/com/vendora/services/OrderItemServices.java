package com.vendora.services;


import com.vendora.models.OrderItems;
import org.springframework.http.ResponseEntity;

public interface OrderItemServices {
    public ResponseEntity<OrderItems> createOrderItem(OrderItems orderItem);
    public  ResponseEntity<OrderItems> deleteOrderItem(Integer id);
}
