package com.vendora.controllers;

import com.vendora.models.OrderItems;
import com.vendora.services.OrderItemServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders/item")
public class OrderItemsController {
    private final OrderItemServicesImpl orderItemServices;
   public OrderItemsController(OrderItemServicesImpl orderItemServices) {
       this.orderItemServices = orderItemServices;
   }

    @PostMapping("/create")
    public ResponseEntity<OrderItems> createOrderItem(@RequestBody OrderItems orderItems){
        return orderItemServices.createOrderItem(orderItems);
    }

}
