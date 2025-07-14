package com.vendora.services;

import com.vendora.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductServices {
    public ResponseEntity<Product> createProduct(Product product);
    public ResponseEntity<Product> updateProduct(Product product,Integer id);
    public ResponseEntity<Product> deleteProduct(Integer id);
    public ResponseEntity<Product> getProduct(Integer id);
    public ResponseEntity<List<Product>> getAllProducts();
}
