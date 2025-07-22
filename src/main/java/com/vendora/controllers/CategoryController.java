package com.vendora.controllers;

import com.vendora.models.Category;
import com.vendora.services.CategoryServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "category controller",description = "manage category of products")
public class CategoryController {

    @Autowired
    private CategoryServicesImpl  categoryServices;

    private static final Logger logger= LoggerFactory.getLogger(CategoryController.class);

    //To create a category
    @PostMapping(value = "/create",
    consumes = "application/json",
    produces = "application/json")
    @Operation(summary = "Create a category" , description = "You can create a category of the products and group them based on a category")
    public ResponseEntity<Category> electronics( @Valid @RequestBody Category category) {
        logger.info("Creating category with name [{}]",category.getName());
        ResponseEntity<Category> response= categoryServices.createCategory(category);
        logger.info("Successfully Created category with name [{}]",category.getName());
        return response;
    }

    //To update a category
    @PatchMapping(value = "/update/{category_id}",
            consumes = "application/json",
            produces = "application/json"
    )
    @Operation(summary = "Update a category",description = "You can update a category using this api.")
    public ResponseEntity<Category> update(@RequestBody Category category,@PathVariable Integer category_id) {
        logger.info("Updating category with id: {}",category_id);
        ResponseEntity<Category> response=categoryServices.updateCategory(category,category_id);
        logger.info("Successfully updated category with id:",category_id);
        return  response;
    }

    //Delete a category
    @DeleteMapping("/delete")
    @Operation(summary = "Delete a category",description = "You can delete a category using this api.")
    public ResponseEntity<Category> delete(@RequestParam Integer category_id){
        logger.info("Deleting category with id:{}",category_id);
        ResponseEntity<Category> response=categoryServices.deleteCategory(category_id);
        logger.info("Successfully deleted category with id:{}",category_id);
        return response;
    }
}
