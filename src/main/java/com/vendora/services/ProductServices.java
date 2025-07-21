package com.vendora.services;

import com.vendora.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductServices {
    public ResponseEntity<Product> createProduct(Product product);
    public ResponseEntity<Product> updateProduct(Product product,Integer id);
    public ResponseEntity<Product> deleteProduct(Integer id);
    public ResponseEntity<Product> getProduct(Integer id);
    public Page<Product> getAllProducts(Pageable  pageable);
}
