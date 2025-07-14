package com.vendora.controllers;

import com.vendora.models.Category;
import com.vendora.repository.CategoryRepository;
import com.vendora.services.CategoryServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServicesImpl  categoryServices;


    @PostMapping("/create")
    public ResponseEntity<Category> electronics(@RequestBody Category category) {
        return categoryServices.createCategory(category);
    }

}
