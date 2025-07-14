package com.vendora.services;

import com.vendora.models.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryServices {

    public ResponseEntity<Category> createCategory(Category category);

}
