package com.vendora.controllers;

import com.vendora.models.Order;
import com.vendora.services.OrderServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order controller",description = "Manage orders ")
public class OrdersController {

    private final OrderServicesImpl orderServices;
    public OrdersController(OrderServicesImpl orderServices) {
        this.orderServices = orderServices;
    }

    private static final Logger logger= LoggerFactory.getLogger(OrdersController.class);


    //create a order
    @Operation(summary = "create a order",description = "You can create an order by using this api.")
    @PostMapping(value = "/create",
    consumes = "application/json",
    produces = "application/json")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        logger.info("Creating an order...");
        ResponseEntity<Order> response=orderServices.createOrder(order);
        logger.info("Successfully created an order");
        return response;
    }

    //Update a Order
    @Operation(summary = "update an order",description = "You can update an order by using this api.")
    @PatchMapping(value = "/update/{id}",
            consumes = "application/json",
            produces = "application/json"
    )

    public ResponseEntity<Order> updateOrder(@RequestBody Order order,@PathVariable Integer id) {
        logger.info("Updating an order...");
        ResponseEntity<Order> response=orderServices.updateOrder(order,id);
        logger.info("Successfully updated an order");
        return response;
    }

    //Delete a order
    @Operation(summary = "delete an order",description = "You can delete an order by using this api.")
    @DeleteMapping(value = "/delete/{id}",
            produces = "application/json")
    public ResponseEntity<Order> deleteOrder(@PathVariable Integer id) {
        logger.info("Deleting an order...");
        ResponseEntity<Order> response=orderServices.deleteOrder(id);
        logger.info("Successfully deleted an order");
        return response;
    }
    //get a order
    @Operation(summary = "get an order",description = "You can get an order by providing the order id")
    @GetMapping(value = "/getorder/{id}",
    consumes = "application/json",
    produces = "application/json")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        logger.info("Getting an order by providing the order id...");
        ResponseEntity<Order> response= orderServices.getOrder(id);
        logger.info("Successfully retrieved an order");
        return response;
    }
}
