package com.vendora.services;

import com.vendora.models.Product;
import com.vendora.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductServices {

    //inject bean
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //create product
    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        if (product == null){
            ResponseEntity.notFound().build();
        }
        Product prod=productRepository.save(product);
        return ResponseEntity.ok().body(prod);
    }
}
