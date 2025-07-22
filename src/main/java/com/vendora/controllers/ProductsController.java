package com.vendora.controllers;

import com.vendora.models.Product;
import com.vendora.services.ProductServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    private static final Logger logger= LoggerFactory.getLogger(ProductsController.class);

    //create a product
    @Operation(summary ="Create a product",description = "You can create a product using this api")
    @PostMapping(value = "/create",
    consumes = "application/json",
    produces = "application/json")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        logger.info("Creating a product with name:{}",product.getName());
        ResponseEntity<Product> response= productService.createProduct(product);
        logger.info("Successfully created an product with name:{}",product.getName());
        return response;
        }
    //update the product
    @Operation(summary = "Update a product",description = "You can update the product using this api.")
    @PatchMapping(value = "/update/{id}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Product> updateProduct( @RequestBody Product product,@PathVariable Integer id) {
        logger.info("Updating a product with id:{}",product.getProduct_id());
        ResponseEntity<Product> response=productService.updateProduct(product,id);
        logger.info("Successfully updated an product with id:{}",product.getProduct_id());
        return response;
    }

    //Delete the product
    @Operation(summary = "Delete a product",description = "You can delete a product using this api.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        logger.info("Deleting a product with id:{}",id);
        ResponseEntity<Product> response= productService.deleteProduct(id);
        logger.info("Successfully deleted an product with id:{}",id);
        return response;
    }


    //get a product
    @GetMapping(value = "/getProduct/{id}",
            produces = "application/json")
    @Operation(summary = "Get a product",description = "You can get a user by providing the product id")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        logger.info("Getting a product with id:{}",id);
        ResponseEntity<Product> response= productService.getProduct(id);
        return response;
    }

    //Get ALl Products
    @GetMapping(value = "/getProducts",
            produces = "application/json")
    @Operation(summary = "Get all products",description = "You can get all the products using this api.")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue ="2") int size,
                                                        @RequestParam(defaultValue ="name") String sortBy) {
        logger.info("Getting all products....");
        Pageable pageable= PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> products=productService.getAllProducts(pageable);
        logger.info("Successfully get all products.");
        return ResponseEntity.ok(products);

    }

}
