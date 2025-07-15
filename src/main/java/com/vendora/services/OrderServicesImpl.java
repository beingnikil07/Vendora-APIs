package com.vendora.services;

import com.vendora.models.Order;
import com.vendora.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServicesImpl implements com.vendora.services.OrderServices {

    //Autowiring bean
    private final OrderRepository orderRepository;

    public OrderServicesImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //TO create a order
    @Override
    public ResponseEntity<Order> createOrder(Order order) {
        if(order==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //To update a order
    @Override
    public ResponseEntity<Order> updateOrder(Order order,Integer order_id) {
        if(order==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Order> order_get=orderRepository.findById(order_id);

        Order  existedOrder=order_get.get();

        if(order.getStatus()!=null){
            existedOrder.setStatus(order.getStatus());
        }
        if(order.getTotal_amount() != null){
            existedOrder.setTotal_amount(order.getTotal_amount());
        }

        //save existed order
        orderRepository.save(existedOrder);
        return new ResponseEntity<>(existedOrder, HttpStatus.OK);
    }

    //Delete a order
    public ResponseEntity<Order> deleteOrder(Integer order_id) {
        if(!orderRepository.existsById(order_id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //find order
        Order existedOrder =orderRepository.findById(order_id).get();
        orderRepository.delete(existedOrder);
        return new ResponseEntity<>(existedOrder, HttpStatus.OK);
    }

    //Get a Order
    @Override
    public ResponseEntity<Order> getOrder(Integer order_id) {
        if(order_id==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Order order=orderRepository.findById(order_id).get();
        return ResponseEntity.ok(order);
    }


}
