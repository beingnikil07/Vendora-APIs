package com.vendora.services;

import com.vendora.models.Order;
import com.vendora.models.OrderItems;
import com.vendora.models.Product;
import com.vendora.repository.OrderRepository;
import com.vendora.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductRepository productRepository;

    //TO create a order
    @Override
    public ResponseEntity<Order> createOrder(Order order) {
        for (OrderItems item : order.getOrderItems()) {
            Integer productId = item.getProduct().getProduct_id();
            Product existingProduct = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

            item.setProduct(existingProduct); // attach managed entity
        }

        order.setOrderItems(order.getOrderItems()); // re-trigger order-setting logic

        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
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
