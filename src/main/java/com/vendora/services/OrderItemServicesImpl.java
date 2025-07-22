package com.vendora.services;

import com.vendora.models.Order;
import com.vendora.models.OrderItems;
import com.vendora.models.Product;
import com.vendora.repository.OrderItemsRepository;
import com.vendora.repository.OrderRepository;
import com.vendora.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServicesImpl implements OrderItemServices {

    private final OrderItemsRepository orderItemsRepository;
    public OrderItemServicesImpl(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;


    //logger
    private static final Logger logger = LoggerFactory.getLogger(OrderItemServicesImpl.class);


    //to create an order item
    @Override
    public ResponseEntity<OrderItems> createOrderItem(OrderItems orderItem) {
        logger.info("Received request to create order item");
        //getting order_id and product_id from the api request
        Integer orderId = orderItem.getOrder().getOrder_id();
        Integer productId = orderItem.getProduct().getProduct_id();

        logger.debug("Checking existence of Order ID: {} and Product ID: {}", orderId, productId);

        // Check if both Order and Product exist
        if (orderRepository.existsById(orderId) && productRepository.existsById(productId)) {
            // Fetch the actual Order and Product from DB
            Order order = orderRepository.findById(orderId).get();
            Product product = productRepository.findById(productId).get();

            // Set fully populated Order and Product
            orderItem.setOrder(order);
            orderItem.setProduct(product);

            // Save the OrderItem
            OrderItems savedOrderItem = orderItemsRepository.save(orderItem);

            // Return the response
            return ResponseEntity.ok(savedOrderItem);
        } else {
            // Return 404 if either Order or Product doesn't exist
            logger.warn("Product ID: {} does not exist", productId);
            return ResponseEntity.notFound().build();
        }
    }


    //To Delete an order_item
    @Override
    public ResponseEntity<OrderItems> deleteOrderItem(Integer id) {
        logger.info("Received request to delete order item with ID: {}", id);
        if(!orderItemsRepository.existsById(id)) {
            logger.warn("OrderItem with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
        OrderItems item=orderItemsRepository.findById(id).get();
        orderItemsRepository.delete(item);
        logger.info("OrderItem with ID: {} deleted successfully", id);
        return ResponseEntity.ok(item);
    }

}