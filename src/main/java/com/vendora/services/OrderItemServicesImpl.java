package com.vendora.services;

import com.vendora.models.OrderItems;
import com.vendora.repository.OrderItemsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServicesImpl implements OrderItemServices {

    private final OrderItemsRepository orderItemsRepository;
    public OrderItemServicesImpl(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    //to create an order item
    @Override
    public ResponseEntity<OrderItems> createOrderItem(OrderItems orderItem) {
        if(orderItem==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderItemsRepository.save(orderItem);
        return new ResponseEntity<>(orderItem, HttpStatus.OK);
    }
}
