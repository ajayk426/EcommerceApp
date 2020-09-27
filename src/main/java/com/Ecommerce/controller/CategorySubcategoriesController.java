package com.Ecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.model.Category;
import com.Ecommerce.service.CategoryService;

import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping(path = "/categories/{parentid}/subcategories")
public class CategorySubcategoriesController {

    @Autowired
    private CategoryService categoryService;
  

    @RequestMapping(method = RequestMethod.GET)
    public Set<Category> retrieveAllSubcategories(@PathVariable Long parentid) {
         Optional<Category> parent = categoryService.getCategoryById(parentid);
         Set<Category> subcategories = parent.get().getChildCategories();
         return subcategories;
        
      
    }

    @RequestMapping(path = "/{childid}", method = RequestMethod.POST)
    public void addSubcategory(@PathVariable Long parentid, @PathVariable Long childid) {
        
         Optional<Category> parent = categoryService.getCategoryById(parentid);
         Optional<Category> child = categoryService.getCategoryById(childid);
         categoryService.addChildCategory(child.get(), parent.get());
    }

    @RequestMapping(path = "/{childid}", method = RequestMethod.DELETE)
    public void removeSubcategory(@PathVariable Long parentid, @PathVariable Long childid) {
         Optional<Category> parentcat = categoryService.getCategoryById(parentid);
         Optional<Category> childcat = categoryService.getCategoryById(childid);
         if(parentcat.isPresent() && childcat.isPresent())
         {
		     Category parent=parentcat.get();
		     Category child=childcat.get();
        	 if (!categoryService.isChildCategory(child, parent)) {
		        }
		
		        else
		        {
		           categoryService.removeChildCategory(child, parent);
		        }
         }

    }

}
