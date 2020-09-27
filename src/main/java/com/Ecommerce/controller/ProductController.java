package com.Ecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.model.Product;
import com.Ecommerce.service.ProductService;

import java.util.Optional;





@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    

    @RequestMapping(method = RequestMethod.GET)
    public Page<Product> retrieveAllProducts(Pageable pageable) {
         Page<Product> products = productService.getAllProducts(pageable);
         return products;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product retrieveProduct(@PathVariable Long id) {
         Optional<Product> prod = productService.getProductById(id);
         Product  product=prod.get();
         return product;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createProduct(@RequestBody  Product prod) {
       
       productService.createProduct(prod.getName(), prod.getPrice());

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable Long id, @RequestBody Product prod) {
        
        Optional<Product> prodfind = productService.getProductById(id);
        if(prodfind.isPresent())
        {
         Product product=prodfind.get();
         productService.updateProduct(product, prod.getName(), prod.getPrice());
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id) {
          Optional<Product> prodfind = productService.getProductById(id);
          if(prodfind.isPresent())
          {
           Product product=prodfind.get();
           productService.deleteProduct(product);
          }
    }

   

}
