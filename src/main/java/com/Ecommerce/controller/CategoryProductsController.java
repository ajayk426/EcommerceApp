package com.Ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.model.Category;
import com.Ecommerce.model.Product;
import com.Ecommerce.service.CategoryService;
import com.Ecommerce.service.ProductService;


@RestController
@RequestMapping(path = "/categories/{categoryid}/products")
public class CategoryProductsController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    
    @RequestMapping(method = RequestMethod.GET)
    public Page<Product> retrieveAllProducts(@PathVariable Long categoryid, Pageable pageable) {
         Optional<Category> category = categoryService.getCategoryById(categoryid);
         Page<Product> products = productService.getAllProducts(category.get(), pageable);
         return products;
    }

    @RequestMapping(path = "/{productid}", method = RequestMethod.POST)
    public void addProduct(@PathVariable Long categoryid, @PathVariable Long productid) {
         Optional<Category> category = categoryService.getCategoryById(categoryid);
         Optional<Product> product = productService.getProductById(productid);
        if (productService.hasCategory(product.get(), category.get())) {
          
        }
        productService.addCategory(product.get(), category.get());
    }

    @RequestMapping(path = "/{productid}", method = RequestMethod.DELETE)
    public void removeProduct(@PathVariable Long categoryid, @PathVariable Long productid) {
         Optional<Category> category = categoryService.getCategoryById(categoryid);
         Optional<Product> product = productService.getProductById(productid);
        if (!productService.hasCategory(product.get(), category.get())) {
          
        }
        else
        {
        productService.removeCategory(product.get(), category.get());
        }
    }

}
