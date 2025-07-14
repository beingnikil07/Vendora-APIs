package com.vendora.controllers;

import com.vendora.models.Product;
import com.vendora.services.ProductServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    //Inject Bean
    private final ProductServices productService;
    public ProductsController(ProductServices productService) {
        this.productService = productService;
    }

    //create a product
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
}
