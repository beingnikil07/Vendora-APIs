package com.vendora.controllers;

import com.vendora.models.Product;
import com.vendora.services.ProductServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name ="Products-controller",description = "manage products ")
public class ProductsController {
    //Inject Bean
    private final ProductServices productService;
    public ProductsController(ProductServices productService) {
        this.productService = productService;
    }

    //create a product
    @Operation(summary ="Create a product",description = "You can create a product using this api")
    @PostMapping(value = "/create",
    consumes = "application/json",
    produces = "application/json")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }
    //update the product
    @Operation(summary = "Update a product",description = "You can update the product using this api.")
    @PatchMapping(value = "/update/{id}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Product> updateProduct( @RequestBody Product product,@PathVariable Integer id) {
        return  productService.updateProduct(product,id);
    }

    //Delete the product
    @Operation(summary = "Delete a product",description = "You can delete a product using this api.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }


    //get a product
    @GetMapping(value = "/getProduct/{id}",
            produces = "application/json")
    @Operation(summary = "Get a product",description = "You can get a user by providing the product id")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return productService.getProduct(id);
    }

    //Get ALl Products
    @GetMapping(value = "/getProducts",
            produces = "application/json")
    @Operation(summary = "Get all products",description = "You can get all the products using this api.")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

}
