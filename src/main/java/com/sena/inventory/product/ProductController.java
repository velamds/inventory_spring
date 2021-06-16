package com.sena.inventory.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    //localhost:8080/api/products/2
    @DeleteMapping(path="/{id}")
    public void Delete(@PathVariable int id){
        //code here
        productService.ValidateDelete(id);
    }
}
