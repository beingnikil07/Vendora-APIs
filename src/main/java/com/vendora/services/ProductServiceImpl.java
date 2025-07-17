package com.vendora.services;

import com.vendora.models.Product;
import com.vendora.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductServices {

    //inject bean
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //create product
    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        if (product == null){
            ResponseEntity.notFound().build();
        }
        Product prod=productRepository.save(product);
        return ResponseEntity.ok().body(prod);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product,Integer id) {
        //check if product is exist or not
        Optional<Product> optionalProd=productRepository.findById(id);

        if (optionalProd.isEmpty()){
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
        return ResponseEntity.ok().body(existProd);
    }


    //Delete a Product
    @Override
    public ResponseEntity<Product> deleteProduct(Integer id) {
        //check whether product exist or not
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
     return ResponseEntity.notFound().build();
    }


    //get single product
    @Override
    public ResponseEntity<Product> getProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products=productRepository.findAll();
        return ResponseEntity.ok().body(products);

    }
}
