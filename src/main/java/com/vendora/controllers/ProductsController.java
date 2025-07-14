package com.vendora.controllers;

import com.vendora.models.Product;
import com.vendora.services.ProductServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //update the product
    @PatchMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable Integer id) {
        return  productService.updateProduct(product,id);
    }

    //Delete the product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }


    //get a product
    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return productService.getProduct(id);
    }

    //Get ALl Products
    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

}
