package com.vendora.services;

import com.vendora.models.Category;
import com.vendora.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServicesImpl implements CategoryServices {

        private final CategoryRepository categoryRepository;
        public CategoryServicesImpl(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

    //create Category
    @Override
    public ResponseEntity<Category> createCategory(Category category) {
        if(categoryRepository.existsById(category.getCategory_id())) {
            return new ResponseEntity<>(category, HttpStatus.CONFLICT);
        }
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
