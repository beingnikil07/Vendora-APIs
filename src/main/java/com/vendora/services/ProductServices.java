package com.vendora.services;

import com.vendora.models.Product;
import org.springframework.http.ResponseEntity;

public interface ProductServices {
    public ResponseEntity<Product> createProduct(Product product);
}
