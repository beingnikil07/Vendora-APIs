package com.vendora.controllers;

import com.vendora.models.Order;
import com.vendora.services.OrderServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrderServicesImpl orderServices;
    public OrdersController(OrderServicesImpl orderServices) {
        this.orderServices = orderServices;
    }

    //create a order
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
       return  orderServices.createOrder(order);
    }

    //Update a Order
    @PatchMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order,@PathVariable Integer id) {
        return orderServices.updateOrder(order,id);
    }

    //Delete a order
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Integer id) {
        return orderServices.deleteOrder(id);
    }

    //get a order
    @GetMapping("/getorder/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        return orderServices.getOrder(id);
    }
}
