package com.vendora.services;

import com.vendora.models.Category;
import com.vendora.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        //logger
        private static final Logger logger= LoggerFactory.getLogger(CategoryServicesImpl.class);


    //create Category
    @Override
    public ResponseEntity<Category> createCategory(Category category) {
        logger.info("Attempting to create category with ID: {}", category.getCategory_id());
        if(categoryRepository.existsById(category.getCategory_id())) {
            logger.warn("Category with ID: {} already exists", category.getCategory_id());
            return new ResponseEntity<>(category, HttpStatus.CONFLICT);
        }
        categoryRepository.save(category);
        logger.info("Category with ID: {} created successfully", category.getCategory_id());
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    //Update a category
    @Override
    public ResponseEntity<Category> updateCategory(Category category,Integer category_id) {

        logger.info("Attempting to update category with ID: {}", category_id);
        //check the existance
        Optional<Category> optionalCategory=categoryRepository.findById(category_id);
        if (optionalCategory.isEmpty()){
            logger.warn("Category with ID: {} not found for update", category_id);
            return ResponseEntity.notFound().build();
        }

        Category updatedCategory=optionalCategory.get();

        if(category.getName()!=null){
            logger.debug("Updating name of category ID {} from '{}' to '{}'",category_id,category.getName(),updatedCategory.getName());
            updatedCategory.setName(category.getName());
        }
        //save the updated category
        categoryRepository.save(updatedCategory);
        logger.info("Category with ID: {} updated successfully", category_id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    //To delete a category
    @Override
    public ResponseEntity<Category> deleteCategory(Integer category_id) {
        logger.info("Attempting to delete category with ID: {}", category_id);
            //if not present
            if(!categoryRepository.existsById(category_id)) {
                logger.warn("Category with ID: {} not found for deletion", category_id);
                return ResponseEntity.notFound().build();
            }

            categoryRepository.deleteById(category_id);
            logger.info("Category with ID: {} deleted successfully", category_id);
            return ResponseEntity.ok().build();
    }
}
