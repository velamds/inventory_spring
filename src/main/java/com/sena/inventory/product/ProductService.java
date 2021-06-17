package com.sena.inventory.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean ValidateDelete(int id) {
        boolean productExists = productRepository.existsById(id);
        if(!productExists){
            throw new IllegalStateException("El producto con el id="+id+" no existe hermano(a).");
        }
        productRepository.deleteById(id);
        return productExists;
    }

    public Optional<Product> ObtenerPorId(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent()){
            throw new IllegalStateException("El producto no existe");
        }
        return optionalProduct;
    }

    // productRepository.deleteById(id);
}
