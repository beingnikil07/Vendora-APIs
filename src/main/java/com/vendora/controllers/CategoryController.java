package com.vendora.controllers;

import com.vendora.models.Category;
import com.vendora.services.CategoryServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServicesImpl  categoryServices;

    //To create a category
    @PostMapping("/create")
    public ResponseEntity<Category> electronics(@RequestBody Category category) {
        return categoryServices.createCategory(category);
    }

    //To update a category
    @PatchMapping("/update/{category_id}")
    public ResponseEntity<Category> update(@RequestBody Category category,@PathVariable Integer category_id) {
        return  categoryServices.updateCategory(category,category_id);
    }

    //Delete a category
    @DeleteMapping("/delete")
    public ResponseEntity<Category> delete(@RequestParam Integer category_id){
        return categoryServices.deleteCategory(category_id);
    }
}
