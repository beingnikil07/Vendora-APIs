package com.vendora.services;

import com.vendora.models.Order;
import org.springframework.http.ResponseEntity;

public interface OrderServices {
    public ResponseEntity<Order> createOrder(Order order);
    public ResponseEntity<Order> updateOrder(Order order,Integer order_id);
    public ResponseEntity<Order> deleteOrder(Integer order_id);
    public ResponseEntity<Order> getOrder(Integer order_id);

}
