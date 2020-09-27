package com.Ecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.model.Category;
import com.Ecommerce.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    ;

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> retrieveAllCategories() {
         List<Category> categories = categoryService.getAllCategories();
        return categories;
       
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Category retrieveCategory(@PathVariable Long id) {
         Optional<Category> category = categoryService.getCategoryById(id);
        return category.get();

       
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createCategory(@RequestBody  Category request) {
         categoryService.createCategory(request.getName());

        
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updateCategory(@PathVariable Long id, @RequestBody  Category request) {
    	Optional<Category> category = categoryService.getCategoryById(id);
        categoryService.updateCategory(category.get(), request.getName());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable Long id) {
        final Optional<Category> category = categoryService.getCategoryById(id);
        categoryService.deleteCategory(category.get());
    }

 

}
