package com.Ecommerce.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.model.Category;
import com.Ecommerce.repository.CategoryRepository;


@Service
public class CategoryService {

	  @Autowired
	    private CategoryRepository categoryRepository;
	  
	    @Transactional
	    public List<Category> getAllCategories() {
	        return categoryRepository.findAll();
	    }

	   
	    @Transactional
	    public Optional<Category> getCategoryById(Long id) {
	        return categoryRepository.findById(id);
	    }

	    
	    @Transactional
	    public Category createCategory(String name) {
	        Category category = new Category();
	        category.setName(name);

	        return categoryRepository.save(category);
	    }

	  
	    @Transactional
	    public void updateCategory(Category category, String name) {
	        category.setName(name);
	        categoryRepository.save(category);
	    }

	    
	    @Transactional
	    public void deleteCategory(Category category) {
	        categoryRepository.delete(category);
	    }

	   
	    @Transactional
	    public boolean isChildCategory(Category category, Category parent) {
	        return category.getParent().equals(parent);
	    }

	  
	    @Transactional
	    public void addChildCategory(Category category, Category parent) {
	        category.setParent(parent);
	        categoryRepository.save(category);
	    }

	
	    @Transactional
	    public void removeChildCategory(Category category, Category parent) {
	        category.setParent(null);
	        categoryRepository.save(category);
	    }

}
