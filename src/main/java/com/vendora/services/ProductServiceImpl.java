package com.vendora.services;

import com.vendora.models.Product;
import com.vendora.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductServices {

    //inject bean
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Logger
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    //create product
    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        logger.info("Attempting to create product with Name:{}", product.getName());
        if (product == null){
            logger.info("Product with Name:{} not found", product.getName());
            ResponseEntity.notFound().build();
        }
        Product prod=productRepository.save(product);
        logger.info("Product with Name:{} created successfully", prod.getName());
        return ResponseEntity.ok().body(prod);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product,Integer id) {
        logger.info("Attempting to update product with Id:{}", id);
        //check if product is exist or not
        Optional<Product> optionalProd=productRepository.findById(id);

        if (optionalProd.isEmpty()){
            logger.info("Product with Id:{} not found", id);
            ResponseEntity.notFound().build();
        }
        Product existProd=optionalProd.get();

        //don't update product id
        if(product.getName()!=null){
            existProd.setName(product.getName());
        }

        if(product.getDescription()!=null){
            existProd.setDescription(product.getDescription());
        }
        if(product.getPrice()!=null){
            existProd.setPrice(product.getPrice());
        }
        if (product.getStock_quantity()!=null){
            existProd.setStock_quantity(product.getStock_quantity());
        }
        if (product.getCategory_id()!=null){
            existProd.setCategory_id(product.getCategory_id());
        }
        if (product.getImage_url()!=null){
            existProd.setImage_url(product.getImage_url());
        }
        //save the existed product
        productRepository.save(existProd);
        logger.info("Product with Id:{} updated successfully", id);
        return ResponseEntity.ok().body(existProd);
    }


    //Delete a Product
    @Override
    public ResponseEntity<Product> deleteProduct(Integer id) {
        logger.info("Attempting to delete product with Id:{}", id);
        //check whether product exist or not
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            logger.info("Product with Id:{} deleted successfully", id);
            return ResponseEntity.ok().build();
        }
     logger.info("Product with Id:{} not found", id);
     return ResponseEntity.notFound().build();
    }


    //get single product
    @Override
    public ResponseEntity<Product> getProduct(Integer id) {
        logger.info("Attempting to get product with Id:{}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return ResponseEntity.ok(product);
    }


    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
            logger.info("Attempting to get all products with Pageable:{}", pageable);
            Page<Product> products=productRepository.findAll(pageable);
            logger.info("Product with Pageable:{} found successfully", pageable);
            return products;
    }
}
