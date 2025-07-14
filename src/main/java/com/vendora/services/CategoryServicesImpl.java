package com.vendora.services;

import com.vendora.models.Category;
import com.vendora.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    //Update a category
    @Override
    public ResponseEntity<Category> updateCategory(Category category,Integer category_id) {
        //check the existance
        Optional<Category> optionalCategory=categoryRepository.findById(category_id);
        if (optionalCategory.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Category updatedCategory=optionalCategory.get();

        if(category.getName()!=null){
            updatedCategory.setName(category.getName());
        }
        //save the updated category
        categoryRepository.save(updatedCategory);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    //To delete a category
    @Override
    public ResponseEntity<Category> deleteCategory(Integer category_id) {
            //if not present
            if(!categoryRepository.existsById(category_id)) {
                return ResponseEntity.notFound().build();
            }

            categoryRepository.deleteById(category_id);
            return ResponseEntity.ok().build();
    }
}
