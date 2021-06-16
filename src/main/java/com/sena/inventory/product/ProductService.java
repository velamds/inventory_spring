package com.sena.inventory.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
       //Here you should write your bussines logic

       return productRepository.findAll();

    }

    public void ValidateDelete(int id) {
        boolean productExists = productRepository.existsById(id);
        if(productExists){
            productRepository.deleteById(id);
        }else{
            throw new IllegalStateException("El producto con el id="+id+" no existe hermano(a).");
        }
    }

    // productRepository.deleteById(id);
}
