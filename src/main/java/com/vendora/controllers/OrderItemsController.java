package com.vendora.controllers;

import com.vendora.models.OrderItems;
import com.vendora.services.OrderItemServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders/item")
@Tag(name = "Order Items controller",description = "Manage order items")
public class OrderItemsController {
    private final OrderItemServicesImpl orderItemServices;
   public OrderItemsController(OrderItemServicesImpl orderItemServices) {
       this.orderItemServices = orderItemServices;
   }

    @PostMapping(value = "/create",
    consumes = "application/json",
    produces = "application/json")
    @Operation(summary = "Create an order item",description = "You can create an order item by using this api")
    public ResponseEntity<OrderItems> createOrderItem(@Valid @RequestBody OrderItems orderItems){
        return orderItemServices.createOrderItem(orderItems);
    }

    @DeleteMapping(value = "/delete/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    @Operation(summary = "Delete an order item",description = "You can delete an order item by using the order id")
    public ResponseEntity<OrderItems> deleteOrderItem(@PathVariable("id") Integer id)
    {
        return orderItemServices.deleteOrderItem(id);
    }
}
