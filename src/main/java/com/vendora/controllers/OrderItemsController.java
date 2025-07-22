package com.vendora.controllers;

import com.vendora.models.OrderItems;
import com.vendora.services.OrderItemServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(OrderItemsController.class);

    @PostMapping(value = "/create",
    consumes = "application/json",
    produces = "application/json")
    @Operation(summary = "Create an order item",description = "You can create an order item by using this api")
    public ResponseEntity<OrderItems> createOrderItem(@Valid @RequestBody OrderItems orderItems){
        logger.info("Creating an order item");
        ResponseEntity<OrderItems> response= orderItemServices.createOrderItem(orderItems);
        logger.info("Successfully created an order item");
        return response;
    }

    @DeleteMapping(value = "/delete/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    @Operation(summary = "Delete an order item",description = "You can delete an order item by using the order id")
    public ResponseEntity<OrderItems> deleteOrderItem(@PathVariable("id") Integer id)
    {
        logger.info("Deleting an order item with id:{}",id);
        ResponseEntity<OrderItems> response= orderItemServices.deleteOrderItem(id);
        logger.info("Successfully deleted an order item with id:{}",id);
        return response;
    }
}
