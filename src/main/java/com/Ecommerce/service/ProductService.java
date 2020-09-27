package com.Ecommerce.service;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Ecommerce.model.Category;
import com.Ecommerce.model.Product;
import com.Ecommerce.repository.ProductRepository;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;
    

   
    @Transactional
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    
    @Transactional
    public Page<Product> getAllProducts(Category category, Pageable page) {
        return productRepository.findByAssociatedWithCategory(category.getId(), page);
    }

    
    @Transactional
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

  
    @Transactional
    public Product createProduct(String name, double price) {
      
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return productRepository.save(product);
    }

  
    @Transactional
    public void updateProduct(Product product, String name, double price) {
        product.setName(name);
        product.setPrice(price);
        productRepository.save(product);
    }

    
    @Transactional
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    
    @Transactional
    public boolean hasCategory(Product product, Category category) {
        return product.getCategories().contains(category);
    }


    @Transactional
    public void addCategory(Product product, Category category) {
        product.getCategories().add(category);
        productRepository.save(product);
    }

    @Transactional
    public void removeCategory(Product product, Category category) {
        product.getCategories().remove(category);
        productRepository.save(product);
    }

    @Transactional
    public boolean hasProductsAssociated(Category category) {
        return productRepository.countByAssociatedWithCategory(category.getId()) > 0;
    }


}
